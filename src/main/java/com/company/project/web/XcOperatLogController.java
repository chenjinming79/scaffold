package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcOperatLog;
import com.company.project.service.XcOperatLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
* Created by CodeGenerator on 2020/12/05.
*/
@RestController
@RequestMapping("/xc/operat/log")
@Api(tags = {"/xc/operat/log"}, description = "操作日志管理模块")
public class XcOperatLogController {
    @Resource
    private XcOperatLogService xcOperatLogService;

    @ApiOperation(value = "新增操作日志", notes = "新增操作日志")
    @RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
    public Result add(@RequestBody XcOperatLog xcOperatLog) {
        xcOperatLog.setCreateTime(new Date());
        xcOperatLog.setIsDelete(false);
        xcOperatLogService.save(xcOperatLog);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcOperatLog);
        return result;
    }

    @ApiOperation(value = "逻辑删除操作日志", notes = "逻辑删除操作日志")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    public Result delete(@RequestParam Long id) {
        XcOperatLog xcOperatLog=new XcOperatLog();
        xcOperatLog.setId(id);
        xcOperatLog.setIsDelete(true);
        xcOperatLogService.update(xcOperatLog);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改操作日志", notes = "修改操作日志")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    public Result update(@RequestBody XcOperatLog xcOperatLog) {
        xcOperatLog.setUpdateTime(new Date());
        xcOperatLogService.update(xcOperatLog);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcOperatLog);
        return result;
    }

    @ApiOperation(value = "获取单个操作日志详情", notes = "获取单个操作日志详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST,RequestMethod.GET})
    public Result detail(@RequestParam Long id) {
        XcOperatLog xcOperatLog = xcOperatLogService.findById(id);
        return ResultGenerator.genSuccessResult(xcOperatLog);
    }

    @ApiOperation(value = "分页查询操作日志", notes = "分页查询操作日志")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,
                       @RequestParam(defaultValue="20",required=false) Integer size,
                       @RequestBody(required =false) XcOperatLog xcOperatLog) {
        return xcOperatLogService.list(page,size,xcOperatLog);
    }
}
