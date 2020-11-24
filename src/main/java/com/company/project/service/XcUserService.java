package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.model.XcUser;
import com.company.project.core.Service;
import com.company.project.vo.LoginVo;


/**
 * Created by CodeGenerator on 2020/11/18.
 */
public interface XcUserService extends Service<XcUser> {

    Result login(LoginVo vo);

    Result captcha();

    Result logout(Long userId);
}
