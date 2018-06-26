package com.quygt.dkcs.service;

import com.quygt.dkcs.model.UserInvite;
import com.quygt.dkcs.service.base.BaseService;
import com.quygt.dkcs.utils.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UserInviteService  extends BaseService<UserInvite> {

    /**
     * 关联被邀请人信息查询
     * @param userInvite
     * @return
     */
    PageUtil<UserInvite> selectListAll(UserInvite userInvite,Integer page,Integer size,String type);

    /**
     * 查询用户获得的奖金总额  包含提现中 提现失败 未申请提现等 不包含提现成功（可提现+不可提现）
     * @param userId
     * @return
     */
    BigDecimal selectSumBountyAll(Long userId);

    /**
     * 根据用户id分组查询用户获得的每项金额
     * @param userId
     * @return
     */
    Map<Integer,BigDecimal> selectSumBountyGroupState(Long userId);


    /**
     * 根据用户id查询可申请提现的总奖金
     * @return
     */
    BigDecimal selectSumBountyByUserId(Long userId);

    /**
     * 根据用户id查询可申请提现的总邀请数
     * @param userId
     * @return
     */
    Integer selectCountByUserId(Long userId,Integer isApp);

    /**
     * 查询该用户 可提现、奖金在路上、全部记录数
     * @param userId
     * @return
     */
    List<Map<String,Object>> selectCountAll(Long userId);

    /**
     * 根据条件修改
     * @param i
     * @param u
     * @return
     */
    boolean updateByUserInvite(UserInvite i, UserInvite u);
}
