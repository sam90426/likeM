package com.quygt.dkcs.utils.sms;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SMS580Util {

    //请求url
    private final static String SMS_POST_URL = "http://119.23.249.213:8888/sms.aspx";
    //企业ID
    private final static String SMS_USERID = "385";
    //账号
    private final static String SMS_ACCOUNT = "zhouzonghy";
    //密码
    private final static String SMS_PASSWORD = "123456";
    //action
    private final static String SMS_ACTION = "send";
    //扩展子号
    private final static String SMS_EXTNO = "";

    //region 发送POST方法的请求

    /**
     * 发送POST请求
     *
     * @param mobiles 发信发送的目的号码.多个号码之间用半角逗号隔开
     * @param content 短信内容
     * @return 远程响应结果
     */
    public static boolean sendSms(String mobiles, String content) {

        //region 参数为空判断
        if (mobiles.isEmpty())
            return false;
        if (content.isEmpty())
            return false;
        //endregion

        String[] mobilearray = mobiles.split(",");
        int mobilecount = mobilearray.length;

        //region 请求参数
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("action", SMS_ACTION);
        parameters.put("userid", SMS_USERID);
        parameters.put("account", SMS_ACCOUNT);
        parameters.put("password", SMS_PASSWORD);
        parameters.put("mobile", mobiles);
        parameters.put("content", content);
        parameters.put("sendTime", "");
        parameters.put("extno", SMS_EXTNO);
        //endregion

        //region 发送请求
        String result = "";// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        PrintWriter out = null;
        StringBuffer sb = new StringBuffer();// 处理请求参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(SMS_POST_URL);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn
                    .getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //endregion

        boolean issuccess = false;

        //region 返回数据解析(result)
        Map resultmap = readStringXmlOut(result);
        if (resultmap.isEmpty()) {
            issuccess = false;
        } else {
            if (resultmap.get("returnstatus").equals("Success")) {
                if (resultmap.get("message").equals("ok")) {
                    issuccess = true;
                } else {
                    issuccess = false;
                }
            } else {
                issuccess = false;
            }
        }
        //endregion

        return issuccess;
    }
    //endregion

    //region xml解析

    /**
     * @param xml
     * @return Map
     * @description 将xml字符串转换成map
     */
    private static Map readStringXmlOut(String xml) {
        Map map = new HashMap();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            map.put("returnstatus", rootElt.elementText("returnstatus"));
            map.put("message", rootElt.elementText("message"));
            map.put("remainpoint", rootElt.elementText("remainpoint"));
            map.put("taskID", rootElt.elementText("taskID"));
            map.put("successCounts", rootElt.elementText("successCounts"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    //endregion
}
