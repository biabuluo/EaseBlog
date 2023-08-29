package com.biabuluo.controller;

import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.Article;
import com.biabuluo.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "博客", description = "博客相关接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //获取热度文章列表
    @GetMapping("/getHotArticleList")
    @ApiOperation(value = "热度文章列表", notes = "获取一页十条最热博文信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "无参")
    )
    public ResponseResult getHotArticleList(){
        //service查询热门文章
        return articleService.hotArticleList();
    }

    //获取首页或者分类文章列表
    @GetMapping("/getArticleList")
    @ApiOperation(value = "文章列表", notes = "获取博文列表，参数形式：QueryString")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数"),
            @ApiImplicitParam(name = "pageSize", value = "一页的量"),
            @ApiImplicitParam(name = "categoryId", value = "分类Id，为null查询所有")
    })
    public ResponseResult getArticleList(@RequestParam Integer pageNum, @RequestParam Integer pageSize, Long categoryId){
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    //获取文章详情
    @GetMapping("/getArticleDetails/{id}")
    @ApiOperation(value = "文章详情", notes = "获取文章信息，参数形式：路径参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章id"),
    })
    public ResponseResult getArticleDetails(@PathVariable Long id){
        return articleService.ArticleDetails(id);
    }


    //更新文章浏览量
    @PutMapping("/updateArticleViewCount/{id}")
    @ApiOperation(value = "文章浏览量", notes = "更新文章浏览量，参数形式：路径参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章id"),
    })
    public ResponseResult updateArticleViewCount(@PathVariable Long id){
        return articleService.updateArticleViewCount(id);
    }
}
