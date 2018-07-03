package com.quygt.dkcs.service.impl;

import javax.annotation.Resource;

import com.quygt.dkcs.dao.FriendsMapper;
import com.quygt.dkcs.dao.base.BaseMapper;
import com.quygt.dkcs.model.Friends;
import com.quygt.dkcs.service.FriendsService;
import com.quygt.dkcs.service.impl.base.BaseServiceImpl;
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