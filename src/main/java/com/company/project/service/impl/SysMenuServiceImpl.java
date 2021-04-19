package com.company.project.service.impl;

import com.company.project.dao.SysMenuMapper;
import com.company.project.model.SysMenu;
import com.company.project.service.SysMenuService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
* Created by CodeGenerator on 2021/04/20.
*/
@Service
@Transactional
public class SysMenuServiceImpl extends AbstractService<SysMenu> implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<Object> selectMenuByRoleId(Integer roleId) {
        return sysMenuMapper.selectMenuByRoleId(roleId);
    }
}
