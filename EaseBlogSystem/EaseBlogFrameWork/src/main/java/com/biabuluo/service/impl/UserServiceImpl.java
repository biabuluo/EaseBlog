package com.biabuluo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.User;
import com.biabuluo.domain.vo.UserInfoVo;
import com.biabuluo.enums.AppHttpCodeEnum;
import com.biabuluo.exception.SystemException;
import com.biabuluo.mapper.UserMapper;
import com.biabuluo.service.UserService;
import com.biabuluo.utils.BeanCopyUtil;
import com.biabuluo.utils.SecurityContextUtil;
import io.jsonwebtoken.lang.Strings;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-08-27 23:02:50
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    //获取用户userId
    @Override
    public ResponseResult UserInfo() {
        //获取当前用户id
        //根据用户ID查询用户信息
        User user = SecurityContextUtil.getLoginUser().getUser();
        //封装成UserInfo
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    //修改用户信息
    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return null;
    }


    //用户注册
    @Override
    public RequestBody userRegister(User user) {
        //对数据非空判断
        if(!StringUtils.hasText(user.getUserName()))
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        if(!StringUtils.hasText(user.getPassword()))
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        if(!StringUtils.hasText(user.getNickName()))
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        if(!StringUtils.hasText(user.getEmail()))
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        //数据是否存在判断
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        if(emailExist(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        //对密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //存入数据库
        save(user);
        return null;
    }

    private boolean emailExist(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        return count(queryWrapper)>0;
    }

    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNickName, nickName);
        return count(queryWrapper)>0;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        return count(queryWrapper)>0;
    }
}

