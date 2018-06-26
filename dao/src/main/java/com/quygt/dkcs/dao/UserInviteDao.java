package com.quygt.dkcs.dao;

import com.quygt.dkcs.dao.base.BaseDao;
import com.quygt.dkcs.model.UserInvite;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface UserInviteDao extends BaseDao<UserInvite> {

    /**
     * 根据用户id查询可申请提现的总奖金
     * @return
     */
    BigDecimal selectSumBountyByUserId(Long userId);

    /**
     * 根据用户修改信息
     * @param userInvite
     * @return
     */
    boolean updateState(@Param("us") UserInvite userInvite,@Param("co") UserInvite condition);

    /**
     * 关联被邀请人信息查询
     * @param userInvite
     * @return
     */
    List<UserInvite> selectListAll(@Param("ui") UserInvite userInvite,@Param("type") String type);

    int updateByIds(UserInvite userInvite, @Param("ids") Long[] ids);

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
     * 根据用户id查询可申请提现的总邀请数
     * @param userId
     * @return
     */
    Integer selectCountByUserId(@Param("userId") Long userId,@Param("isApp") Integer isApp);

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
    boolean updateByUserInvite(@Param("i") UserInvite i,@Param("u") UserInvite u);
}
