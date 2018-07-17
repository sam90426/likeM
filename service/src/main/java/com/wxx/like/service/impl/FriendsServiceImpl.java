package com.wxx.like.service.impl;

import javax.annotation.Resource;

import com.wxx.like.dao.FriendsMapper;
import com.wxx.like.dao.base.BaseMapper;
import com.wxx.like.model.Friends;
import com.wxx.like.service.FriendsService;
import com.wxx.like.service.impl.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



/**
 * 好友表ServiceImpl
 */
 
@Service("friendsService")
public class FriendsServiceImpl extends BaseServiceImpl<Friends, Long> implements FriendsService {
	
    private static final Logger logger = LoggerFactory.getLogger(FriendsServiceImpl.class);
   
    @Resource
    private FriendsMapper friendsMapper;

	public BaseMapper<Friends, Long> getMapper() {
		return friendsMapper;
	}
	
}