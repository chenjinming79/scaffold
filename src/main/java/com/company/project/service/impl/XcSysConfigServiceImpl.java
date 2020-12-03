package com.company.project.service.impl;

import com.company.project.dao.XcSysConfigMapper;
import com.company.project.model.XcSysConfig;
import com.company.project.service.XcSysConfigService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/12/03.
*/
@Service
@Transactional
public class XcSysConfigServiceImpl extends AbstractService<XcSysConfig> implements XcSysConfigService {
@Resource
private XcSysConfigMapper tSysConfigMapper;

}
