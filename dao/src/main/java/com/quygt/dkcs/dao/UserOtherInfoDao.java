package com.quygt.dkcs.dao;

import com.quygt.dkcs.dao.base.BaseDao;
import com.quygt.dkcs.model.UserOtherInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOtherInfoDao extends BaseDao<UserOtherInfo> {

    UserOtherInfo getByUserId(Long userId);

}
