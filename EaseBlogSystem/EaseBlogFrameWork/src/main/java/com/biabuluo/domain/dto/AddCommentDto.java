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
 * @date 2023-08-29:23:36
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)

@ApiModel(description = "添加评论dto")
public class AddCommentDto {
    //评论类型
    @ApiModelProperty(notes = "评论类型")
    private String type;

    //文章id
    @ApiModelProperty(notes = "文章id")
    private Long articleId;

    //根评论id
    @ApiModelProperty(notes = "根评论id")
    private Long rootId;

    //评论内容
    @ApiModelProperty(notes = "评论内容")
    private String content;

    //所回复的目标评论的userid
    @ApiModelProperty(notes = "所回复的目标评论的userid")
    private Long toCommentUserId;

    //回复目标评论id
    @ApiModelProperty(notes = "回复目标评论id")
    private Long toCommentId;

}
