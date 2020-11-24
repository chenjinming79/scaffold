package com.company.project.vo;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserVo
 * @Description //TODO
 * @Author cjm
 * @Date 2020/11/24 15:30
 * @Version 1.0
 **/
public class UserVo implements Serializable {

    private Long userId;

    private String phone;

    private String token;

    private Long expireTime;

    private String channel;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public UserVo() {
        super();
    }

    public UserVo(Long userId, String phone, String token, Long expireTime, String channel) {
        this.userId = userId;
        this.phone = phone;
        this.token = token;
        this.expireTime = expireTime;
        this.channel = channel;
    }
}
