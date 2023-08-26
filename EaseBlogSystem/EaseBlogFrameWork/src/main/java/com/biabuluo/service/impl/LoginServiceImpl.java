package com.biabuluo.service.impl;

import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.LoginUser;
import com.biabuluo.domain.entity.User;
import com.biabuluo.domain.vo.LoginUserVo;
import com.biabuluo.domain.vo.UserInfoVo;
import com.biabuluo.service.LoginService;
import com.biabuluo.utils.BeanCopyUtil;
import com.biabuluo.utils.JwtUtil;
import com.biabuluo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author 小宇
 * @date 2023-08-26:22:11
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public ResponseResult login(User user) {
        //构造Authentication对象
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        //调用providerManager实现认证
        Authentication authenticate
                = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //判断是否认证成功
        if(Objects.isNull(authenticate))
            throw new RuntimeException("用户信息错误！");
        //获取Userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long userId = loginUser.getUser().getId();
        String jwt = JwtUtil.createJWT(userId.toString());

        //用户信息存入redis
        redisCache.setCacheObject("EaseBlog_Login"+userId, loginUser);
        //User信息+token返回
        LoginUserVo loginUserVo = new LoginUserVo();
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(loginUser.getUser(), UserInfoVo.class);
        loginUserVo.setUserInfoVo(userInfoVo);
        return ResponseResult.okResult(loginUserVo);
    }
}
