package com.quygt.dkcs.controller;

import com.quygt.dkcs.utils.CookieHelp;
import com.quygt.dkcs.utils.SessionHelp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MallController extends BaseController {

    //region 主页
    @RequestMapping(value = "/")
    public String home(HttpServletRequest request, ModelMap model){
        String realname= CookieHelp.GetCookieByEncrypt(request,"AdminUserName");
        if(realname==null||realname=="")
            realname= SessionHelp.GetSessionByEncrypt(request,"AdminUserName");
        model.addAttribute("username",realname);
        return "/mall/home";
    }
    //endregion

}
