package com.company.project.service.impl;

import com.company.project.dao.XcOperatLogMapper;
import com.company.project.model.XcOperatLog;
import com.company.project.service.XcOperatLogService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/12/05.
*/
@Service
@Transactional
public class XcOperatLogServiceImpl extends AbstractService<XcOperatLog> implements XcOperatLogService {
@Resource
private XcOperatLogMapper tOperatLogMapper;

}
