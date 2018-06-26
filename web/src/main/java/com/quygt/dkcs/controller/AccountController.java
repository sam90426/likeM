package com.quygt.dkcs.controller;

import com.quygt.dkcs.model.AdminUser;
import com.quygt.dkcs.model.AdminUserLoginLog;
import com.quygt.dkcs.service.AdminUserLoginLogService;
import com.quygt.dkcs.service.AdminUserService;
import com.quygt.dkcs.utils.*;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

import static com.quygt.dkcs.controller.CommonController.BrowserInfo;
import static com.quygt.dkcs.controller.CommonController.getIpAddr;

@Controller
@RequestMapping(value = "/account",produces = "text/plain;charset=UTF-8")
public class AccountController extends BaseController {

    @Resource
    private AdminUserService adminUserService;
    @Resource
    private AdminUserLoginLogService adminUserLoginLogService;

    //region 登录-加载
    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }
    //endregion

    //region 登录-操作
    @ResponseBody
    @RequestMapping(value = "/loginon")
    public String loginon(HttpServletRequest request,HttpServletResponse response,String username, String password) {
        AdminUser user = new AdminUser();
        user.setUserName(username);
        user = adminUserService.getmodel(user);
        String key=ConfigUtil.getInstance().getString("desKey");
        password= DesUtil.desEncrypt(key,password);
        if (user != null) {
            if (user.getState() == 1) {
                //region 登录日志
                AdminUserLoginLog loginLog = new AdminUserLoginLog();
                loginLog.setAdminUserId(user.getId());
                loginLog.setAdminRealName(user.getRealName());
                loginLog.setAdminUserName(user.getUserName());
                loginLog.setCreateTime(new Date());
                loginLog.setBrowser(BrowserInfo(request));
                loginLog.setIp(getIpAddr(request));
                //endregion
                if (user.getPassWord().equals(password)) {
                    //region 更新用户信息
                    user.setLastLoginTime(new Date());
                    user.setLoginCount(user.getLoginCount() + 1);
                    adminUserService.update(user);
                    //endregion

                    //region 插入登录日志
                    loginLog.setState(1);
                    adminUserLoginLogService.add(loginLog);
                    //endregion

                    //region 登录状态保存
                    HashMap map=new HashMap();
                    map.put("AdminUserID",user.getId());
                    map.put("AdminUserName",user.getRealName());
                    CookieHelp.WriteCookieByEncrypt(response,map);
                    SessionHelp.WriteSessionByEncrypt(request,map);
                    //endregion

                    return ResponseMsg.New(1, "/").asJson();
                } else {

                    //region 插入登录日志
                    loginLog.setState(2);
                    adminUserLoginLogService.add(loginLog);
                    //endregion

                    return ResponseMsg.New(0, "账号或密码错误").asJson();
                }
            } else {
                return ResponseMsg.New(0, "账号停用中").asJson();
            }
        } else {
            return ResponseMsg.New(0, "账号不存在").asJson();
        }
    }
    //endregion

    //region 退出登录
    @RequestMapping(value = "/loginout")
    public String loginout(HttpServletResponse response, HttpServletRequest request){
        CookieHelp.clearCookie(request,response,"dkcs");
        SessionHelp.removeSession(request,"dkcs");
        return "/account/login";
    }
    //endregion

    //region 修改密码

    //region 页面加载
    @RequestMapping(value = "/pwdupdate")
    public String pwdupdate(){
        return "/account/pwdupdate";
    }
    //endregion

    //region 修改密码
    @RequestMapping(value = "/savepwd",method = RequestMethod.POST)
    @ResponseBody
    public String savepwd(HttpServletRequest request){
        String oldpwd=request.getParameter("OldPwd");
        String newpwd=request.getParameter("NewPwd");
        if(oldpwd==null||oldpwd=="")
            return ResponseMsg.New(0,"请输入旧密码").asJson();
        if(newpwd==null||newpwd=="")
            return  ResponseMsg.New(0,"请输入新密码").asJson();
        String idstr=CookieHelp.GetCookieByEncrypt(request,"AdminUserID");
        if(idstr!=null&&idstr!=""){
            AdminUser adminUser=new AdminUser();
            adminUser.setId(Long.parseLong(idstr));
            adminUser=adminUserService.getmodel(adminUser);
            String key=ConfigUtil.getInstance().getString("desKey");
            oldpwd= DesUtil.desEncrypt(key,oldpwd);
            newpwd= DesUtil.desEncrypt(key,newpwd);
            if(!oldpwd.equals(adminUser.getPassWord()))
                return ResponseMsg.New(0,"原密码输入错误").asJson();
            if(oldpwd.equals(newpwd))
                return ResponseMsg.New(0,"原密码与新密码一致").asJson();
            adminUser.setPassWord(newpwd);
            if(adminUserService.update(adminUser))
                return ResponseMsg.New(1,"修改成功").asJson();
            else
                return  ResponseMsg.New(0,"修改失败").asJson();
        }else{
            return ResponseMsg.New(0,"修改失败").asJson();
        }
    }
    //endregion

    //endregion
}
