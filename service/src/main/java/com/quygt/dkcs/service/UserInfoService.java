package com.quygt.dkcs.service;


import com.quygt.dkcs.model.UserInfo;
import com.quygt.dkcs.service.base.BaseService;

/**
 * 用户信息表Service
 */
public interface UserInfoService extends BaseService<UserInfo, Long> {

    /**
     * 根据登录名查找用户记录
     * @param loginName
     * @return
     */
    UserInfo findUserByLoginName(String loginName);
}
