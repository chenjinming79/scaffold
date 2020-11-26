package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcUser;
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
@Api(tags = {"/xc/withdraw"}, description = "体现记录管理模块")
public class XcWithdrawController {
    @Resource
    private XcWithdrawService xcWithdrawService;

    @ApiOperation(value = "新增提现申请", notes = "新增提现申请")
    @RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
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
    public Result delete(@RequestParam Long id) {
        XcWithdraw xcWithdraw=new XcWithdraw();
        xcWithdraw.setId(id);
        xcWithdraw.setIsDelete(true);
        xcWithdrawService.update(xcWithdraw);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "审核提现", notes = "审核提现")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
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

    @ApiOperation(value = "分页模糊查询查询会员", notes = "分页模糊查询查询会员")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,
                       @RequestParam(defaultValue="20",required=false) Integer size,
                       @RequestBody(required =false) XcWithdraw xcWithdraw) {
        return xcWithdrawService.list(page,size,xcWithdraw);
    }
}
