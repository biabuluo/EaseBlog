package com.biabuluo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.Article;
import com.biabuluo.mapper.ArticleMapper;
import com.biabuluo.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小宇
 * @date 2023-08-25:22:52
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description: 文章表service接口实现类
 *
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    //查询热门文章
    @Override
    public ResponseResult hotArticleList() {

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //正式文章status=0
        queryWrapper.eq(Article::getStatus, 0);
        //热度降序排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //十条文章
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();

        return ResponseResult.okResult(articles);
    }
}
