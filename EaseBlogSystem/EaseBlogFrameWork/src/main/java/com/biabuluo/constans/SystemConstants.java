package com.biabuluo.constans;

/**
 * @author 小宇
 * @date 2023-08-26:14:08
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description: 项目常量类
 */
public class SystemConstants {
    //文章草稿
    public static final int ARTICLA_ATATUS_DRAFT = 1;
    //正常发布文章
    public static final int ARTICLE_STATUS_NORMAL = 0;
    //查询热门文章页码
    public static final int HOTARTICLE_PAGE = 1;
    //查询热门文章数量
    public static final int HOTARTICLE_NUM = 10;



    //文章分类正常
    public static final String CATEGORY_NORMAL = "0";
    //文章是否允许评论
    public static final String ARTICLE_CAN_COMMENT = "1";
    public static final String ARTICLE_CANNOT_COMMENT = "0";


    //友链状态
    public static final String LINK_PASS = "0";
    public static final String LINK_FAIL = "1";
    public static final String LINK_UNSURE = "2";


    //用户状态
    public static final String USER_TYPE_ADMIN = "1";
    public static final String USER_TYPE_ORDINARY = "0";
    //用户账号状态
    public static final String USER_STATUS_NORMAL = "0";
    public static final String USER_STATUS_FORBID = "1";


    //评论信息: 根评论
    public static final int COMMENT_ISROOT = -1;
    //友链评论
    public static final String COMMENT_LINK = "1";
    //普通文章评论
    public static final String COMMENT_NORMAL = "0";


    //redis中viewCount的key
    public static final String REDIS_VIEWCOUNT_KEY = "article:viewCount";
    //redis中用户信息key前缀
    public static final String REDIS_USERID_KEY_PRE = "EaseBlog_Login:";



}
