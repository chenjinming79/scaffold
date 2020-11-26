package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.XcWithdrawMapper;
import com.company.project.model.XcWithdraw;
import com.company.project.service.XcWithdrawService;
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
public class XcWithdrawServiceImpl extends AbstractService<XcWithdraw> implements XcWithdrawService {
@Resource
private XcWithdrawMapper xcWithdrawMapper;

    @Override
    public Result list(Integer page, Integer size, XcWithdraw xcWithdraw) {
        PageHelper.startPage(page, size);
        List<XcWithdraw> list = xcWithdrawMapper.list(xcWithdraw);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
