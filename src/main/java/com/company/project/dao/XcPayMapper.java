package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.XcPay;

import java.util.List;

public interface XcPayMapper extends Mapper<XcPay> {

    List<XcPay> list(XcPay xcPay);

}