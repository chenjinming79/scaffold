package com.company.project.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName LoginVo
 * @Description //TODO
 * @Author cjm
 * @Date 2020/11/23 17:09
 * @Version 1.0
 **/
public class LoginVo {

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "渠道号")
    private String channel;

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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
