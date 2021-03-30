package com.company.project.service.impl;

import com.company.project.constants.Constant;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.service.FileService;
import com.company.project.utils.ExportExcelUtil;
import com.company.project.utils.Logger;
import com.company.project.vo.ExportVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName FileServiceImpl
 * @Description //TODO
 * @Author cjm
 * @Date 2020/11/27 17:40
 * @Version 1.0
 **/
@Service
public class FileServiceImpl implements FileService {

    /*@Value("${file.url}")
    private String uploadDir;*/

    private static String uploadDir = Constant.OS_PREFIX;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @Override
    public Result uploadSingle(MultipartFile file) {
        if (file.isEmpty()) {
            return ResultGenerator.genFailResult(ResultCode.FILE_BULL_ERROR,"文件不能为空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        String filePath = uploadDir;
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            String path;
            /*if (){

            }else if (){

            }*/
            return ResultGenerator.genSuccessResult(dest.getPath());
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult(ResultCode.FILEUPLOAD_ERROR,"文件上传失败");
    }

    @Override
    public void export(HttpServletRequest request, HttpServletResponse response) {

        /*List<ExportVo> exportVoList = userMapper.selectExport();
        String[] titles = {"预约成功时间", "预约客人名称", "停车场名称", "停车场地址" , "车位名字"};
        try {
            ExportExcelUtil.export(response, "预约成功的用户信息.xlsx", titles, exportVoList);
        } catch (Exception e) {
            Logger.info(this, "=====》预约成功的用户信息" + e );
        }*/
    }

}
