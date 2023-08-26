package com.biabuluo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.biabuluo.domain.entity.LoginUser;
import com.biabuluo.domain.entity.User;
import com.biabuluo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 小宇
 * @date 2023-08-26:22:28
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);
        //判断是否查到用户， 没有抛出异常
        if(Objects.isNull(user))
            throw new RuntimeException("user dose not exist!");
        //返回用户信息
        LoginUser loginUser = new LoginUser(user);
        //todo 返回权限信息

        return loginUser;
    }
}
