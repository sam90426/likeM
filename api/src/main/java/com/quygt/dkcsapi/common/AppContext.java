package com.quygt.dkcsapi.common;

import com.quygt.dkcs.utils.ResponseMsg;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AppContext {
    public static boolean errorReturn(HttpServletResponse httpServletResponse, int code, String message) {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = httpServletResponse.getWriter();
            writer.print(ResponseMsg.New(code, message).asJson());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
        return false;
    }
}
