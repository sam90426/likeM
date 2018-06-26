package com.quygt.dkcs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.ChannelDao;
import com.quygt.dkcs.dao.UserInviteDao;
import com.quygt.dkcs.model.Channel;
import com.quygt.dkcs.model.UserInvite;
import com.quygt.dkcs.service.ChannelService;
import com.quygt.dkcs.service.UserInviteService;
import com.quygt.dkcs.service.impl.base.BaseServiceImpl;
import com.quygt.dkcs.utils.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Project:dkcs
 * @PackageName:com.quygt.dkcs.service.impl
 * @Author:fujian
 * @CreationDate:2018年01月15日11:44
 */
@Service
public class UserInviteServiceImpl extends BaseServiceImpl<UserInviteDao, UserInvite> implements UserInviteService {

    @Resource
    private UserInviteDao userInviteDao;

    /**
     * 关联被邀请人信息查询
     * @param userInvite
     * @return
     */
    public PageUtil<UserInvite> selectListAll(UserInvite userInvite, Integer pageNum, Integer size,String type){
        Page<UserInvite> page = PageHelper.startPage(pageNum, size);
        userInviteDao.selectListAll(userInvite,type);
        return new PageUtil<UserInvite>(pageNum, size, page.getPages(), page.getTotal(), page.getResult());
    }

    /**
     * 查询用户获得的奖金总额  包含提现中 提现失败 未申请提现等 不包含提现成功（可提现+不可提现）
     * @param userId
     * @return
     */
    public BigDecimal selectSumBountyAll(Long userId){
        return userInviteDao.selectSumBountyAll(userId);
    }

    /**
     * 根据用户id分组查询用户获得的每项金额
     * @param userId
     * @return
     */
    public Map<Integer,BigDecimal> selectSumBountyGroupState(Long userId){
        return userInviteDao.selectSumBountyGroupState(userId);
    }

    /**
     * 根据用户id查询可申请提现的总奖金
     * @return
     */
    public BigDecimal selectSumBountyByUserId(Long userId){
        return userInviteDao.selectSumBountyByUserId(userId);
    }

    /**
     * 根据用户id查询可申请提现的总邀请数
     * @param userId
     * @return
     */
    public Integer selectCountByUserId(Long userId,Integer isApp){
        return userInviteDao.selectCountByUserId(userId,isApp);
    }

    /**
     * 查询该用户 可提现、奖金在路上、全部记录数
     * @param userId
     * @return
     */
    public List<Map<String,Object>> selectCountAll(Long userId){
        return userInviteDao.selectCountAll(userId);
    }

    /**
     * 根据条件修改
     * @param i
     * @param u
     * @return
     */
    public boolean updateByUserInvite(UserInvite i,UserInvite u){
        return userInviteDao.updateByUserInvite(i,u);
    }

}
