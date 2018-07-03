package com.quygt.dkcs.service.impl;

import com.quygt.dkcs.dao.CircleInfoMapper;
import com.quygt.dkcs.dao.base.BaseMapper;
import com.quygt.dkcs.model.CircleInfo;
import com.quygt.dkcs.service.CircleInfoService;
import com.quygt.dkcs.service.impl.base.BaseServiceImpl;
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