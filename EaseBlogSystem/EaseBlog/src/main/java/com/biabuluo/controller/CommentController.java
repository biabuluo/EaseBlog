package com.biabuluo.controller;

import com.biabuluo.constans.SystemConstants;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.Comment;
import com.biabuluo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 小宇
 * @date 2023-08-27:21:54
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //获取comment列表
    @GetMapping("/getCommentList")
    public ResponseResult getCommentList(String commentType,  Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.COMMENT_NORMAL, articleId, pageNum, pageSize);
    }

    //添加评论
    @PostMapping("/addComment")
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    //获取友链comment列表 @RequestParam
    public ResponseResult getLinkCommentList(Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.COMMENT_LINK, null, pageNum, pageSize);
    }

}
