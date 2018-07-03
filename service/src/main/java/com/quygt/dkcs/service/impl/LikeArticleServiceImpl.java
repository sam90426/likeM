package com.quygt.dkcs.service.impl;

import javax.annotation.Resource;

import com.quygt.dkcs.dao.LikeArticleMapper;
import com.quygt.dkcs.dao.base.BaseMapper;
import com.quygt.dkcs.model.LikeArticle;
import com.quygt.dkcs.service.LikeArticleService;
import com.quygt.dkcs.service.impl.base.BaseServiceImpl;
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