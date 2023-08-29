package com.biabuluo.controller;

import com.biabuluo.domain.ResponseResult;
import com.biabuluo.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小宇
 * @date 2023-08-26:14:55
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description: 文章分类列表
 */
@RestController
@RequestMapping("/category")
@Api(tags = "分类", description = "分类相关接口")
public class CategoryController {

    @Autowired

    private CategoryService categoryService;


    @GetMapping("/getCategoryList")
    @ApiOperation(value = "分类列表", notes = "获取分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "无参")
    })
    public ResponseResult getCategoryList(){
        return categoryService.categoryList();
    }
}
