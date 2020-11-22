package com.company.project.service.impl;

import com.company.project.dao.XcAnnounCementMapper;
import com.company.project.model.XcAnnounCement;
import com.company.project.service.XcAnnounCementService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/11/22.
*/
@Service
@Transactional
public class XcAnnounCementServiceImpl extends AbstractService<XcAnnounCement> implements XcAnnounCementService {
@Resource
private XcAnnounCementMapper tAnnounCementMapper;

}
