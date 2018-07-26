package com.wxx.like.service.impl;

import com.wxx.like.dao.CircleCommentMapper;
import com.wxx.like.dao.base.BaseMapper;
import com.wxx.like.model.CircleComment;
import com.wxx.like.service.CircleCommentService;
import com.wxx.like.service.impl.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 动态评论表ServiceImpl
 */

@Service("circleCommentService")
public class CircleCommentServiceImpl extends BaseServiceImpl<CircleComment> implements CircleCommentService {

    private static final Logger logger = LoggerFactory.getLogger(CircleCommentServiceImpl.class);

    @Resource
    private CircleCommentMapper circleCommentMapper;

    @Override
    public BaseMapper<CircleComment> getMapper() {
        return circleCommentMapper;
    }

    @Override
    public Long insertBackId(CircleComment circleComment) {
        return circleCommentMapper.insertBackId(circleComment);
    }
}