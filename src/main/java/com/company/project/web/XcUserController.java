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

    @ApiOperation(value = "用户退出", notes = "用户退出")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result logout(@RequestParam Long userId) {
        Logger.info(this,"/xc/user/logout 用户退出接口入参 : " + userId);
        return xcUserService.logout(userId);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody LoginVo vo, HttpServletRequest request) {
        Logger.info(this, "/xc/user/login 用户登录接口入参 :" + vo);
        return xcUserService.login(vo);
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

    @ApiOperation(value = "生成验证码", notes = "生成验证码")
    @RequestMapping(value = "/captcha", method = RequestMethod.POST)
    public Result captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return xcUserService.captcha();
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
