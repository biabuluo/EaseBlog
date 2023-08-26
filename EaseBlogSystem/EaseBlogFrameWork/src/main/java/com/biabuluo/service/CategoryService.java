package com.biabuluo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-08-26 14:50:15
 */
public interface CategoryService extends IService<Category> {

    ResponseResult categoryList();
}

