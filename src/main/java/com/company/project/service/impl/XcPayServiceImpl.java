package com.company.project.service.impl;

import com.company.project.dao.XcPayMapper;
import com.company.project.model.XcPay;
import com.company.project.service.XcPayService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/11/22.
*/
@Service
@Transactional
public class XcPayServiceImpl extends AbstractService<XcPay> implements XcPayService {
@Resource
private XcPayMapper tPayMapper;

}
