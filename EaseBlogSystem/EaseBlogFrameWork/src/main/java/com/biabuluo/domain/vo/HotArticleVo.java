package com.biabuluo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小宇
 * @date 2023-08-26:13:59
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:hotArticle视图对象
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo {
    private Long id;
    private String title;
    private Long viewCount;
}
