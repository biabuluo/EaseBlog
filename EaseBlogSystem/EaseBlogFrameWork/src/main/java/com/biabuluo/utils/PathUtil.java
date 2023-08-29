package com.biabuluo.utils;

/**
 * @author 小宇
 * @date 2023-08-28:19:44
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 根据文件生成文件路径
 */
public class PathUtil {
    public static String generateFilePath(String fileName){
        //根据日期生成路径
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dataPath = sdf.format(new Date());
        //uuid作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //后缀和文件后缀一致
        int index = fileName.lastIndexOf(".");
        //test.jpg -> .jpg
        String fileType = fileName.substring(index);
        return new StringBuilder()
                .append(dataPath)
                .append(uuid)
                .append(fileType)
                .toString();
    }
}
