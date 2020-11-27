package com.company.project.service.impl;

import com.company.project.dao.XcAreaCodeMapper;
import com.company.project.model.XcAreaCode;
import com.company.project.service.XcAreaCodeService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/11/27.
*/
@Service
@Transactional
public class XcAreaCodeServiceImpl extends AbstractService<XcAreaCode> implements XcAreaCodeService {
@Resource
private XcAreaCodeMapper tAreaCodeMapper;

}
