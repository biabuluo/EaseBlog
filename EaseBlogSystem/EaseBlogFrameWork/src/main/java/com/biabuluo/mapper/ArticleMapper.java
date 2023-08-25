package com.biabuluo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biabuluo.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 小宇
 * @date 2023-08-25:22:50
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description: 文章表mapper接口
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
