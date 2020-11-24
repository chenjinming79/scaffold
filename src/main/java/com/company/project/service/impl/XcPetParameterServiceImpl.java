package com.company.project.service.impl;

import com.company.project.dao.XcPetParameterMapper;
import com.company.project.model.XcPetParameter;
import com.company.project.service.XcPetParameterService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/11/24.
*/
@Service
@Transactional
public class XcPetParameterServiceImpl extends AbstractService<XcPetParameter> implements XcPetParameterService {
@Resource
private XcPetParameterMapper tPetParameterMapper;

}
