package com.quygt.dkcsapi.filter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.quygt.dkcs.model.User;
import com.quygt.dkcs.service.UserService;
import com.quygt.dkcs.utils.ConfigUtil;
import com.quygt.dkcs.utils.DesUtil;
import com.quygt.dkcs.utils.TokenUtil;
import com.quygt.dkcsapi.common.AppContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenFilter implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader("token");
        if (token == null || token == "")
            return AppContext.errorReturn(httpServletResponse, 2, "token为空");
        String uidString = httpServletRequest.getParameter("uid");
        long uid = 0;
        try {
            uid = Long.parseLong(uidString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (uid == 0)
            return AppContext.errorReturn(httpServletResponse, 2, "uid为空");
        String tokenString = DesUtil.desDecrypt(ConfigUtil.getInstance().getString("desKey"), token);
        if (tokenString == null)
            return AppContext.errorReturn(httpServletResponse, 2, "token验证失败");
        String[] tokenArray = tokenString.split("\\|");
        if (tokenArray.length != 4)
            return AppContext.errorReturn(httpServletResponse, 2, "token验证失败");
        long id = 0;
        long expiredTime = 0;
        try {
            id = Long.parseLong(tokenArray[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            expiredTime = Long.parseLong(tokenArray[3]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (id == 0 || expiredTime == 0)
            return AppContext.errorReturn(httpServletResponse, 2, "token验证失败");
        TokenUtil tokenUtil = new TokenUtil(id, tokenArray[1], tokenArray[2], expiredTime);
        try {
            //验证是否过期
            if (System.currentTimeMillis() > tokenUtil.getExpiredTime())
                return AppContext.errorReturn(httpServletResponse, 3, "token已过期");
            //验证用户名和密码是否正确
            User user = userService.getUserByAccount(tokenUtil.getUserName(), tokenUtil.getPassWord());
            if (user == null)
                return AppContext.errorReturn(httpServletResponse, 2, "token验证失败");
            if (user.getState() != 1)
                return AppContext.errorReturn(httpServletResponse, 2, "账号已停用");
            //验证token和uid是否对应
            if (user.getId() != uid)
                return AppContext.errorReturn(httpServletResponse, 2, "非法操作");
            //写入request中在controller中调用
            httpServletRequest.setAttribute("user", user);
        } catch (JsonSyntaxException e) {
            return AppContext.errorReturn(httpServletResponse, -1, "token验证失败");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
