package com.company.project.service.impl;

import com.company.project.dao.XcFeedPetRecordMapper;
import com.company.project.model.XcFeedPetRecord;
import com.company.project.service.XcFeedPetRecordService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/12/14.
*/
@Service
@Transactional
public class XcFeedPetRecordServiceImpl extends AbstractService<XcFeedPetRecord> implements XcFeedPetRecordService {
@Resource
private XcFeedPetRecordMapper tFeedPetRecordMapper;

}
