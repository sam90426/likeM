package com.quygt.dkcs.controller;

import com.quygt.dkcs.model.UserInvite;
import com.quygt.dkcs.model.UserOtherInfo;
import com.quygt.dkcs.model.Withdrawals;
import com.quygt.dkcs.service.UserInviteService;
import com.quygt.dkcs.service.UserOtherInfoService;
import com.quygt.dkcs.service.UserService;
import com.quygt.dkcs.service.WithdrawalsService;
import com.quygt.dkcs.utils.PageUtil;
import com.quygt.dkcs.utils.ParseUtil;
import com.quygt.dkcs.utils.SessionHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.quygt.dkcs.utils.JsonUtil.Encode;

@Controller
@RequestMapping(value = "/withdrawals", produces = "text/plain;charset=UTF-8")
public class WithdrawalsController {

    @Autowired
    private WithdrawalsService withdrawalsService;

    @Autowired
    private UserInviteService userInviteService;

    @Autowired
    private UserOtherInfoService userOtherInfoService;


    @Autowired
    private UserService userService;

    private static final String WITHDRAWAL_LIST = "invitation/withdrawals/withdrawalList";
    private static final String WITHDRAWAL_MODAL = "invitation/withdrawals/withdrawalModal";

    @RequestMapping("/withdrawalList")
    public String withdrawalList() {
        return WITHDRAWAL_LIST;
    }

    @RequestMapping(value = "/withdrawalModal")
    public String withdrawalModal() {
        return WITHDRAWAL_MODAL;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer pageIndex, Withdrawals withdrawals) {
        int pageSize = ParseUtil.toInt(request.getParameter("pageSize"), 20);
        PageUtil<Withdrawals> page = withdrawalsService.getPageList(withdrawals, pageIndex, pageSize);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", page.getData());
        data.put("total", page.getTotalItems());
        return Encode(data);
    }

    @RequestMapping(value = "/getModalData")
    @ResponseBody
    public String getModalData(@RequestParam Long id) {
        Withdrawals withdrawals = withdrawalsService.getById(id);
        /*if(withdrawals.getState()==1){//提现中的需查询用户最新设置的值
            UserOtherInfo userOtherInfo=userOtherInfoService.getByUserId(withdrawals.getUserId());
            if(userOtherInfo!=null){
                withdrawals.setAlipayAccount(userOtherInfo.getAlipayAccount());
                withdrawals.setAlipayName(userOtherInfo.getAlipayName());
            }
        }*/
        return Encode(withdrawals);
    }

    @RequestMapping(value = "/updateWithdrawal")
    @ResponseBody
    public String updateWithdrawal(HttpServletRequest request, Withdrawals withdrawals) {
        String AdminUserName = SessionHelp.GetSessionByEncrypt(request, "AdminUserName");
        String AdminUserID = SessionHelp.GetSessionByEncrypt(request, "AdminUserID");
        Withdrawals old=withdrawalsService.getById(withdrawals.getId());
        withdrawals.setSysAdminId(Long.parseLong(AdminUserID));
        withdrawals.setSysAdminName(AdminUserName);
        if(withdrawals.getState().equals(2)){
            withdrawals.setPayTime(new Date());
        }
        boolean flag= withdrawalsService.updateById(withdrawals);
        if(flag){
            UserInvite u = new UserInvite();//修改的条件
            u.setUserId(old.getUserId());
            u.setWithdrawalsState(1);
            UserInvite i = new UserInvite();//修改的值
            i.setWithdrawalsState(withdrawals.getState());
            userInviteService.updateByUserInvite(i,u);
        }
        return Encode(withdrawals);
    }
}
