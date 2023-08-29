package com.biabuluo.service.impl;

import com.biabuluo.domain.ResponseResult;
import com.biabuluo.enums.AppHttpCodeEnum;
import com.biabuluo.exception.SystemException;
import com.biabuluo.service.UploadService;
import com.biabuluo.utils.PathUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author 小宇
 * @date 2023-08-28:19:19
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Service
@ConfigurationProperties(prefix = "oss")
@Data
public class OssUploadServiceImpl implements UploadService {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String domain;

    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        //判断文件类型+文件大小（配置文件已经进行限制）
        if(img.getSize()>1048576)
            throw new SystemException(AppHttpCodeEnum.FILE_SIZE_ERROR);
        //获取原始文件名判断
        String originalFilename = img.getOriginalFilename();
        if(!originalFilename.endsWith(".png") || !originalFilename.endsWith(".jpg")){
           throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        //判断通过上传文件到OSS
        //生成存放路径+文件名：
        String fileName = PathUtil.generateFilePath(originalFilename);
        String url = ossTest(img, fileName);
        return ResponseResult.okResult(url);
    }


    //上传文件到OSS函数 返回外链
    public String ossTest(MultipartFile imgFile, String fileName){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        //        String accessKey = "wubfppxLHBwJD7zqNK4gvaTapKZCB2mSbTfdRgg5";
        //        String secretKey = "gZ2qJgl5HMwjrZ7THzlL6SNwWinbwmwfhigSDgsB";
        //        String bucket = "easeblog-oss";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        try {
        //            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
        //            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);

            InputStream inputStream = imgFile.getInputStream();


            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return domain+key;
            } catch (QiniuException ex) {
                ex.printStackTrace();
                if (ex.response != null) {
                    System.err.println(ex.response);
                    try {
                        String body = ex.response.toString();
                        System.err.println(body);
                    } catch (Exception ignored) {
                    }
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return null;
    }
}
