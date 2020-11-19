package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcUser;
import com.company.project.service.XcUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/xc/user")
@Api(tags = {"/xc/user"}, description = "用户管理模块")
public class XcUserController {
    @Resource
    private XcUserService xcUserService;

    @PostMapping("/add")
    @ApiOperation(value = "新增用户", notes = "新增用户")
    public Result add(@RequestBody XcUser xcUser) {
        xcUserService.save(xcUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "逻辑删除用户", notes = "逻辑删除用户")
    public Result delete(@RequestParam Long id) {
        XcUser xcUser = new XcUser();
        xcUser.setId(id);
        xcUser.setIsDelete(true);
        xcUserService.update(xcUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改用户", notes = "修改用户")
    public Result update(@RequestBody XcUser xcUser) {
        xcUserService.update(xcUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取用户详情", notes = "获取用户详情")
    public Result detail(@RequestParam Long id) {
        XcUser xcUser = xcUserService.findById(id);
        return ResultGenerator.genSuccessResult(xcUser);
    }

    @PostMapping("/list")
    @ApiOperation(value = "获取用户详情", notes = "获取用户详情")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<XcUser> list = xcUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
