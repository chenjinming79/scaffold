package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.XcWithdraw;
import com.company.project.core.Service;


/**
* Created by CodeGenerator on 2020/11/22.
*/
public interface XcWithdrawService extends Service<XcWithdraw> {

    Result list(Integer page, Integer size, XcWithdraw xcWithdraw);
}
