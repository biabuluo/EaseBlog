package com.biabuluo.aspect;

import com.alibaba.fastjson.JSON;
import com.biabuluo.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 小宇
 * @date 2023-08-28:21:48
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */


@Aspect
@Component
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.biabuluo.annotation.SystemLog)")
    //确定切点：标识哪个注解对service的增强
    public void pt(){
    }
    //增强方法
    @Around("pt()")  //功能最强大的around通知
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable{
        //上面这个参数表示正在被增强的方法
        //目标方法调用前
        //获取增强方法的签名
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        //获取方法签名
//        String methodName = signature.getMethod().getName();
        Object proceed = null;
        try {
            handleBefore(joinPoint);
            proceed = joinPoint.proceed();
            handleAfter(proceed);
            //目标方法调用后
        } finally {
            //无论有没有异常都执行
            //lineSeparator：系统换行符
            log.info("============End============"+System.lineSeparator());
        }
        return proceed;
    }

    private void handleAfter(Object proceed) {
        log.info("Respon          : {}", JSON.toJSON(proceed));
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {
        //获取request对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //获取被增强方法的注解对象
        SystemLog systemLog = getSystemLog(joinPoint);

        log.info("===========start===========");
        log.info("URL             : {}", request.getRequestURL());
        log.info("Businessname         : {}", systemLog.businessname());
        log.info("HTTPMethod           : {}", request.getMethod());
        log.info("ClassMethod          : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), ((MethodSignature)joinPoint.getSignature()).getName());
        log.info("IP                   : {}", request.getRemoteHost());
        log.info("RequestArgs          : {}", JSON.toJSON(joinPoint.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(SystemLog.class);
    }
}
