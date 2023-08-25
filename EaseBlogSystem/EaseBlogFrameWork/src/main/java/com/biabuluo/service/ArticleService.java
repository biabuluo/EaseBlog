package com.biabuluo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.Article;

/**
 * @author 小宇
 * @date 2023-08-25:22:51
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description: 文章表service接口
 *
 */
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();
}
