package com.biabuluo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.User;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-08-27 23:02:49
 */
public interface UserService extends IService<User> {

    ResponseResult UserInfo();

    ResponseResult updateUserInfo(User user);

    RequestBody userRegister(User user);
}

