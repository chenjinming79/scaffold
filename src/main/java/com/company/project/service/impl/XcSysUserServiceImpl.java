package com.company.project.service.impl;

import com.company.project.dao.XcSysUserMapper;
import com.company.project.model.XcSysUser;
import com.company.project.service.XcSysUserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/11/24.
*/
@Service
@Transactional
public class XcSysUserServiceImpl extends AbstractService<XcSysUser> implements XcSysUserService {
@Resource
private XcSysUserMapper tSysUserMapper;

}
