package com.quygt.dkcs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.RegisterUserDao;
import com.quygt.dkcs.dao.UserDao;
import com.quygt.dkcs.model.RegisterUser;
import com.quygt.dkcs.model.User;
import com.quygt.dkcs.service.RegisterUserService;
import com.quygt.dkcs.service.UserService;
import com.quygt.dkcs.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("RegisterUserService")
public class RegisterUserServiceImpl implements RegisterUserService {

    @Resource
    private RegisterUserDao registerUserDao;

    public boolean insert(RegisterUser registerUser) {
        return registerUserDao.insert(registerUser);
    }

    public RegisterUser getmodel(RegisterUser registerUser) {
        return registerUserDao.getmodel(registerUser);
    }
}
