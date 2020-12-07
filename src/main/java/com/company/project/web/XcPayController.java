package com.company.project.web;
import com.company.project.aop.OperatLog;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcPay;
import com.company.project.model.XcUser;
import com.company.project.service.XcPayService;
import com.company.project.core.Page;
import com.company.project.service.XcUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

/**
* Created by CodeGenerator on 2020/11/22.
*/
@RestController
@RequestMapping("/xc/pay")
@Api(tags = {"/xc/pay"}, description = "充值记录管理模块")
public class XcPayController {
    @Resource
    private XcPayService xcPayService;

    @Resource
    private XcUserService xcUserService;

    @ApiOperation(value = "新增充值记录", notes = "新增充值记录")
    @OperatLog(function = "新增",module = "充值管理",content = "新增充值记录")
    @RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
    public Result add(@RequestBody XcPay xcPay) {
        Date date=new Date();
        xcPay.setCreateTime(date);
        xcPay.setUpdateTime(date);
        xcPay.setIsDelete(false);
        xcPayService.save(xcPay);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcPay);
        return result;
    }

    @ApiOperation(value = "删除充值记录", notes = "删除充值记录")
    @OperatLog(function = "删除",module = "充值管理",content = "删除充值记录")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    public Result delete(@RequestParam Long id) {
        XcPay xcPay=new XcPay();
        xcPay.setId(id);
        xcPay.setIsDelete(true);
        xcPayService.update(xcPay);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "审批充值记录", notes = "审批充值记录")
    @OperatLog(function = "修改",module = "充值管理",content = "审批充值记录")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    public Result update(@RequestBody XcPay xcPay) {
        xcPayService.update(xcPay);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcPay);
        return result;
    }

    @ApiOperation(value = "获取详情", notes = "获取详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST,RequestMethod.GET})
    public Result detail(@RequestParam Long id) {
        XcPay xcPay = xcPayService.findById(id);
        return ResultGenerator.genSuccessResult(xcPay);
    }

    @ApiOperation(value = "分页模糊查询充值记录", notes = "分页模糊查询充值记录")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,@RequestParam(defaultValue="20",required = false) Integer size,
                       @RequestBody(required = false) XcPay xcPay) {
        return xcPayService.list(page,size,xcPay);

    }
}
