package com.company.project.service.impl;

import com.company.project.dao.XcUserMapper;
import com.company.project.model.XcUser;
import com.company.project.service.XcUserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/11/18.
 */
@Service
@Transactional
public class XcUserServiceImpl extends AbstractService<XcUser> implements XcUserService {
    @Resource
    private XcUserMapper tUserMapper;

}
