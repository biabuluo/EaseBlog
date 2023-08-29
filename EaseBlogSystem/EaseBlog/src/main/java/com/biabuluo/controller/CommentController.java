package com.biabuluo.controller;

import com.biabuluo.constans.SystemConstants;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.dto.AddCommentDto;
import com.biabuluo.domain.entity.Comment;
import com.biabuluo.service.CommentService;
import com.biabuluo.utils.BeanCopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "评论", description = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //获取comment列表
    @GetMapping("/getArticleCommentList")
    @ApiOperation(value = "评论列表", notes = "获取评论列表，参数：QueryString")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentType", value = "是否为友链评论(这里没有用的，传什么都可以)"),
            @ApiImplicitParam(name = "articleId", value = "文章id"),
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "页量"),
    })
    public ResponseResult getCommentList(String commentType,  Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.COMMENT_NORMAL, articleId, pageNum, pageSize);
    }

    //添加评论
    @PostMapping("/addComment")
    @ApiOperation(value = "评论添加", notes = "添加评论，参数：body，需要token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comment", value = "一个comment实体")
    })
    public ResponseResult addComment(@RequestBody AddCommentDto addCommentDto){
        Comment comment = BeanCopyUtil.copyBean(addCommentDto, Comment.class);
        return commentService.addComment(comment);
    }

    //获取友链comment列表 @RequestParam
    @GetMapping("/getLinkCommentList")
    @ApiOperation(value = "获取友链评论", notes = "获取友链评论，参数：QueryString")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "页量"),
    })
    public ResponseResult getLinkCommentList(Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.COMMENT_LINK, null, pageNum, pageSize);
    }

}
