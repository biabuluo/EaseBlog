package com.biabuluo.runner;

import com.biabuluo.constans.SystemConstants;
import com.biabuluo.domain.entity.Article;
import com.biabuluo.mapper.ArticleMapper;
import com.biabuluo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 小宇
 * @date 2023-08-29:19:25
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description: 浏览量计数器的初始化器
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息 id viewCount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> collect = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        //map存储到redis中
        redisCache.setCacheMap(SystemConstants.REDIS_VIEWCOUNT_KEY, collect);
    }
}
