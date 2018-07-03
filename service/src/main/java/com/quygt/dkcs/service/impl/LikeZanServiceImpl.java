package com.quygt.dkcs.service.impl;

import javax.annotation.Resource;

import com.quygt.dkcs.dao.LikeZanMapper;
import com.quygt.dkcs.dao.base.BaseMapper;
import com.quygt.dkcs.model.LikeZan;
import com.quygt.dkcs.service.LikeZanService;
import com.quygt.dkcs.service.impl.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 官方文章点赞表ServiceImpl
 */
 
@Service("likeZanService")
public class LikeZanServiceImpl extends BaseServiceImpl<LikeZan, Long> implements LikeZanService {
	
    private static final Logger logger = LoggerFactory.getLogger(LikeZanServiceImpl.class);
   
    @Resource
    private LikeZanMapper likeZanMapper;

	public BaseMapper<LikeZan, Long> getMapper() {
		return likeZanMapper;
	}
	
}