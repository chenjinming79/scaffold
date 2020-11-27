package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcAreaCode;
import com.company.project.service.XcAreaCodeService;
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
* Created by CodeGenerator on 2020/11/27.
*/
@RestController
@RequestMapping("/xc/area/code")
@Api(tags = {"/xc/area/code"}, description = "区号管理模块")
public class XcAreaCodeController {
    @Resource
    private XcAreaCodeService xcAreaCodeService;

    @ApiOperation(value = "新增区号", notes = "新增区号")
    @RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
    public Result add(@RequestBody XcAreaCode xcAreaCode) {
        xcAreaCode.setIsDelete(false);
        xcAreaCodeService.save(xcAreaCode);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcAreaCode);
        return result;
    }

    @ApiOperation(value = "逻辑删除区号", notes = "逻辑删除区号")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.GET})
    public Result delete(@RequestParam Long id) {
        XcAreaCode xcAreaCode=new XcAreaCode();
        xcAreaCode.setId(id);
        xcAreaCode.setIsDelete(true);
        xcAreaCodeService.update(xcAreaCode);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改区号", notes = "修改区号")
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    public Result update(@RequestBody XcAreaCode xcAreaCode) {
        xcAreaCodeService.update(xcAreaCode);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(xcAreaCode);
        return result;
    }

    @ApiOperation(value = "获取区号详情", notes = "获取区号详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST,RequestMethod.GET})
    public Result detail(@RequestParam Long id) {
        XcAreaCode xcAreaCode = xcAreaCodeService.findById(id);
        return ResultGenerator.genSuccessResult(xcAreaCode);
    }

    @ApiOperation(value = "分页模糊查询区号", notes = "分页模糊查询区号")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(@RequestParam(defaultValue="1",required=false) Integer page,@RequestParam(defaultValue="20",required=false) Integer size, @RequestBody(required =false) XcAreaCode xcAreaCode) {
        PageHelper.startPage(page, size);
        xcAreaCode.setIsDelete(false);
        List<XcAreaCode> list = xcAreaCodeService.findByModel(xcAreaCode);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
