package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.XcOperatLog;
import com.company.project.core.Service;


/**
* Created by CodeGenerator on 2020/12/05.
*/
public interface XcOperatLogService extends Service<XcOperatLog> {

    Result list(Integer page, Integer size, XcOperatLog xcOperatLog);

    Result add(XcOperatLog xcOperatLog);
}
