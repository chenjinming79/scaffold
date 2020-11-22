package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcAnnounCement;
import com.company.project.service.XcAnnounCementService;
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
* Created by CodeGenerator on 2020/11/22.
*/
@RestController
@RequestMapping("/xc/announ/cement")
@Api(tags = {"/xc/announ/cement"}, description = "公告管理模块")
public class XcAnnounCementController {
    @Resource
    private XcAnnounCementService xcAnnounCementService;

    @PostMapping("/add")
    @ApiOperation(value = "新增公告", notes = "新增公告")
    public Result add(@RequestBody XcAnnounCement xcAnnounCement) {
        Date date=new Date();
        xcAnnounCement.setCreateTime(date);
        xcAnnounCement.setUpdateTime(date);
        xcAnnounCement.setIsDelete(false);
        xcAnnounCementService.save(xcAnnounCement);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcAnnounCement);
        return result;
    }

    @PostMapping("/delete")
    @ApiOperation(value = "逻辑删除公告", notes = "逻辑删除公告")
    public Result delete(@RequestParam Long id) {
        XcAnnounCement xcAnnounCement=new XcAnnounCement();
        xcAnnounCement.setId(id);
        xcAnnounCement.setIsDelete(true);
        xcAnnounCementService.update(xcAnnounCement);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改公告", notes = "修改公告")
    public Result update(@RequestBody XcAnnounCement xcAnnounCement) {
        xcAnnounCementService.update(xcAnnounCement);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcAnnounCement);
        return result;
    }

    @GetMapping("/detail")
    @ApiOperation(value = "获取公告详情", notes = "获取公告详情")
    public Result detail(@RequestParam Long id) {
        XcAnnounCement xcAnnounCement = xcAnnounCementService.findById(id);
        return ResultGenerator.genSuccessResult(xcAnnounCement);
    }

    @PostMapping("/findByModal")
    @ApiOperation(value = "分页查询公告", notes = "分页查询公告")
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,@RequestParam(defaultValue="20",required=false) Integer size, @RequestBody(required =false) XcAnnounCement xcAnnounCement) {
        PageHelper.startPage(page, size);
        xcAnnounCement.setIsDelete(false);
        List<XcAnnounCement> list = xcAnnounCementService.findByModel(xcAnnounCement);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
