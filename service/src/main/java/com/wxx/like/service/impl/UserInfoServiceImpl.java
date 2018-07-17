package com.wxx.like.service.impl;

import javax.annotation.Resource;

import com.wxx.like.dao.UserInfoMapper;
import com.wxx.like.dao.base.BaseMapper;
import com.wxx.like.model.UserInfo;
import com.wxx.like.service.UserInfoService;
import com.wxx.like.service.impl.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 用户信息表ServiceImpl
 */
 
@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo, Long> implements UserInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
   
    @Resource
    private UserInfoMapper userInfoMapper;

	public BaseMapper<UserInfo, Long> getMapper() {
		return userInfoMapper;
	}

    @Override
    public UserInfo findUserByLoginName(String loginName) {
        return null;
    }

    @Override
    public UserInfo findUserInfoByUserId(Long userId) {
	    UserInfo userInfo=new UserInfo();
	    userInfo.setId(userId);
        return userInfoMapper.getmodel(userInfo);
    }
}