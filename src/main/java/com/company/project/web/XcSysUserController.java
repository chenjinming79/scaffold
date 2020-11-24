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

    @PostMapping("/add")
    @ApiOperation(value = "新增后台用户", notes = "新增后台用户")
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

    @PostMapping("/delete")
    @ApiOperation(value = "逻辑删除后台用户", notes = "逻辑删除后台用户")
    public Result delete(@RequestParam Long id) {
        XcSysUser xcSysUser=new XcSysUser();
        xcSysUser.setId(id);
        xcSysUser.setIsDelete(true);
        xcSysUserService.update(xcSysUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改后台用户", notes = "修改后台用户")
    public Result update(@RequestBody XcSysUser xcSysUser) {
        xcSysUserService.update(xcSysUser);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcSysUser);
        return result;
    }

    @GetMapping("/detail")
    @ApiOperation(value = "获取后台用户详情", notes = "获取后台用户详情")
    public Result detail(@RequestParam Long id) {
        XcSysUser xcSysUser = xcSysUserService.findById(id);
        return ResultGenerator.genSuccessResult(xcSysUser);
    }

    @PostMapping("/findByModal")
    @ApiOperation(value = "分页查询后台用户", notes = "分页查询后台用户")
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,@RequestParam(defaultValue="20",required=false) Integer size, @RequestBody(required =false) XcSysUser xcSysUser) {
        PageHelper.startPage(page, size);
        xcSysUser.setIsDelete(false);
        List<XcSysUser> list = xcSysUserService.findByModel(xcSysUser);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
