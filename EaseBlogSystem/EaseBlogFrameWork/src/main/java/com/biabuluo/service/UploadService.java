package com.biabuluo.service;

import com.biabuluo.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 小宇
 * @date 2023-08-28:19:07
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
