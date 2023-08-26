package com.biabuluo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 小宇
 * @date 2023-08-26:16:56
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description: 所有分页查询返回结果封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {
    private List rows;
    private Long total;

}
