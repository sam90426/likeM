package com.wxx.like.service.impl;

import com.wxx.like.dao.CircleInfoMapper;
import com.wxx.like.dao.base.BaseMapper;
import com.wxx.like.model.CircleInfo;
import com.wxx.like.service.CircleInfoService;
import com.wxx.like.service.impl.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 动态表ServiceImpl
 */
 
@Service("circleInfoService")
public class CircleInfoServiceImpl extends BaseServiceImpl<CircleInfo, Long> implements CircleInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(CircleInfoServiceImpl.class);
   
    @Resource
    private CircleInfoMapper circleInfoMapper;

	public BaseMapper<CircleInfo, Long> getMapper() {
		return circleInfoMapper;
	}
	
}