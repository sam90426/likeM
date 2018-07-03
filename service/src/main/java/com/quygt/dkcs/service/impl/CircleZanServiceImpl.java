package com.quygt.dkcs.service.impl;

import javax.annotation.Resource;

import com.quygt.dkcs.dao.CircleZanMapper;
import com.quygt.dkcs.dao.base.BaseMapper;
import com.quygt.dkcs.model.CircleZan;
import com.quygt.dkcs.service.CircleZanService;
import com.quygt.dkcs.service.impl.base.BaseServiceImpl;
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