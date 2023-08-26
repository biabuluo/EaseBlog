package com.biabuluo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biabuluo.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-26 21:45:40
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

