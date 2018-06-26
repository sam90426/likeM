package com.quygt.dkcs.utils.sms;


import com.alibaba.fastjson.JSONObject;
import com.quygt.dkcs.utils.ConfigUtil;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 天瑞短信接口
 */
public class TianRuiSms {

    private final static String accesskey = "zugjpavHCDpNgBjp"; //用户开发key

    private final static String accessSecret = "CNrRJa7XNnrCjRTLody8sArRIutsOzIs"; //用户开发秘钥

    private final static String SMS_POST_URL = "http://api.1cloudsp.com/api/v2/send";//发送短信接口

    private final static String sign = ConfigUtil.getInstance().getString("sms_sign");

    private static final Logger logger = LoggerFactory.getLogger(TianRuiSms.class);

    //普通短信

    /**
     * @param mobile  发送手机号；多个手机号用半角逗号隔开 至少一个手机号
     * @param type    发送类型
     * @param content 发送的短信内容是模板变量内容，多个变量中间用##或者$$隔开 选填
     * @throws Exception
     */
    public static Map<String, Object> sendSms(String mobile, String type, String content) {
        Map<String,Object> map=new HashMap<>();
        String templateId = "";
        switch (type) {
            case "register":        //注册
                templateId = TianRuiTemplate.REGISTER.getTemplateId();
                break;
            case "dynamic_login":     //登录验证码
                templateId = TianRuiTemplate.LOGIN.getTemplateId();
                break;
        }
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(SMS_POST_URL);
        postMethod.getParams().setContentCharset("UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        if (content == null) {
            content = "";
        }
        try {
            NameValuePair[] data = {
                    new NameValuePair("accesskey", accesskey),
                    new NameValuePair("secret", accessSecret),
                    new NameValuePair("sign", sign),
                    new NameValuePair("templateId", templateId),
                    new NameValuePair("mobile", mobile),
                    new NameValuePair("content", URLEncoder.encode(content, "utf-8"))//（示例模板：{1}您好，您的订单于{2}已通过{3}发货，运单号{4}）
            };
            postMethod.setRequestBody(data);
            int statusCode = httpClient.executeMethod(postMethod);

            System.err.println("statusCode: " + statusCode + ", body: "
                    + postMethod.getResponseBodyAsString());
            map=JSONObject.parseObject(postMethod.getResponseBodyAsString());
        } catch (IOException e) {
            map.put("code",1);
            map.put("msg","访问异常");
        }
        if(map==null){
            map=new HashMap<>();
        }
        if(map.get("code")==null){
            map.put("code",1);
            map.put("msg","访问失败");
        }
        return map;
    }

    /**
     * 封装为返回boolean值得发送普通短信
     * @param mobile
     * @param type
     * @param content
     * @return
     */
    public static boolean sendMsg(String mobile, String type, String content){
        Map<String,Object> map=TianRuiSms.sendSms(mobile,type,content);
        if(map.get("code")==0||map.get("code").equals("0")){
            return true;//成功状态
        }
        return false;
    }

    //个性短信
    public void sendsms2() throws Exception {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod("http://api.1cloudsp.com/api/v2/send");
        postMethod.getParams().setContentCharset("UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        //组装个性短信内容
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("13700000000", "先生##9:40##快递公司##1234567");
        jsonObj.put("13700000001", "女士##10:10##物流公司##000000");//（示例模板：{1}您好，您的订单于{2}已通过{3}发货，运单号{4}）

        NameValuePair[] data = {
                new NameValuePair("accesskey", accesskey),
                new NameValuePair("secret", accessSecret),
                new NameValuePair("sign", sign),
                new NameValuePair("templateId", "100"),
                new NameValuePair("data", URLEncoder.encode(jsonObj.toString(), "utf-8"))
        };
        postMethod.setRequestBody(data);

        int statusCode = httpClient.executeMethod(postMethod);
        System.out.println("statusCode: " + statusCode + ", body: "
                + postMethod.getResponseBodyAsString());
    }

    public static void getTemplate() throws Exception {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod("http://api.1cloudsp.com/query/templatelist");
        postMethod.getParams().setContentCharset("UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        NameValuePair[] data = {
                new NameValuePair("accesskey", accesskey),
                new NameValuePair("secret", accessSecret)
        };
        postMethod.setRequestBody(data);

        int statusCode = httpClient.executeMethod(postMethod);
        System.out.println("statusCode: " + statusCode + ", body: "
                + postMethod.getResponseBodyAsString());
    }

    public static void main(String[] args) throws Exception {
        TianRuiSms t = new TianRuiSms();
//        //普通短信
        Map map=t.sendSms("18357250335", "register", "2171");
        System.out.println("code"+map.get("code"));
        System.out.println("msg"+map.get("msg"));
        //个性短信
        // t.sendsms2();
    }
}
