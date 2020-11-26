package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.XcPayMapper;
import com.company.project.model.XcPay;
import com.company.project.service.XcPayService;
import com.company.project.core.AbstractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
* Created by CodeGenerator on 2020/11/22.
*/
@Service
@Transactional
public class XcPayServiceImpl extends AbstractService<XcPay> implements XcPayService {
@Resource
private XcPayMapper tPayMapper;

    @Override
    public Result list(Integer page,Integer size,XcPay xcPay) {
        PageHelper.startPage(page, size);
        List<XcPay> list = tPayMapper.list(xcPay);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
