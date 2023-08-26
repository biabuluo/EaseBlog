package com.biabuluo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小宇
 * @date 2023-08-26:22:51
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVo {
    private String token;
    private UserInfoVo userInfoVo;
}
