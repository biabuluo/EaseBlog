package com.biabuluo.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author 小宇
 * @date 2023-08-29:23:37
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)

@ApiModel(description = "登录用户Dto")
public class LoginUserDto {

    //用户名
    @ApiModelProperty(notes = "用户名")
    private String userName;
    //密码
    @ApiModelProperty(notes = "密码")
    private String password;

}
