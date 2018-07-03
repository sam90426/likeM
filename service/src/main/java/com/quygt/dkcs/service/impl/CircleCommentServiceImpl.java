package com.quygt.dkcs.service.impl;

import com.quygt.dkcs.dao.CircleCommentMapper;
import com.quygt.dkcs.dao.base.BaseMapper;
import com.quygt.dkcs.model.CircleComment;
import com.quygt.dkcs.service.CircleCommentService;
import com.quygt.dkcs.service.impl.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 动态评论表ServiceImpl
 */

@Service("circleCommentService")
public class CircleCommentServiceImpl extends BaseServiceImpl<CircleComment, Long> implements CircleCommentService {

    private static final Logger logger = LoggerFactory.getLogger(CircleCommentServiceImpl.class);

    @Resource
    private CircleCommentMapper circleCommentMapper;

    public BaseMapper<CircleComment, Long> getMapper() {
        return circleCommentMapper;
    }

}