package com.biabuluo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author 小宇
 * @date 2023-08-27:22:34
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CommentVo {

    private Long id;

    //文章id
    private Long articleId;
    //根评论id
    private Long rootId;
    //评论内容
    private String content;
    //所回复的目标评论的userid
    private Long toCommentUserId;
    private String toCommentUserName;
    //回复目标评论id
    private Long toCommentId;

    //评论用户id
    private Long createBy;
    //评论用户名
    private String createName;

    private Date createTime;
    //子评论
    private List<CommentVo> children;

}
