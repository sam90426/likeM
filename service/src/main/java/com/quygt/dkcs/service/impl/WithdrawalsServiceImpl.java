package com.quygt.dkcs.service.impl;

import com.quygt.dkcs.dao.UserDao;
import com.quygt.dkcs.dao.UserInviteDao;
import com.quygt.dkcs.dao.UserOtherInfoDao;
import com.quygt.dkcs.dao.WithdrawalsDao;
import com.quygt.dkcs.model.*;
import com.quygt.dkcs.service.SysDictionaryContentService;
import com.quygt.dkcs.service.UserService;
import com.quygt.dkcs.service.WithdrawalsService;
import com.quygt.dkcs.service.impl.base.BaseServiceImpl;
import com.quygt.dkcs.utils.ConfigUtil;
import com.quygt.dkcs.utils.ResponseMsg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Project:dkcs
 * @PackageName:com.quygt.dkcs.service.impl
 * @Author:fujian
 * @CreationDate:2018年01月15日11:44
 */
@Service
public class WithdrawalsServiceImpl extends BaseServiceImpl<WithdrawalsDao, Withdrawals> implements WithdrawalsService {

    @Resource
    private WithdrawalsDao withdrawalsDao;
    @Resource
    private UserInviteDao userInviteDao;

    @Resource
    private SysDictionaryContentService sysDictionaryContentService;

    @Resource
    private UserOtherInfoDao userOtherInfoDao;

    @Resource
    private UserDao userDao;

    /**
     * 申请提现
     * @return
     */
    public String apply(Long userId){
        BigDecimal bounty=userInviteDao.selectSumBountyByUserId(userId);
        if(bounty==null||bounty.compareTo(new BigDecimal("0"))<=0){
            return ResponseMsg.New(0, "暂无可提现金额").asJson();
        }
        //最低提现金额
        String key = ConfigUtil.getInstance().getString("invitation_config");
        SysDictionaryContent sysDictionaryContent=new SysDictionaryContent();
        sysDictionaryContent.setDicPath(key);
        List<SysDictionaryContent> list=sysDictionaryContentService.getList(sysDictionaryContent);
        BigDecimal minBounty=new BigDecimal("100");//默认为100 string4:最低提现金额
        if(list!=null&&list.size()>0){//存在该配置
            minBounty=StringUtils.isEmpty(list.get(0).getString4())?new BigDecimal("100"):new BigDecimal(list.get(0).getString4());
        }
        if(bounty.compareTo(minBounty)<0){
            return ResponseMsg.New(0, "申请失败，最低提现额度为："+minBounty+"元").asJson();
        }
        UserOtherInfo userOtherInfo=userOtherInfoDao.getByUserId(userId);
        if(userOtherInfo==null|| StringUtils.isEmpty(userOtherInfo.getAlipayAccount())||StringUtils.isEmpty(userOtherInfo.getAlipayName())){
            return ResponseMsg.New(-1, "请先设置支付宝信息").asJson();
        }

        Withdrawals withdrawals=new Withdrawals();
        withdrawals.setUserId(userId);
        withdrawals.setState(1);
        Integer num=withdrawalsDao.selectCount(withdrawals);
        if(num!=null&&num>0){
            return ResponseMsg.New(0, "您有一笔提现正在处理中，请等待").asJson();
        }
        User user=userDao.getById(userId);
        withdrawals.setAlipayName(userOtherInfo.getAlipayName());
        withdrawals.setAlipayAccount(userOtherInfo.getAlipayAccount());
        withdrawals.setCreateTime(new Date());
        withdrawals.setUserName(user.getRealName());
        boolean flag=withdrawalsDao.insert(withdrawals);
        if(flag){
            UserInvite userInvite=new UserInvite();//需更改的值
            userInvite.setUserName(user.getRealName());
            userInvite.setWithdrawalsState(1);
            UserInvite co=new UserInvite();//条件
            Integer [] states={0,3};//未申请和提现失败的
            co.setStates(states);
            co.setUserId(userId);
            userInviteDao.updateState(userInvite,co);
            return ResponseMsg.New(1, "申请成功，请等待人工审核").asJson();
        }
        return ResponseMsg.New(0, "申请失败，请稍后再试").asJson();
    }

    /**
     * 查询列表
     * @param withdrawals 其他条件
     * @param start 起始位置 不是页码
     * @param pageSize 查询条数
     * @param minAmount 最低金额筛选
     * @return
     */
    public List<Withdrawals> getNotice(Withdrawals withdrawals,Integer start,Integer pageSize,BigDecimal minAmount){
        return withdrawalsDao.getNotice(withdrawals,start,pageSize,minAmount);
    }
}
