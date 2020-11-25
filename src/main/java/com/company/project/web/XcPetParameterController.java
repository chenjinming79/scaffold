package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcPetParameter;
import com.company.project.model.XcUser;
import com.company.project.service.XcPetParameterService;
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
* Created by CodeGenerator on 2020/11/24.
*/
@RestController
@RequestMapping("/xc/pet/parameter")
@Api(tags = {"/xc/pet/parameter"}, description = "星宠参数管理模块")
public class XcPetParameterController {
    @Resource
    private XcPetParameterService xcPetParameterService;

    @ApiOperation(value = "新增星宠参数", notes = "新增星宠参数")
    @RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
    public Result add(@RequestBody XcPetParameter xcPetParameter) {
        Date date=new Date();
        xcPetParameter.setCreateTime(date);
        xcPetParameter.setUpdateTime(date);
        xcPetParameter.setIsDelete(false);
        xcPetParameterService.save(xcPetParameter);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcPetParameter);
        return result;
    }

    @ApiOperation(value = "逻辑删除星宠参数", notes = "逻辑删除星宠参数")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    public Result delete(@RequestParam Long id) {
        XcPetParameter xcPetParameter=new XcPetParameter();
        xcPetParameter.setId(id);
        xcPetParameter.setIsDelete(true);
        xcPetParameterService.update(xcPetParameter);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改星宠参数", notes = "修改星宠参数")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    public Result update(@RequestBody XcPetParameter xcPetParameter) {
        xcPetParameterService.update(xcPetParameter);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcPetParameter);
        return result;
    }

    @ApiOperation(value = "获取单个星宠参数详情", notes = "获取单个星宠参数详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST,RequestMethod.GET})
    public Result detail(@RequestParam Long id) {
        XcPetParameter xcPetParameter = xcPetParameterService.findById(id);
        return ResultGenerator.genSuccessResult(xcPetParameter);
    }

    @ApiOperation(value = "分页模糊查询查询会员", notes = "分页模糊查询查询会员")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,@RequestParam(defaultValue="20",required=false) Integer size, @RequestBody(required =false) XcPetParameter xcPetParameter) {
        PageHelper.startPage(page, size);
        xcPetParameter.setIsDelete(false);
        List<XcPetParameter> list = xcPetParameterService.findByModel(xcPetParameter);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
