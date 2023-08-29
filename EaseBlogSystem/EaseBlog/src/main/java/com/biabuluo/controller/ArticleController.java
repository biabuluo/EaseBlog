package com.biabuluo.controller;

import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.Article;
import com.biabuluo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 小宇
 * @date 2023-08-25:22:56
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //获取热度文章列表
    @GetMapping("/getHotArticleList")
    public ResponseResult getHotArticleList(){
        //service查询热门文章
        return articleService.hotArticleList();
    }

    //获取首页或者分类文章列表
    @GetMapping("/getArticleList")
    public ResponseResult getArticleList(@RequestParam Integer pageNum, @RequestParam Integer pageSize, Long categoryId){
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    //获取文章详情
    @GetMapping("/getArticleDetails/{id}")
    public ResponseResult getArticleDetails(@PathVariable Long id){
        return articleService.ArticleDetails(id);
    }


    //更新文章浏览量
    @PutMapping("/updateArticleViewCount/{id}")
    public ResponseResult updateArticleViewCount(@PathVariable Long id){
        return articleService.updateArticleViewCount(id);
    }
}
