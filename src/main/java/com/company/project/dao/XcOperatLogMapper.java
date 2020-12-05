package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.XcOperatLog;

import java.util.List;

public interface XcOperatLogMapper extends Mapper<XcOperatLog> {

    List<XcOperatLog> list(XcOperatLog xcOperatLog);

}