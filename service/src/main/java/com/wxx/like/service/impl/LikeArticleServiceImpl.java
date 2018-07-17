package com.wxx.like.service.impl;

import javax.annotation.Resource;

import com.wxx.like.dao.LikeArticleMapper;
import com.wxx.like.dao.base.BaseMapper;
import com.wxx.like.model.LikeArticle;
import com.wxx.like.service.LikeArticleService;
import com.wxx.like.service.impl.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 官方文章表ServiceImpl
 */
 
@Service("likeArticleService")
public class LikeArticleServiceImpl extends BaseServiceImpl<LikeArticle, Long> implements LikeArticleService {
	
    private static final Logger logger = LoggerFactory.getLogger(LikeArticleServiceImpl.class);
   
    @Resource
    private LikeArticleMapper likeArticleMapper;

	public BaseMapper<LikeArticle, Long> getMapper() {
		return likeArticleMapper;
	}
	
}