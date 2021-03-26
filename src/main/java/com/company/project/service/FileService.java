package com.company.project.service;

import com.company.project.core.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {

    Result uploadSingle(MultipartFile file);

    Result downloadImage(String imageName, HttpServletRequest request, HttpServletResponse response);

    Result showPhoto(String path, HttpServletResponse response);
}
