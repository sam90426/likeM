package com.wxx.like.api.common;

import com.wxx.like.utils.VerifyCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class code extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        HttpSession session = request.getSession(true);
        session.removeAttribute("vcode");
        session.setAttribute("vcode", verifyCode.toLowerCase());
        int w = 141, h = 63;
        VerifyCodeUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
    }
}
