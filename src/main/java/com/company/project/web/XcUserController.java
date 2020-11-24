package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcUser;
import com.company.project.service.XcUserService;
import com.company.project.utils.Logger;
import com.company.project.utils.Md5Utils;
import com.company.project.utils.RedisService;
import com.company.project.vo.LoginVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wf.captcha.GifCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/xc/user")
@Api(tags = {"/xc/user"}, description = "会员管理模块")
public class XcUserController {
    @Resource
    private XcUserService xcUserService;

    @Autowired
    private RedisService redisService;

    /**
     * 用户登录
     *
     * @param vo
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody LoginVo vo, HttpServletRequest request) {
        Logger.info(this, "/xc/user/delete 用户登录接口入参 :" + vo);
        return xcUserService.login(vo);
    }

    @ApiOperation(value = "生成验证码", notes = "生成验证码")
    @RequestMapping(value = "/captcha", method = RequestMethod.POST)
    public Result captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GifCaptcha specCaptcha = new GifCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为30分钟
        //redisService.setWithExpire(key,verCode,2505600000L);
        System.out.println(specCaptcha.toBase64());
        // 将key和base64返回给前端
        return ResultGenerator.genSuccessResult(specCaptcha.toBase64());
    }

    @PostMapping("/add")
    @ApiOperation(value = "会员注册", notes = "会员注册")
    public Result add(@RequestBody XcUser xcUser) {
        xcUser.setCreateTime(new Date());
        xcUser.setStatus(1);
        xcUser.setRegisterTime(new Date());
        xcUser.setPassword(Md5Utils.getMd5(xcUser.getPassword()));
        xcUserService.save(xcUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "逻辑删除会员", notes = "逻辑删除会员")
    public Result delete(@RequestParam Long id) {
        XcUser xcUser = new XcUser();
        xcUser.setId(id);
        xcUser.setIsDelete(true);
        xcUserService.update(xcUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改会员", notes = "修改会员")
    public Result update(@RequestBody XcUser xcUser) {
        xcUserService.update(xcUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取会员详情", notes = "获取会员详情")
    public Result detail(@RequestParam Long id) {
        XcUser xcUser = xcUserService.findById(id);
        return ResultGenerator.genSuccessResult(xcUser);
    }

    @PostMapping("/findByModal")
    @ApiOperation(value = "分页查询会员", notes = "分页查询会员")
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,@RequestParam(defaultValue="20",required=false) Integer size, @RequestBody(required =false) XcUser xcUser) {
        PageHelper.startPage(page, size);
        xcUser.setIsDelete(false);
        List<XcUser> list = xcUserService.findByModel(xcUser);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
