package com.quygt.dkcs.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CookieHelp {

    private static final int MAX_AGE=518400;//6天有效

    //region 设置Cookie
    public static void addCookie(HttpServletResponse response, String name,
                                 String value) {
        try {
            Cookie cookie = new Cookie(name, value);
            cookie.setMaxAge(MAX_AGE);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception ex) {

        }
    }
    //endregion

    //region 获取Cookie
    public static String getCookieByName(HttpServletRequest request,
                                         String name) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies || cookies.length == 0) return null;
        String string = null;
        try {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                String cname = cookie.getName();
                if (cname.equals(name)) {
                    string = cookie.getValue();
                }
            }
        } catch (Exception ex) {
        }
        return string;
    }
    //endregion

    //region 清空Cookie
    public static boolean clearCookie(HttpServletRequest request,
                                      HttpServletResponse response, String name) {
        boolean bool = false;
        Cookie[] cookies = request.getCookies();
        if (null == cookies || cookies.length == 0) return bool;
        try {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = new Cookie(name, null);
                cookie.setMaxAge(0);
                cookie.setPath("/");// 根据你创建cookie的路径进行填写
                response.addCookie(cookie);
                bool = true;
            }
        } catch (Exception ex) {
        }
        return bool;
    }
    //endregion

    //region 根据传入字段从Cookie中解析出对应的值
    public static String GetCookieByEncrypt(HttpServletRequest request, String name) {
        if (name.isEmpty())
            return null;
        String cookiestr = getCookieByName(request, "dkcs");
        if(cookiestr==null) return null;
        cookiestr = DesUtil.desDecrypt(ConfigUtil.getInstance().getString("cookieKey"), cookiestr);
        if (cookiestr.indexOf('&') == -1) return null;
        for (int i = 0; i < cookiestr.split("&").length; i++) {
            if (cookiestr.split("&")[i].indexOf(name + "=") == 0) {
                return cookiestr.split("&")[i].split("=")[1];
            }
        }
        return null;
    }
    //endregion

    //region 根据传入字段加密保存
    public static void WriteCookieByEncrypt(HttpServletResponse response,HashMap hashMap) {
        if (hashMap == null)
            return;
        String userData = "";
        Iterator iter = hashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            userData = userData + entry.getKey().toString() + "=" + entry.getValue().toString() + "&";
        }
        userData=DesUtil.desEncrypt(ConfigUtil.getInstance().getString("cookieKey"),userData.substring(0,userData.length()-1));
        addCookie(response,"dkcs",userData);
    }
    //endregion
}
