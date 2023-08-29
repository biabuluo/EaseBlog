package com.biabuluo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.Comment;

/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-08-27 21:51:03
 */
public interface CommentService extends IService<Comment> {


    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}

