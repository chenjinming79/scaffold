package com.company.project.web;
import com.company.project.aop.OperatLog;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcSysConfig;
import com.company.project.service.XcSysConfigService;
import com.company.project.core.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

/**
* Created by CodeGenerator on 2020/12/03.
*/
@RestController
@RequestMapping("/xc/sys/config")
@Api(tags = {"/xc/sys/config"}, description = "系统参数管理模块")
public class XcSysConfigController {
    @Resource
    private XcSysConfigService xcSysConfigService;

    @ApiOperation(value = "新增系统参数", notes = "新增系统参数")
    @OperatLog(function = "新增",module = "系统参数管理",content = "新增系统参数")
    @RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
    public Result add(@RequestBody XcSysConfig xcSysConfig) {
        xcSysConfig.setCreateTime(new Date());
        xcSysConfig.setIsDelete(false);
        xcSysConfigService.save(xcSysConfig);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcSysConfig);
        return result;
    }

    @ApiOperation(value = "逻辑删除系统配置", notes = "逻辑删除系统配置")
    @OperatLog(function = "删除",module = "系统参数管理",content = "删除系统参数")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    public Result delete(@RequestParam Long id) {
        XcSysConfig xcSysConfig=new XcSysConfig();
        xcSysConfig.setId(id);
        xcSysConfig.setIsDelete(true);
        xcSysConfigService.update(xcSysConfig);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改系统配置", notes = "修改系统配置")
    @OperatLog(function = "修改",module = "系统参数管理",content = "修改提现记录")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    public Result update(@RequestBody XcSysConfig xcSysConfig) {
        xcSysConfig.setUpdateTime(new Date());
        xcSysConfigService.update(xcSysConfig);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcSysConfig);
        return result;
    }

    @ApiOperation(value = "根据id查询系统配置", notes = "根据id查询系统配置")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST,RequestMethod.GET})
    public Result detail(@RequestParam Long id) {
        XcSysConfig xcSysConfig = xcSysConfigService.findById(id);
        return ResultGenerator.genSuccessResult(xcSysConfig);
    }

    @ApiOperation(value = "分页查询系统配置", notes = "分页查询系统配置")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,@RequestParam(defaultValue="20",required=false) Integer size, @RequestBody(required =false) XcSysConfig xcSysConfig) {
        PageHelper.startPage(page, size);
        xcSysConfig.setIsDelete(false);
        List<XcSysConfig> list = xcSysConfigService.findByModel(xcSysConfig);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
