package com.wxx.like.api.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Auther: wuxianxin
 * @Date: 2018/8/13 13:38
 * @Description:
 */
@Controller
@Scope("prototype")
public class VideoReadController extends BaseController {
    private static final Logger logger = Logger.getLogger(String.valueOf(VideoReadController.class));

    @RequestMapping(value = "/readVideo.htm")
    public String readImg(HttpServletRequest request, HttpServletResponse response) {

        //读取文件相对路径
        String strPath = request.getParameter("path");
        String suffix = "";

        if (strPath.lastIndexOf(".") != -1) {
            suffix = strPath.substring(strPath.lastIndexOf(".") + 1);
        }
        File inFile = new File(strPath);
        BufferedInputStream is = null;
        FileInputStream fiStream = null;
        try {
            try {
                fiStream = new FileInputStream(inFile);
            } catch (IOException e) {
                logger.log(Level.INFO, strPath + " " + e.getMessage());

            }
            final long fileLen = fiStream.available();
            String range = request.getHeader("Range");
            response.setHeader("Accept-Ranges", "bytes");
            OutputStream out = response.getOutputStream();
            if (range == null) {

                range = "bytes=0-";
            }

            long start = Integer.valueOf(range.substring(range.indexOf("=") + 1, range.indexOf("-")));
            long count = fileLen - start;
            long end;
            if (range.endsWith("-")) {
                end = fileLen - 1;
            } else {
                end = Integer.valueOf(range.substring(range.indexOf("-") + 1));
            }
            String ContentRange = "bytes " + String.valueOf(start) + "-" + end + "/" + String.valueOf(fileLen);
            response.setStatus(206);
            response.setContentType("video/mpeg4");
            response.setHeader("Content-Range", ContentRange);

            byte[] buffer = new byte[1024 * 10];
            int length = 0;
            fiStream.skip(start);

            try {

                while ((length = fiStream.read(buffer)) != -1) {
                    out.write(buffer, 0, length);

                }

            } catch (Exception e) {

                logger.log(Level.INFO, e.toString());
            }


            fiStream.close();
            fiStream = null;

            out.close();
            out = null;
        } catch (Exception e) {

        }
        return null;
    }

}
