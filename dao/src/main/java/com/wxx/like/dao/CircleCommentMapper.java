package com.wxx.like.dao;

import com.wxx.like.dao.base.BaseMapper;
import com.wxx.like.model.CircleComment;
import org.springframework.stereotype.Repository;

/**
 * 动态评论表Dao
 */
@Repository
public interface CircleCommentMapper extends BaseMapper<CircleComment> {

    Long insertBackId(CircleComment circleComment);
}
