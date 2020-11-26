package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.XcWithdraw;

import java.util.List;

public interface XcWithdrawMapper extends Mapper<XcWithdraw> {

    List<XcWithdraw> list(XcWithdraw xcWithdraw);

}