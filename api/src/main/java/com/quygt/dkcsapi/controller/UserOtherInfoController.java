package com.quygt.dkcsapi.controller;

import com.quygt.dkcs.model.SysDictionaryContent;
import com.quygt.dkcs.model.User;
import com.quygt.dkcs.model.UserOtherInfo;
import com.quygt.dkcs.service.SysDictionaryContentService;
import com.quygt.dkcs.service.UserInviteService;
import com.quygt.dkcs.service.UserOtherInfoService;
import com.quygt.dkcs.service.UserService;
import com.quygt.dkcs.utils.ConfigUtil;
import com.quygt.dkcs.utils.ResponseMsg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project:dkcs
 * @PackageName:com.quygt.dkcsapi.controller
 * @Author:fujian
 * @CreationDate:2018年01月23日11:57
 */
@Controller
@RequestMapping(value = "/userOtherInfo",produces = "text/plain;charset=UTF-8")
public class UserOtherInfoController {

    @Resource
    private UserOtherInfoService userOtherInfoService;

    @Resource
    private UserService userService;

    @Resource
    private UserInviteService userInviteService;

    @Resource
    private SysDictionaryContentService sysDictionaryContentService;

    /**
     * 绑定支付宝
     * @param userOtherInfo
     * @return
     */
    @RequestMapping(value = "/bindAliPay", method = RequestMethod.POST)
    @ResponseBody
    public String bindAliPay(UserOtherInfo userOtherInfo,Long uid) {
        userOtherInfo.setUserId(uid);
        User user=userService.getmodel(new User(userOtherInfo.getUserId()));
        if(user==null){
            return ResponseMsg.New(0, "该用户不存在").asJson();
        }
        boolean flag=false;
        UserOtherInfo old=userOtherInfoService.getByUserId(userOtherInfo.getUserId());
        if(old==null){
            userOtherInfo.setInvitationCode(userOtherInfoService.randomInvitationCode(6));
            userOtherInfo.setIsApp(1);
            flag=userOtherInfoService.insert(userOtherInfo);
        }else{
            userOtherInfo.setId(old.getId());
            flag=userOtherInfoService.updateById(userOtherInfo);
        }
        if(flag){
            return ResponseMsg.New(1, "绑定成功").asJson();
        }
        return ResponseMsg.New(0, "绑定失败").asJson();
    }

    /**
     * 查看支付宝信息
     * @param uid
     * @return
     */
    @RequestMapping(value = "/selectByUserId", method = RequestMethod.POST)
    @ResponseBody
    public String selectByUserId(Long uid) {
        User user=userService.getmodel(new User(uid));
        if(user==null){
            return ResponseMsg.New(0, "该用户不存在").asJson();
        }
        UserOtherInfo old=userOtherInfoService.getByUserId(uid);
        return ResponseMsg.New(1, "查询成功",old).asJson();
    }

    /**
     * 我的邀请码 页面数据查询
     * @param uid
     * @return
     */
    @RequestMapping(value = "/myInvitationCode", method = RequestMethod.POST)
    @ResponseBody
    public String myInvitationCode(Long uid) {
        User user=userService.getmodel(new User(uid));
        if(user==null){
            return ResponseMsg.New(0, "该用户不存在").asJson();
        }
        UserOtherInfo old=userOtherInfoService.getByUserId(uid);
        if(old==null){
            old=new UserOtherInfo();
            old.setInvitationCode(userOtherInfoService.randomInvitationCode(6));
            old.setIsApp(1);
            userOtherInfoService.insert(old);
        }
        BigDecimal bounty=userInviteService.selectSumBountyAll(uid);//我的待返奖金
        if(bounty==null){
            bounty=new BigDecimal("0");
        }
        //邀请配置字典编码
        String key = ConfigUtil.getInstance().getString("invitation_config");
        SysDictionaryContent sysDictionaryContent=new SysDictionaryContent();
        sysDictionaryContent.setDicPath(key);
        List<SysDictionaryContent> list=sysDictionaryContentService.getList(sysDictionaryContent);
        BigDecimal bountyOne=new BigDecimal("1");//默认为1 string3:邀请好友成功奖励金额
        if(list!=null&&list.size()>0){//存在该配置
            bountyOne=(StringUtils.isEmpty(list.get(0).getString3())?new BigDecimal("1"):new BigDecimal(list.get(0).getString3()));
        }
        Map<String,Object> map=new HashMap<>();
        map.put("info",old);
        map.put("bounty",bounty);
        map.put("bountyOne",bountyOne);
        return ResponseMsg.New(1, "查询成功",map).asJson();
    }
}
