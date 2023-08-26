package com.biabuluo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小宇
 * @date 2023-08-26:15:55
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description: 分类列表视图对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListVo {
    private Long id;
    //分类名
    private String name;

}
