package com.company.project.service.impl;

import com.company.project.dao.XcPetDetailsMapper;
import com.company.project.model.XcPetDetails;
import com.company.project.service.XcPetDetailsService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/12/09.
*/
@Service
@Transactional
public class XcPetDetailsServiceImpl extends AbstractService<XcPetDetails> implements XcPetDetailsService {
@Resource
private XcPetDetailsMapper tPetDetailsMapper;

}
