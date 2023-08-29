package com.biabuluo.controller;

import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.dto.RegisterUserDto;
import com.biabuluo.domain.dto.UpdateUserDto;
import com.biabuluo.domain.entity.User;
import com.biabuluo.service.UserService;
import com.biabuluo.utils.BeanCopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "用户", description = "用户信息相关接口")
public class UserController {
    @Autowired
    private UserService userService;

    //获取用户信息
    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息", notes = "无参，需要token")
    public ResponseResult getUserInfo(){
        return userService.UserInfo();
    }

    //编辑用户信息
    @PutMapping("/setUserInfo")
    @ApiOperation(value = "修改用户信息", notes = "body参数，需要token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "一个user实体")
    })
    public ResponseResult updateUserInfo(@RequestBody UpdateUserDto updateUserDto){
        User user = BeanCopyUtil.copyBean(updateUserDto, User.class);
        return userService.updateUserInfo(user);
    }


    //用户注册
    @PostMapping("/register")
    @ApiOperation(value = "注册", notes = "body参数，不需要token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "一个user实体")
    })
    public RequestBody userRegister(@RequestBody RegisterUserDto registerUserDto){
        User user = BeanCopyUtil.copyBean(registerUserDto, User.class);
        return userService.userRegister(user);
    }

}
