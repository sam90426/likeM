package com.wxx.like.service.impl;

import com.wxx.like.dao.UserInfoMapper;
import com.wxx.like.dao.base.BaseMapper;
import com.wxx.like.model.UserInfo;
import com.wxx.like.service.UserInfoService;
import com.wxx.like.service.impl.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 用户信息表ServiceImpl
 */

@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public BaseMapper<UserInfo> getMapper() {
        return userInfoMapper;
    }

    @Override
    public UserInfo findUserByLoginName(String loginName) {
        return userInfoMapper.findUserByLoginName(loginName);
    }

    @Override
    public UserInfo findUserInfoByUserId(Long userId) {
        return userInfoMapper.findByPrimary(userId);
    }
}