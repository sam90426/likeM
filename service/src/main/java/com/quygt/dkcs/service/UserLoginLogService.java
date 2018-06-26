package com.quygt.dkcs.service;

import com.quygt.dkcs.model.UserLoginLog;
import com.quygt.dkcs.utils.PageUtil;

public interface UserLoginLogService {
    boolean insert(UserLoginLog userLoginLog);

    /**
     * 按条件查询分页
     */
    PageUtil<UserLoginLog> getpagelist(UserLoginLog userLoginLog, int currentPage, int pagesize);
}
