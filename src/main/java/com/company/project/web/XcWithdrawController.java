package com.company.project.web;

import com.company.project.aop.OperatLog;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcWithdraw;
import com.company.project.service.XcWithdrawService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
* Created by CodeGenerator on 2020/11/22.
*/
@RestController
@RequestMapping("/xc/withdraw")
@Api(tags = {"/xc/withdraw"}, description = "提现管理模块")
public class XcWithdrawController {
    @Resource
    private XcWithdrawService xcWithdrawService;

    @ApiOperation(value = "新增提现申请", notes = "新增提现申请")
    @RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
    @OperatLog(function = "新增",module = "提现管理",content = "新增提现记录")
    public Result add(@RequestBody XcWithdraw xcWithdraw) {
        Date date=new Date();
        xcWithdraw.setCreateTime(date);
        xcWithdraw.setUpdateTime(date);
        xcWithdraw.setIsDelete(false);
        xcWithdrawService.save(xcWithdraw);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcWithdraw);
        return result;
    }

    @ApiOperation(value = "删除提现记录", notes = "删除提现记录")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    @OperatLog(function = "删除",module = "提现管理",content = "逻辑删除提现记录")
    public Result delete(@RequestParam Long id) {
        XcWithdraw xcWithdraw=new XcWithdraw();
        xcWithdraw.setId(id);
        xcWithdraw.setIsDelete(true);
        xcWithdrawService.update(xcWithdraw);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "审核提现", notes = "审核提现")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    @OperatLog(function = "修改",module = "提现管理",content = "修改提现记录")
    public Result update(@RequestBody XcWithdraw xcWithdraw) {
        xcWithdrawService.update(xcWithdraw);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcWithdraw);
        return result;
    }

    @ApiOperation(value = "获取提现详情", notes = "获取提现详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST,RequestMethod.GET})
    public Result detail(@RequestParam Long id) {
        XcWithdraw xcWithdraw = xcWithdrawService.findById(id);
        return ResultGenerator.genSuccessResult(xcWithdraw);
    }

    @ApiOperation(value = "分页模糊查询提现记录", notes = "分页模糊查询提现记录")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,
                       @RequestParam(defaultValue="20",required=false) Integer size,
                       @RequestBody(required =false) XcWithdraw xcWithdraw) {
        return xcWithdrawService.list(page,size,xcWithdraw);
    }
}
