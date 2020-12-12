package com.company.project.service.impl;

import com.company.project.dao.XcConsumePetRecordMapper;
import com.company.project.model.XcConsumePetRecord;
import com.company.project.service.XcConsumePetRecordService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/12/12.
*/
@Service
@Transactional
public class XcConsumePetRecordServiceImpl extends AbstractService<XcConsumePetRecord> implements XcConsumePetRecordService {
@Resource
private XcConsumePetRecordMapper tConsumePetRecordMapper;

}
