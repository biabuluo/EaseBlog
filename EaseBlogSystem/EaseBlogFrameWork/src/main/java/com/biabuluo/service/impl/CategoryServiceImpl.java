package com.biabuluo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biabuluo.constans.SystemConstants;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.Article;
import com.biabuluo.domain.entity.Category;
import com.biabuluo.domain.vo.CategoryListVo;
import com.biabuluo.mapper.CategoryMapper;
import com.biabuluo.service.ArticleService;
import com.biabuluo.service.CategoryService;
import com.biabuluo.utils.BeanCopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-08-26 14:50:15
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;
    @Override
    public ResponseResult categoryList() {
        //要求：需要查询所有已发布文章的categoryid去重
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleLambdaQueryWrapper);
        Set<Long> categoryIds = articleList.stream()
                .map(o -> o.getCategoryId())
                .collect(Collectors.toSet());

        List<Category> categories = listByIds(categoryIds);

        //获取其中状态正常的category
        categories = categories.stream()
                .filter(o -> o.getStatus().equals(SystemConstants.CATEGORY_NORMAL))
                .collect(Collectors.toList());

        //创建VO对象
        List<CategoryListVo> categoryListVos = BeanCopyUtil.copyBeanList(categories, CategoryListVo.class);
        return ResponseResult.okResult(categoryListVos);
    }
}

