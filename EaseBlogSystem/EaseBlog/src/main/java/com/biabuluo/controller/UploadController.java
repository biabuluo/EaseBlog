package com.biabuluo.controller;

import com.biabuluo.domain.ResponseResult;
import com.biabuluo.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "文件上传", description = "文件上传（主要是头像上传）相关接口")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/uploadImg")
    @ApiOperation(value = "上传头像接口", notes = "参数Body， 文件类型， 需要token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "img", value = "jpg/png文件，大小不超过2M")
    })
    public ResponseResult uploadImg(@RequestBody MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
