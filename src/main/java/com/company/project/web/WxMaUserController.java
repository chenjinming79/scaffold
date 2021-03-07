package com.company.project.web;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.alibaba.fastjson.JSONObject;
import com.company.project.configurer.WxMaConfiguration;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/wx/user/{appid}")
@Api(tags = {"/wx/user/{appid}"}, description = "微信小程序用户管理模块")
public class WxMaUserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*@Autowired
    private UserService userService;*/

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    @ApiOperation(value = "登陆接口", notes = "登陆接口")
    public Result login(@PathVariable String appid, String code) {
        if (StringUtils.isBlank(code)) {
            return ResultGenerator.genFailResult(ResultCode.WX_LOGIN_PARAM_ERROR,"empty jscode");
        }

        final WxMaService wxMaService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());

            //TODO 可以增加自己的逻辑，关联业务相关数据
            /*User user = userService.findUserByOpenId(session.getOpenid());
            if (null == user){
                user = new User();
                //根据微信唯一openId查询，没有就注册
                user.setId(DigitUtil.generatorLongId());
                user.setOpenId(session.getOpenid());
                user.setSessionKey(session.getSessionKey());
                userService.save(user);
                return ResultGenerator.genSuccessResult(user);
            }else {
                //直接返回
                return ResultGenerator.genSuccessResult(user);
            }*/
            return ResultGenerator.genSuccessResult(session);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return ResultGenerator.genFailResult(ResultCode.WX_LOGIN_ERROR,e.toString());
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("/info")
    @ApiOperation(value = "获取用户信息接口", notes = "获取用户信息接口")
    public Result info(@PathVariable String appid, String sessionKey,
                       String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxMaService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxMaService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return ResultGenerator.genFailResult(ResultCode.WX_USER_INFO_ERROR,"user check failed");
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return ResultGenerator.genSuccessResult(userInfo);
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @GetMapping("/phone")
    @ApiOperation(value = "获取用户绑定手机号信息", notes = "获取用户绑定手机号信息")
    public Result phone(@PathVariable String appid, String sessionKey, String signature,
                        String rawData, String encryptedData, String iv) {
        final WxMaService wxMaService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxMaService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return ResultGenerator.genFailResult(ResultCode.WX_USER_INFO_ERROR,"user check failed");
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxMaService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return ResultGenerator.genSuccessResult(phoneNoInfo);
    }
    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @GetMapping("/getSessionData")
    @ApiOperation(value = "获取用户绑定手机号信息", notes = "获取用户绑定手机号信息")
    public Result getSessionData(@PathVariable String appid, String code){
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        WxMaJscode2SessionResult jscode2SessionResult= null;
        try {
            jscode2SessionResult = wxService.jsCode2SessionInfo(code);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("未获取到微信会话");
        }
        return  ResultGenerator.genSuccessResult(jscode2SessionResult);
    }
    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @PostMapping("/getPhone")
    @ApiOperation(value = "获取用户绑定手机号信息", notes = "获取用户绑定手机号信息")
    public Result getPhone(@PathVariable String appid, @RequestBody JSONObject json){
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(json.getString("sessionKey"), json.getString("encryptedData"),json.getString("iv") );

        return  ResultGenerator.genSuccessResult(phoneNoInfo);
    }
}
