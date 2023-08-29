package com.biabuluo.exception;

import com.biabuluo.enums.AppHttpCodeEnum;

/**
 * @author 小宇
 * @date 2023-08-27:15:03
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public class SystemException extends RuntimeException{
    private int code;
    private String msg;
    public int getCode(){
        return code;
    }
    public String msg(){
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum){
        super((httpCodeEnum.getMsg()));
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}
