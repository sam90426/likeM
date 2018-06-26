package com.quygt.dkcs.service;

import com.quygt.dkcs.model.RegisterUser;
import com.quygt.dkcs.model.User;
import com.quygt.dkcs.utils.PageUtil;

public interface RegisterUserService {
    boolean insert(RegisterUser registerUser);
    RegisterUser getmodel(RegisterUser registerUser);
}
