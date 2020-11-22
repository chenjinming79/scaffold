package com.company.project.service.impl;

import com.company.project.dao.XcWithdrawMapper;
import com.company.project.model.XcWithdraw;
import com.company.project.service.XcWithdrawService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/11/22.
*/
@Service
@Transactional
public class XcWithdrawServiceImpl extends AbstractService<XcWithdraw> implements XcWithdrawService {
@Resource
private XcWithdrawMapper tWithdrawMapper;

}
