package com.biabuluo.controller;

import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.dto.LoginUserDto;
import com.biabuluo.domain.entity.User;
import com.biabuluo.enums.AppHttpCodeEnum;
import com.biabuluo.exception.SystemException;
import com.biabuluo.service.LoginService;
import com.biabuluo.utils.BeanCopyUtil;
import io.jsonwebtoken.lang.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小宇
 * @date 2023-08-26:22:04
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@RestController
@Api(tags = "登录", description = "登录相关接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "参数：body")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "一个User实体:传入userName+password")
    })
    public ResponseResult login(@RequestBody LoginUserDto loginUserDto){
        User user = BeanCopyUtil.copyBean(loginUserDto, User.class);
        if(!Strings.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        return loginService.login(user);
    }


    @PostMapping("/logout")
    @ApiOperation(value = "登录接口", notes = "参数：无参， 需要token")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
