package com.biabuluo.service;

import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.User;

/**
 * @author 小宇
 * @date 2023-08-26:22:10
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public interface LoginService {

    ResponseResult login(User user);

}
