package com.quygt.dkcs.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SessionHelp {

    //region 设置Session
    public static void addSession(HttpServletRequest request,String name, String value){
        HttpSession session = request.getSession(true);
        session.setAttribute(name,value);
    }
    //endregion

    //region 获取Session
    public static String getSession(HttpServletRequest request,String name)
    {
        HttpSession session = request.getSession(true);
         Object result=session.getAttribute(name);
        if(result==null)
            return null;
        else{

         return result.toString();
        }
    }
    //endregion

    //region 清空Session
    public static void removeSession(HttpServletRequest request,String name)
    {
        HttpSession session = request.getSession(true);
        session.removeAttribute(name);
    }
    //endregion

    //region 根据传入字段从Cookie中解析出对应的值
    public static String GetSessionByEncrypt(HttpServletRequest request, String name) {
        if (name.isEmpty())
            return null;
        String sessionstr = getSession(request, "dkcs");
        if(sessionstr==null) return null;
        sessionstr = DesUtil.desDecrypt(ConfigUtil.getInstance().getString("cookieKey"), sessionstr);
        if (sessionstr.indexOf('&') == -1) return null;
        for (int i = 0; i < sessionstr.split("&").length; i++) {
            if (sessionstr.split("&")[i].indexOf(name + "=") == 0) {
                return sessionstr.split("&")[i].split("=")[1];
            }
        }
        return null;
    }
    //endregion

    //region 根据传入字段加密保存
    public static void WriteSessionByEncrypt(HttpServletRequest request,HashMap hashMap) {
        if (hashMap == null)
            return;
        String userData = "";
        Iterator iter = hashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            userData = userData + entry.getKey().toString() + "=" + entry.getValue().toString() + "&";
        }
        userData=DesUtil.desEncrypt(ConfigUtil.getInstance().getString("cookieKey"),userData.substring(0,userData.length()-1));
        addSession(request,"dkcs",userData);
    }
    //endregion
}
