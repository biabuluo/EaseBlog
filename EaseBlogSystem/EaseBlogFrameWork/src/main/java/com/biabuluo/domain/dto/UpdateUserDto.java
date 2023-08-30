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
 * @date 2023-08-29:23:38
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)

@ApiModel(description = "更新用户dto")
public class UpdateUserDto {
    @ApiModelProperty(notes = "用户id")
    private Long id;

    //昵称
    @ApiModelProperty(notes = "昵称")
    private String nickName;

    //密码
    @ApiModelProperty(notes = "密码")
    private String password;

    //邮箱
    @ApiModelProperty(notes = "邮箱")
    private String email;

    //手机号
    @ApiModelProperty(notes = "手机号")
    private String phonenumber;

    //用户性别（0男，1女，2未知）
    @ApiModelProperty(notes = "用户性别（0男，1女，2未知）")
    private String sex;

    //头像
    @ApiModelProperty(notes = "头像")
    private String avatar;
}
