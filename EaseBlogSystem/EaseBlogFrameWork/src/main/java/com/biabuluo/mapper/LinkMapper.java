package com.biabuluo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biabuluo.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;

/**
 * 友链(Link)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-26 20:21:33
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}

