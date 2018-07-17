package com.wxx.like.dao;

import com.wxx.like.dao.base.BaseMapper;
import com.wxx.like.model.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * 用户信息表Dao
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo, Long> {

    /**
     * 根据登录名查找用户记录
     * @param loginName
     * @return
     */
    UserInfo findUserByLoginName(String loginName);

}
