package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.XcOperatLogMapper;
import com.company.project.model.XcOperatLog;
import com.company.project.service.XcOperatLogService;
import com.company.project.core.AbstractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
* Created by CodeGenerator on 2020/12/05.
*/
@Service
@Transactional
public class XcOperatLogServiceImpl extends AbstractService<XcOperatLog> implements XcOperatLogService {
@Resource
private XcOperatLogMapper xcOperatLogMapper;

    @Override
    public Result list(Integer page, Integer size, XcOperatLog xcOperatLog) {
        PageHelper.startPage(page, size);
        xcOperatLog.setIsDelete(false);
        List<XcOperatLog> list = xcOperatLogMapper.list(xcOperatLog);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
