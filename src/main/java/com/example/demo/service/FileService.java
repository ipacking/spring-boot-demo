package com.example.demo.service;

import com.example.demo.entity.base.CommonResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    CommonResult uploadFile(MultipartFile file) throws Exception;
}