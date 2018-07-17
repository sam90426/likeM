package com.wxx.like.service.impl;

import javax.annotation.Resource;

import com.wxx.like.dao.LikeZanMapper;
import com.wxx.like.dao.base.BaseMapper;
import com.wxx.like.model.LikeZan;
import com.wxx.like.service.LikeZanService;
import com.wxx.like.service.impl.base.BaseServiceImpl;
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