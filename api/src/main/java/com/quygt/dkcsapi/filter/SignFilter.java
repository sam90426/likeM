package com.quygt.dkcsapi.filter;

import com.quygt.dkcs.utils.ConfigUtil;
import com.quygt.dkcs.utils.Md5Util;
import com.quygt.dkcsapi.common.AppContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class SignFilter implements HandlerInterceptor  {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = httpServletRequest.getParameterMap();
        for (Iterator iterator = requestParams.keySet().iterator(); iterator.hasNext(); ) {
            String name = (String) iterator.next();
            String[] values = (String[]) requestParams.get(name);
            String value = "";
            for (int i = 0; i < values.length; i++)
                value = (i == values.length - 1) ? value + values[i] : value + values[i] + ",";
            params.put(name, value);
        }
        String sign = params.get("sign");
        if (sign == null || sign == "")
           return AppContext.errorReturn(httpServletResponse, -1, "sign为空");
        params.remove("sign");
        StringBuffer queryString = new StringBuffer();
        List<String> keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); ++i) {
            String key = (String) keys.get(i);
            String value = (String) params.get(key);
            queryString.append((i == 0 ? "" : "&") + key + "=" + value);
        }
        if (!Md5Util.md5(queryString.toString() + ConfigUtil.getInstance().getString("md5AppKey")).equals(sign))
           return AppContext.errorReturn(httpServletResponse, -1, "sign验证失败");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
