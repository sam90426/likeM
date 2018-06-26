package com.quygt.dkcs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.UserLoginLogDao;
import com.quygt.dkcs.model.UserLoginLog;
import com.quygt.dkcs.service.UserLoginLogService;
import com.quygt.dkcs.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserLoginLogService")
public class UserLoginLogServiceImpl implements UserLoginLogService {

    @Resource
    private UserLoginLogDao userLoginLogDao;

    public boolean insert(UserLoginLog userLoginLog) {
        return userLoginLogDao.insert(userLoginLog);
    }

    public PageUtil<UserLoginLog> getpagelist(UserLoginLog userLoginLog, int currentPage, int pagesize) {
        try {
            Page<UserLoginLog> page = PageHelper.startPage(currentPage, pagesize);
            userLoginLogDao.getlist(userLoginLog);
            PageUtil data = new PageUtil(currentPage, pagesize, page.getPages(), page.getTotal(), page.getResult());
            return data;
        } catch (Exception e) {

        }
        return null;
    }
}
