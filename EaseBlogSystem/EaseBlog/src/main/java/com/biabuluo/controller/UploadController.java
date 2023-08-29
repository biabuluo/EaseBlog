package com.biabuluo.controller;

import com.biabuluo.domain.ResponseResult;
import com.biabuluo.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 小宇
 * @date 2023-08-28:19:04
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */

@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/uploadImg")
    public ResponseResult uploadImg(@RequestBody MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
