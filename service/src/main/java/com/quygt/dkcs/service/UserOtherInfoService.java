package com.quygt.dkcs.service;

import com.quygt.dkcs.model.UserInvite;
import com.quygt.dkcs.model.UserOtherInfo;
import com.quygt.dkcs.service.base.BaseService;

public interface UserOtherInfoService extends BaseService<UserOtherInfo> {


    /**
     * 生成邀请码
     * @param len
     * @return
     */
    String randomInvitationCode(int len);

    /**
     * 根据用id查询用户其他信息
     * @param userId
     * @return
     */
    UserOtherInfo getByUserId(Long userId);
}
