package com.wxx.like.service.impl;

import com.wxx.like.dao.LikeCommentMapper;
import com.wxx.like.dao.base.BaseMapper;
import com.wxx.like.model.LikeComment;
import com.wxx.like.service.LikeCommentService;
import com.wxx.like.service.impl.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 官方文章评论ServiceImpl
 */

@Service("likeCommentService")
public class LikeCommentServiceImpl extends BaseServiceImpl<LikeComment, Long> implements LikeCommentService {

    private static final Logger logger = LoggerFactory.getLogger(LikeCommentServiceImpl.class);

    @Resource
    private LikeCommentMapper likeCommentMapper;

    public BaseMapper<LikeComment, Long> getMapper() {
        return likeCommentMapper;
    }

}