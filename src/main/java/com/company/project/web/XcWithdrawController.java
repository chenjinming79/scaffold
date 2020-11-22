package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcWithdraw;
import com.company.project.service.XcWithdrawService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2020/11/22.
*/
@RestController
@RequestMapping("/xc/withdraw")
@Api(tags = {"/xc/withdraw"}, description = "体现管理模块")
public class XcWithdrawController {
    @Resource
    private XcWithdrawService xcWithdrawService;

    @PostMapping("/add")
    @ApiOperation(value = "新增提现申请", notes = "新增提现申请")
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

    @PostMapping("/delete")
    @ApiOperation(value = "删除提现记录", notes = "删除提现记录")
    public Result delete(@RequestParam Long id) {
        XcWithdraw xcWithdraw=new XcWithdraw();
        xcWithdraw.setId(id);
        xcWithdraw.setIsDelete(true);
        xcWithdrawService.update(xcWithdraw);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ApiOperation(value = "审核提现", notes = "审核提现")
    public Result update(@RequestBody XcWithdraw xcWithdraw) {
        xcWithdrawService.update(xcWithdraw);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcWithdraw);
        return result;
    }

    @GetMapping("/detail")
    @ApiOperation(value = "获取提现详情", notes = "获取提现详情")
    public Result detail(@RequestParam Long id) {
        XcWithdraw xcWithdraw = xcWithdrawService.findById(id);
        return ResultGenerator.genSuccessResult(xcWithdraw);
    }

    @PostMapping("/findByModal")
    @ApiOperation(value = "分页查询提现详情", notes = "分页查询提现详情")
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,@RequestParam(defaultValue="20",required=false) Integer size, @RequestBody(required =false) XcWithdraw xcWithdraw) {
        PageHelper.startPage(page, size);
        xcWithdraw.setIsDelete(false);
        List<XcWithdraw> list = xcWithdrawService.findByModel(xcWithdraw);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
