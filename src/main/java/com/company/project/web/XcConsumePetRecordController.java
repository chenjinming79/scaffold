package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcConsumePetRecord;
import com.company.project.service.XcConsumePetRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2020/12/12.
*/
@RestController
@RequestMapping("/xc/consume/pet/record")
@Api(tags = {"/xc/consume/pet/record"}, description = "管理模块")
public class XcConsumePetRecordController {
    @Resource
    private XcConsumePetRecordService xcConsumePetRecordService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
    public Result add(@RequestBody XcConsumePetRecord xcConsumePetRecord) {
        xcConsumePetRecord.setCreateTime(new Date());
        xcConsumePetRecord.setIsDelete(false);
        xcConsumePetRecordService.save(xcConsumePetRecord);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcConsumePetRecord);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    public Result delete(@RequestParam Long id) {
        XcConsumePetRecord xcConsumePetRecord=new XcConsumePetRecord();
        xcConsumePetRecord.setId(id);
        xcConsumePetRecord.setIsDelete(true);
        xcConsumePetRecordService.update(xcConsumePetRecord);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    public Result update(@RequestBody XcConsumePetRecord xcConsumePetRecord) {
        xcConsumePetRecord.setUpdateTime(new Date());
        xcConsumePetRecordService.update(xcConsumePetRecord);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcConsumePetRecord);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST,RequestMethod.GET})
    public Result detail(@RequestParam Long id) {
        XcConsumePetRecord xcConsumePetRecord = xcConsumePetRecordService.findById(id);
        return ResultGenerator.genSuccessResult(xcConsumePetRecord);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,@RequestParam(defaultValue="20",required=false) Integer size,
                       @RequestBody(required = false) XcConsumePetRecord xcConsumePetRecord) {
        return xcConsumePetRecordService.list(page,size,xcConsumePetRecord);
    }
}
