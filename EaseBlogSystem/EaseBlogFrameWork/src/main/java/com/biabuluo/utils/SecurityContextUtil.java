package com.biabuluo.utils;

import com.biabuluo.domain.entity.LoginUser;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author 小宇
 * @date 2023-08-28:14:14
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description: 获取SecurityContext里的LoginUser
 */
public class SecurityContextUtil {

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     */
    public static LoginUser getLoginUser(){
        return (LoginUser) getAuthentication().getPrincipal();
    }


    /**
     * 是否管理员
     */
    public static Boolean isAdmin(){
        return true;
    }


    /**
     * 获取用户id
     */
    public static Long getUserId(){
        return getLoginUser().getUser().getId();
    }
}
