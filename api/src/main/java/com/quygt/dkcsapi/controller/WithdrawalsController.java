package com.quygt.dkcsapi.controller;

import com.quygt.dkcs.model.InfoHelpDicContent;
import com.quygt.dkcs.model.SysDictionaryContent;
import com.quygt.dkcs.model.User;
import com.quygt.dkcs.model.Withdrawals;
import com.quygt.dkcs.service.*;
import com.quygt.dkcs.utils.ConfigUtil;
import com.quygt.dkcs.utils.PageUtil;
import com.quygt.dkcs.utils.ResponseMsg;
import com.quygt.dkcsapi.common.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tool.util.DateUtil;
import tool.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Project:dkcs
 * @PackageName:com.quygt.dkcsapi.controller
 * @Author:fujian
 * @CreationDate:2018年01月22日14:06
 */
@Controller
@RequestMapping(value = "/withdrawals",produces = "text/plain;charset=UTF-8")
public class WithdrawalsController {

    @Resource
    private WithdrawalsService withdrawalsService;

    @Resource
    private UserInviteService userInviteService;

    @Resource
    private UserService userService;

    @Resource
    private SysDictionaryContentService sysDictionaryContentService;

    @Resource
    private InfoHelpDicContentService infoHelpDicContentService;

    /**
     * 申请提现
     * @param uid
     * @return
     */
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    @ResponseBody
    public String apply(Long uid) {
        return withdrawalsService.apply(uid);
    }

    /**
     * 我的奖金页面
     * @param uid
     * @return
     */
    @RequestMapping(value = "/myBounty", method = RequestMethod.POST)
    @ResponseBody
    public String myBounty(Long uid) {
        Map<String,Object> json=new HashMap<>();
        BigDecimal waitBountyAll=userInviteService.selectSumBountyAll(uid);//我的待返奖金总额
        BigDecimal bountyOk=new BigDecimal("0");
        if(waitBountyAll==null){
            waitBountyAll=new BigDecimal("0");
        }else{
            bountyOk=userInviteService.selectSumBountyByUserId(uid);//可提总额
            if(bountyOk==null){
                bountyOk=new BigDecimal("0");
            }
        }
        BigDecimal bountyNo=waitBountyAll.subtract(bountyOk);
        json.put("waitBountyAll",waitBountyAll);
        json.put("bountyOk",bountyOk);
        json.put("bountyNo",bountyNo);
        return ResponseMsg.New(1, "查询成功",json).asJson();
    }

    /**
     * 提现记录
     * @param withdrawals
     * @return
     */
    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    @ResponseBody
    public void selectList(Withdrawals withdrawals,Long uid,Integer currPage, Integer pageSize, HttpServletResponse response) {
        currPage--;
        if(withdrawals==null){
            withdrawals=new Withdrawals();
        }
        withdrawals.setUserId(uid);
        PageUtil<Withdrawals> list=withdrawalsService.getPageList(withdrawals,currPage,pageSize);
        Map<String,Object> result=new HashMap<>();
        result.put("code", 1);
        result.put("message", "success");
        result.put("data", list);
        ServletUtils.writeToResponse(response,result);
    }

    /**
     * 提现公告
     * @return
     */
    @RequestMapping(value = "/getNotice", method = RequestMethod.POST)
    @ResponseBody
    public String getNotice(Withdrawals withdrawals) {
        //最低提现金额
        SysDictionaryContent sysDictionaryContent=new SysDictionaryContent();
        String key = ConfigUtil.getInstance().getString("invitation_config");
        sysDictionaryContent.setDicPath(key);
        List<SysDictionaryContent> sysDictionaryContents=sysDictionaryContentService.getList(sysDictionaryContent);
        BigDecimal minBounty=new BigDecimal("100");//默认为100 string4:最低提现金额
        if(sysDictionaryContents!=null&&sysDictionaryContents.size()>0){//存在该配置
            minBounty=StringUtils.isEmpty(sysDictionaryContents.get(0).getString4())?new BigDecimal("100"):new BigDecimal(sysDictionaryContents.get(0).getString4());
        }

        int num=10;//展示的条数
        withdrawals.setStartTime(DateUtil.dateStr2(DateUtil.rollDay(new Date(),-1)));
        withdrawals.setState(2);
        List<Withdrawals> list=withdrawalsService.getNotice(withdrawals,0,num,minBounty);
        if(list==null){
            list=new ArrayList<>();
        }
        for (int i = 0; i < list.size(); i++) {
            if(!StringUtil.isNumber(list.get(i).getUserName())){
                list.remove(i);
                i--;
            }
        }
        if(list.size()<num){
            PageUtil<User> users=userService.getPageList(null,1,num-list.size());


            for (int i = 0; i < users.getData().size(); i++) {
                Withdrawals withdrawals1=new Withdrawals();
                withdrawals1.setUserName((Long.parseLong(users.getData().get(i).getUserName())+3815)+"");
                Long time=users.getData().get(i).getCreateTime().getTime()/1000;
                BigDecimal amount=new BigDecimal(time.toString().substring(time.toString().length()-minBounty.toString().length()-1,time.toString().length())).setScale(2,BigDecimal.ROUND_HALF_UP);
                boolean flag = true;
                while (flag) {
                    if(amount.compareTo(minBounty)<0){
                        amount=minBounty;
                    }
                    if(amount.compareTo(minBounty)>=0&&amount.compareTo(minBounty.multiply(new BigDecimal(3)))<=0){
                        flag=false;
                    }else{
                        amount=amount.divide(new BigDecimal(2),0,BigDecimal.ROUND_HALF_UP).setScale(2,BigDecimal.ROUND_HALF_UP);
                    }
                }
                withdrawals1.setAmount(amount);
                list.add(withdrawals1);
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);
        return ResponseMsg.New(1, "查询成功",map).asJson();
    }

    /**
     * 提现规则文案
     * @return
     */
    @RequestMapping(value = "/getRule")
    public ModelAndView getRule() {
        String key = ConfigUtil.getInstance().getString("role_copy_config");
        InfoHelpDicContent search=new InfoHelpDicContent();
        search.setState(1);
        search.setDicPath(key);
        InfoHelpDicContent infoHelpDicContent=infoHelpDicContentService.getmodel(search);
        ModelAndView modelAndView = new ModelAndView("info/getRule");
        modelAndView.addObject("info",infoHelpDicContent);
        return modelAndView;
    }
}
