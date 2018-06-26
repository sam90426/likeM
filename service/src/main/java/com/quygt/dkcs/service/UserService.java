package com.quygt.dkcs.service;

import com.quygt.dkcs.model.User;
import com.quygt.dkcs.utils.PageUtil;

import java.util.List;

public interface UserService {
    User getUserByMobile(String mobile);
    User getUserByAccount(String userName,String passWord);
    boolean insert(User user);
    boolean update(User user);
    PageUtil<User> getPageList(User user, int currPage, int pageSize);
    User getmodel(User user);

    /**
     * 获取登录验证码
     * @param mobile
     * @return
     */
    String getCode(String mobile);

    /**
     * 登录
     * @param mobile
     * @return
     */
    String insertOrSelect(String mobile);

    /**
     * 查询用户信息列表 未分页
     * @param user
     * @return
     */
    List<User> selectListAll(User user);
}
