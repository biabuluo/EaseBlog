package com.biabuluo.controller;

import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.User;
import com.biabuluo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 小宇
 * @date 2023-08-28:15:15
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //获取用户信息
    @GetMapping("/getUserInfo")
    public ResponseResult getUserInfo(){
        return userService.UserInfo();
    }

    //编辑用户信息
    @PutMapping("/setUserInfo")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }


    //用户注册
    @PostMapping("/register")
    public RequestBody userRegister(@RequestBody User user){
        return userService.userRegister(user);
    }

}
