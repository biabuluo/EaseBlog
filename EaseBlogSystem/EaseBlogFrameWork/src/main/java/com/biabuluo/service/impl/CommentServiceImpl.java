package com.biabuluo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biabuluo.constans.SystemConstants;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.Comment;
import com.biabuluo.domain.entity.User;
import com.biabuluo.domain.vo.CommentVo;
import com.biabuluo.domain.vo.PageVo;
import com.biabuluo.enums.AppHttpCodeEnum;
import com.biabuluo.exception.SystemException;
import com.biabuluo.mapper.CommentMapper;
import com.biabuluo.service.CommentService;
import com.biabuluo.service.UserService;
import com.biabuluo.utils.BeanCopyUtil;
import com.biabuluo.utils.SecurityContextUtil;

import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-08-27 21:51:05
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;
    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //articleId要对应
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(articleId!=null, Comment::getArticleId, articleId);
        //获取对应评论类型
        queryWrapper.eq(Comment::getType, commentType);
        //获取所有rootid = -1
        queryWrapper.eq(Comment::getRootId, SystemConstants.COMMENT_ISROOT);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        //分页查询
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<Comment> pageRecords = page.getRecords();
        //封装Vo
        List<CommentVo> commentVoList = toCommentVo(pageRecords);
        //查询评论对应的子评论
        commentVoList = commentVoList.stream()
                .map(this::getChildComment)
                .collect(Collectors.toList());
        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));
    }

    //添加评论功能实现
    @Override
    public ResponseResult addComment(Comment comment) {
        //从token获取createBy字段
        //自动填充
        //评论内容不能为空   //未来内容扩展：敏感词
        if(Strings.hasText(comment.getContent()))
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        save(comment);
        return ResponseResult.okResult();
    }


    //封装评论Vo
    private List<CommentVo> toCommentVo(List<Comment> records){
        List<CommentVo> commentVoList = BeanCopyUtil.copyBeanList(records, CommentVo.class);
        //填补确实值：评论用户名+被评论用户名
        commentVoList = commentVoList.stream()
                .map(o1 -> {
                    o1.setCreateName(userService.getById(o1.getCreateBy()).getNickName());
                    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
                    Long toCommentUserId = o1.getToCommentUserId();
                    if(toCommentUserId!=-1){
                        o1.setToCommentUserName(userService.getById(toCommentUserId).getNickName());
                    }else o1.setToCommentUserName(null);
                    return o1;
                })
                .collect(Collectors.toList());
        return commentVoList;
    }


    //获取子评论
    private CommentVo getChildComment(CommentVo commentVo){
        List<CommentVo> childsVo = new ArrayList<>();
        List<Comment> childs = new ArrayList<>();
        Long rootId = commentVo.getId();
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, rootId);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        childs = list(queryWrapper);
        childsVo = toCommentVo(childs);
        return commentVo.setChildren(childsVo);
    }
}

