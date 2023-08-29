package com.biabuluo.job;

import com.biabuluo.constans.SystemConstants;
import com.biabuluo.domain.entity.Article;
import com.biabuluo.service.ArticleService;
import com.biabuluo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 小宇
 * @date 2023-08-29:20:26
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description: 定时任务：将redis中的viewCount更新回数据库中
 */
@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;
    //涉及cron表达式:每隔5秒钟

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void testJob(){
        System.out.println("每隔5s执行定时任务: 将redis中ViewCount更新回数据库");
        //获取Redis中的viewCount中的map
        Map<String, Integer> cacheMap = redisCache.getCacheMap(SystemConstants.REDIS_VIEWCOUNT_KEY);
        //更新数据库
        Integer integer = articleService.updateBatchViewCount(cacheMap);
    }
}
