package com.company.project.common;

import com.company.project.utils.Constant;
import com.company.project.utils.RedisService;
import com.company.project.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisService redisService;

    public Long getUserId() {
        String token = request.getHeader(Constant.TOKEN_NAME);
        SysUserVo sysUserVo;
        try {
            sysUserVo = (SysUserVo)redisService.get(Constant.REDIS_KEY_LOGIN+token);
        }catch (Exception e){
            return null;
            //throw new RedisException();
        }
        if (null == sysUserVo) {
            return null;
        }
        return sysUserVo.getUserId();
    }
}
