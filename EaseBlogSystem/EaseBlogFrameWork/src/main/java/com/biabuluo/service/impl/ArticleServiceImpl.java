package com.biabuluo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biabuluo.constans.SystemConstants;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.Article;
import com.biabuluo.domain.vo.ArticleDetailsVo;
import com.biabuluo.domain.vo.ArticleListVo;
import com.biabuluo.domain.vo.HotArticleVo;
import com.biabuluo.domain.vo.PageVo;
import com.biabuluo.mapper.ArticleMapper;
import com.biabuluo.service.ArticleService;
import com.biabuluo.service.CategoryService;
import com.biabuluo.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 小宇
 * @date 2023-08-25:22:52
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description: 文章表service接口实现类
 *
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;


    //查询文章详情信息
    @Override
    public ResponseResult ArticleDetails(Long id) {
        //更具id查文章
        Article article = getById(id);
        //更具cid查分类名
        article.setCategoryName(categoryService.getById(article.getCategoryId()).getName());
        //返回
        ArticleDetailsVo articleDetailsVo = BeanCopyUtil.copyBean(article, ArticleDetailsVo.class);
        return ResponseResult.okResult(articleDetailsVo);
    }

    //查询热门文章
    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //正式文章status=0
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //热度降序排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //十条文章
        Page<Article> page = new Page<>(SystemConstants.HOTARTICLE_PAGE, SystemConstants.HOTARTICLE_NUM);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();
        //响应给前端使用VO->方法使用Bean拷贝
        List<HotArticleVo> hotArticleVos = BeanCopyUtil.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(hotArticleVos);
    }

    //查询文章列表
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //分页查询
        //查询条件：categoryId可以为空
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0, Article::getCategoryId, categoryId);
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //文章已发布
        //置顶文章（对isTop排序）
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);
        List<Article> articles = page.getRecords();
        //查询分类名称
        articles = articles.stream().map(o1-> o1.setCategoryName(categoryService.getById(o1.getCategoryId()).getName()))
                .collect(Collectors.toList());
        //封装查询结果vo
        List<ArticleListVo> articleListVos = BeanCopyUtil.copyBeanList(articles, ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
}
