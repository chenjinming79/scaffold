package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.XcConsumePetRecordMapper;
import com.company.project.model.XcConsumePetRecord;
import com.company.project.param.PreorderPetParam;
import com.company.project.service.XcConsumePetRecordService;
import com.company.project.core.AbstractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
* Created by CodeGenerator on 2020/12/12.
*/
@Service
@Transactional
public class XcConsumePetRecordServiceImpl extends AbstractService<XcConsumePetRecord> implements XcConsumePetRecordService {
@Resource
private XcConsumePetRecordMapper tConsumePetRecordMapper;

    @Override
    public Result list(Integer page, Integer size, XcConsumePetRecord xcConsumePetRecord) {
        PageHelper.startPage(page, size);
        xcConsumePetRecord.setIsDelete(false);
        List<XcConsumePetRecord> list = findByModel(xcConsumePetRecord);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
