package com.biabuluo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 小宇
 * @date 2023-08-28:21:47
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLog {
    String businessname();
}
