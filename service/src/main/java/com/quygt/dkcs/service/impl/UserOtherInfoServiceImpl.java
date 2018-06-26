package com.quygt.dkcs.service.impl;

import com.quygt.dkcs.dao.UserInviteDao;
import com.quygt.dkcs.dao.UserOtherInfoDao;
import com.quygt.dkcs.model.UserInvite;
import com.quygt.dkcs.model.UserOtherInfo;
import com.quygt.dkcs.service.UserInviteService;
import com.quygt.dkcs.service.UserOtherInfoService;
import com.quygt.dkcs.service.impl.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @Project:dkcs
 * @PackageName:com.quygt.dkcs.service.impl
 * @Author:fujian
 * @CreationDate:2018年01月15日11:44
 */
@Service
public class UserOtherInfoServiceImpl extends BaseServiceImpl<UserOtherInfoDao, UserOtherInfo> implements UserOtherInfoService {

    @Resource
    private UserOtherInfoDao userOtherInfoDao;

    /**
     * 根据用id查询用户其他信息
     * @param userId
     * @return
     */
    public UserOtherInfo getByUserId(Long userId){
        return userOtherInfoDao.getByUserId(userId);
    }

    /**
     * 生成邀请码
     * @param len
     * @return
     */
    public String randomInvitationCode(int len) {
        UserOtherInfo search=new UserOtherInfo();
        while (true) {
            String str = randomNumAlph(len);
            search.setInvitationCode(str);
            UserOtherInfo userOtherInfo=userOtherInfoDao.getmodel(search);
            if (userOtherInfo == null) {
                return str;
            }
        }
    }

    private static String randomNumAlph(int len) {
        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        byte[][] list = {
                {48, 57},
                {97, 122},
                {65, 90}
        };
        for (int i = 0; i < len; i++) {
            byte[] o = list[random.nextInt(list.length)];
            byte value = (byte) (random.nextInt(o[1] - o[0] + 1) + o[0]);
            sb.append((char) value);
        }
        return sb.toString();
    }

}
