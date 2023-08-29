package com.biabuluo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biabuluo.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论表(Comment)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-27 21:51:05
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}

