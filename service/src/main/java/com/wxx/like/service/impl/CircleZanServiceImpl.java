package com.wxx.like.service.impl;

import javax.annotation.Resource;

import com.wxx.like.dao.CircleZanMapper;
import com.wxx.like.dao.base.BaseMapper;
import com.wxx.like.model.CircleZan;
import com.wxx.like.service.CircleZanService;
import com.wxx.like.service.impl.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



/**
 * 动态点赞表ServiceImpl
 */
 
@Service("circleZanService")
public class CircleZanServiceImpl extends BaseServiceImpl<CircleZan, Long> implements CircleZanService {
	
    private static final Logger logger = LoggerFactory.getLogger(CircleZanServiceImpl.class);
   
    @Resource
    private CircleZanMapper circleZanMapper;

	public BaseMapper<CircleZan, Long> getMapper() {
		return circleZanMapper;
	}
	
}