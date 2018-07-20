package com.wxx.like.service;


import com.wxx.like.model.UserInfo;
import com.wxx.like.service.base.BaseService;

/**
 * 用户信息表Service
 */
public interface UserInfoService extends BaseService<UserInfo> {

    /**
     * 根据登录名查找用户记录
     * @param loginName
     * @return
     */
    UserInfo findUserByLoginName(String loginName);

    UserInfo findUserInfoByUserId(Long userId);
}
