package com.biabuluo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biabuluo.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-26 14:50:14
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}

