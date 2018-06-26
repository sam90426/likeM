package com.quygt.dkcs.controller;

import com.quygt.dkcs.utils.CookieHelp;
import com.quygt.dkcs.utils.SessionHelp;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String AdminUserID =SessionHelp.GetSessionByEncrypt(httpServletRequest, "AdminUserID");
        if (AdminUserID == null || AdminUserID.equals("") || Long.parseLong(AdminUserID) < 1) {
            AdminUserID = CookieHelp.GetCookieByEncrypt(httpServletRequest, "AdminUserID");
            if (AdminUserID == null || AdminUserID.equals("") || Long.parseLong(AdminUserID) < 1) {
                httpServletResponse.sendRedirect("/account/login");
                return false;
            }else{
                String userData=CookieHelp.getCookieByName(httpServletRequest,"dkcs");
                if(userData!=null||userData!="")
                    SessionHelp.addSession(httpServletRequest,"dkcs",userData);
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
