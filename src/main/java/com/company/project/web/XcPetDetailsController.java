package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcPetDetails;
import com.company.project.service.XcPetDetailsService;
import com.company.project.core.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

/**
* Created by CodeGenerator on 2020/12/09.
*/
@RestController
@RequestMapping("/xc/pet/details")
@Api(tags = {"/xc/pet/details"}, description = "管理模块")
public class XcPetDetailsController {
    @Resource
    private XcPetDetailsService xcPetDetailsService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
    public Result add(@RequestBody XcPetDetails xcPetDetails) {
        xcPetDetails.setCreateTime(new Date());
        xcPetDetails.setIsDelete(false);
        xcPetDetailsService.save(xcPetDetails);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcPetDetails);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    public Result delete(@RequestParam Long id) {
        XcPetDetails xcPetDetails=new XcPetDetails();
        xcPetDetails.setId(id);
        xcPetDetails.setIsDelete(true);
        xcPetDetailsService.update(xcPetDetails);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    public Result update(@RequestBody XcPetDetails xcPetDetails) {
        xcPetDetails.setUpdateTime(new Date());
        xcPetDetailsService.update(xcPetDetails);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcPetDetails);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST,RequestMethod.GET})
    public Result detail(@RequestParam Long id) {
        XcPetDetails xcPetDetails = xcPetDetailsService.findById(id);
        return ResultGenerator.genSuccessResult(xcPetDetails);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,@RequestParam(defaultValue="20",required=false) Integer size, @RequestBody(required =false) XcPetDetails xcPetDetails) {
        PageHelper.startPage(page, size);
        xcPetDetails.setIsDelete(false);
        List<XcPetDetails> list = xcPetDetailsService.findByModel(xcPetDetails);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
