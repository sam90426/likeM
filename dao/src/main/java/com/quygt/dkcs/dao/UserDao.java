package com.quygt.dkcs.dao;

import com.quygt.dkcs.dao.base.BaseDao;
import com.quygt.dkcs.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseDao<User> {
    User getUserByMobile(@Param("mobile") String mobile);
    User getUserByAccount(@Param("userName") String userName,@Param("passWord") String passWord);

    /**
     * 查询用户列表
     * @param user
     * @return
     */
    List<User> selectListAll(User user);
}
