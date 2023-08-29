package com.biabuluo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author 小宇
 * @date 2023-08-26:16:52
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ArticleListVo {
    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;
    //所属分类名
    private String categoryName;
    //缩略图
    private String thumbnail;

    //访问量
    private Long viewCount;

    private Date createTime;

}
