package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.XcPay;
import com.company.project.core.Service;


/**
* Created by CodeGenerator on 2020/11/22.
*/
public interface XcPayService extends Service<XcPay> {

    Result list(Integer page,Integer size,XcPay xcPay);
}
