package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcFeedPetRecord;
import com.company.project.service.XcFeedPetRecordService;
import com.company.project.core.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

/**
* Created by CodeGenerator on 2020/12/14.
*/
@RestController
@RequestMapping("/xc/feed/pet/record")
@Api(tags = {"/xc/feed/pet/record"}, description = "宠物养护记录管理模块")
public class XcFeedPetRecordController {
    @Resource
    private XcFeedPetRecordService xcFeedPetRecordService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
    public Result add(@RequestBody XcFeedPetRecord xcFeedPetRecord) {
        xcFeedPetRecord.setCreateTime(new Date());
        xcFeedPetRecord.setIsDelete(false);
        xcFeedPetRecordService.save(xcFeedPetRecord);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcFeedPetRecord);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    public Result delete(@RequestParam Long id) {
        XcFeedPetRecord xcFeedPetRecord=new XcFeedPetRecord();
        xcFeedPetRecord.setId(id);
        xcFeedPetRecord.setIsDelete(true);
        xcFeedPetRecordService.update(xcFeedPetRecord);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    public Result update(@RequestBody XcFeedPetRecord xcFeedPetRecord) {
        xcFeedPetRecord.setUpdateTime(new Date());
        xcFeedPetRecordService.update(xcFeedPetRecord);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcFeedPetRecord);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST,RequestMethod.GET})
    public Result detail(@RequestParam Long id) {
        XcFeedPetRecord xcFeedPetRecord = xcFeedPetRecordService.findById(id);
        return ResultGenerator.genSuccessResult(xcFeedPetRecord);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,@RequestParam(defaultValue="20",required=false) Integer size, @RequestBody(required =false) XcFeedPetRecord xcFeedPetRecord) {
        PageHelper.startPage(page, size);
        xcFeedPetRecord.setIsDelete(false);
        List<XcFeedPetRecord> list = xcFeedPetRecordService.findByModel(xcFeedPetRecord);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
