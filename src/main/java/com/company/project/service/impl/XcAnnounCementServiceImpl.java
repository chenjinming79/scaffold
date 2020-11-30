package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.XcAnnounCementMapper;
import com.company.project.model.XcAnnounCement;
import com.company.project.service.XcAnnounCementService;
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
public class XcAnnounCementServiceImpl extends AbstractService<XcAnnounCement> implements XcAnnounCementService {
@Resource
private XcAnnounCementMapper xcAnnounCementMapper;

    @Override
    public Result list(Integer page, Integer size, XcAnnounCement xcAnnounCement) {
        PageHelper.startPage(page, size);
        List<XcAnnounCement> list = xcAnnounCementMapper.list(xcAnnounCement);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
