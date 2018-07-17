package com.wxx.like.service.impl;

import javax.annotation.Resource;

import com.wxx.like.dao.LableMapper;
import com.wxx.like.dao.base.BaseMapper;
import com.wxx.like.model.Lable;
import com.wxx.like.service.LableService;
import com.wxx.like.service.impl.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 标签表ServiceImpl
 */
 
@Service("lableService")
public class LableServiceImpl extends BaseServiceImpl<Lable, Long> implements LableService {
	
    private static final Logger logger = LoggerFactory.getLogger(LableServiceImpl.class);
   
    @Resource
    private LableMapper lableMapper;

	public BaseMapper<Lable, Long> getMapper() {
		return lableMapper;
	}
	
}