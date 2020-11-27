package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UploadController
 * @Description //TODO
 * @Author cjm
 * @Date 2020/11/27 17:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("/xc/file")
@Api(tags = {"/xc/file"},description="文件上传模块")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "上传单个文件", notes = "上传单个文件")
    @RequestMapping(value = "/uploadSingle", method = {RequestMethod.POST,RequestMethod.GET})
    public Result uploadSingle(MultipartFile file){
        return fileService.uploadSingle(file);
    }

    @ApiOperation(value = "单个文件下载", notes = "单个文件下载")
    @RequestMapping(value = "/downloadImage", method = {RequestMethod.POST,RequestMethod.GET})
    public Result downloadImage(@RequestParam String imageName, HttpServletRequest request, HttpServletResponse response){
        return fileService.downloadImage(imageName,request,response);
    }


























}
