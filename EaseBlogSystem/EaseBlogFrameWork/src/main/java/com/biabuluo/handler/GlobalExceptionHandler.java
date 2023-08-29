package com.biabuluo.handler;

import com.biabuluo.domain.ResponseResult;
import com.biabuluo.enums.AppHttpCodeEnum;
import com.biabuluo.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 小宇
 * @date 2023-08-27:15:12
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description: 统一异常处理
 */

@RestControllerAdvice
//lombok注解：可以直接使用log
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseResult handlerSystemException(SystemException e){
        //打印异常信息
        log.error("出现系统异常！", e);
        //获取异常信息，存放在ResponseResult的msg属性
        ResponseResult result = ResponseResult.errorResult(e.getCode(), e.getMessage());
        //把ResponseResult作为返回值，要求到时候转换成json存入到响应体
        return result;
    }


    @ExceptionHandler(Exception.class)
    public ResponseResult handlerException(Exception e){
        //打印异常信息
        log.error("出现其它异常！", e);
        //获取异常信息，存放在ResponseResult的msg属性
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        //把ResponseResult作为返回值，要求到时候转换成json存入到响应体
        return result;
    }
}
