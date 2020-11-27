package com.company.project.service;

import com.company.project.core.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    Result uploadSingle(MultipartFile file);
}
