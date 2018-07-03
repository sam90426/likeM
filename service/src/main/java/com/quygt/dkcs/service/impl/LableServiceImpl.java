package com.quygt.dkcs.service.impl;

import javax.annotation.Resource;

import com.quygt.dkcs.dao.LableMapper;
import com.quygt.dkcs.dao.base.BaseMapper;
import com.quygt.dkcs.model.Lable;
import com.quygt.dkcs.service.LableService;
import com.quygt.dkcs.service.impl.base.BaseServiceImpl;
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