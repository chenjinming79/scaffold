package com.company.project.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @ClassName LoginVo
 * @Description //TODO
 * @Author cjm
 * @Date 2020/11/23 17:09
 * @Version 1.0
 **/
public class LoginVo {

    @NotNull(message = "手机号不可为空")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @NotNull(message = "密码不可为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @NotNull(message = "验证码key不可为空")
    @ApiModelProperty(value = "验证码key")
    private String verifyKey;

    @NotNull(message = "登录验证码不可为空")
    @ApiModelProperty(value = "登录验证码")
    private String verifyCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
