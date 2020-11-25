package com.company.project.web;

import com.company.project.core.Page;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcSysUser;
import com.company.project.service.XcSysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2020/11/24.
*/
@RestController
@RequestMapping("/xc/sys/user")
@Api(tags = {"/xc/sys/user"}, description = "后台用户管理模块")
public class XcSysUserController {
    @Resource
    private XcSysUserService xcSysUserService;

    @ApiOperation(value = "新增后台用户", notes = "新增后台用户")
    @RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
    public Result add(@RequestBody XcSysUser xcSysUser) {
        Date date=new Date();
        xcSysUser.setCreateTime(date);
        xcSysUser.setUpdateTime(date);
        xcSysUser.setIsDelete(false);
        xcSysUserService.save(xcSysUser);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcSysUser);
        return result;
    }

    @ApiOperation(value = "逻辑删除后台用户", notes = "逻辑删除后台用户")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    public Result delete(@RequestParam Long id) {
        XcSysUser xcSysUser=new XcSysUser();
        xcSysUser.setId(id);
        xcSysUser.setIsDelete(true);
        xcSysUserService.update(xcSysUser);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改后台用户", notes = "修改后台用户")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    public Result update(@RequestBody XcSysUser xcSysUser) {
        xcSysUserService.update(xcSysUser);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcSysUser);
        return result;
    }

    @ApiOperation(value = "获取后台用户详情", notes = "获取后台用户详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST,RequestMethod.GET})
    public Result detail(@RequestParam Long id) {
        XcSysUser xcSysUser = xcSysUserService.findById(id);
        return ResultGenerator.genSuccessResult(xcSysUser);
    }
}
