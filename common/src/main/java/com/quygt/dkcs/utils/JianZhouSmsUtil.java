package com.quygt.dkcs.utils;

import com.jianzhou.sdk.BusinessService;
import com.quygt.dkcs.utils.model.JianZhou;
import com.quygt.dkcs.utils.model.JianZhouMsg;

public class JianZhouSmsUtil {

    public static JianZhouMsg sendMessage(String mobile, String message) {
        JianZhouMsg jianZhouMsg = new JianZhouMsg(false, "");
        if (mobile == "") {
            jianZhouMsg.setErrorMessage("手机号不能为空");
            return jianZhouMsg;
        }
        if (mobile == "") {
            jianZhouMsg.setErrorMessage("短信内容不能为空");
            return jianZhouMsg;
        }
        JianZhou jianZhou = new JianZhou();
        message = message + jianZhou.getSign();
        BusinessService businessService = new BusinessService();
        businessService.setWebService("http://www.jianzhou.sh.cn/JianzhouSMSWSServer/services/BusinessService");
        int result = businessService.sendBatchMessage(jianZhou.getAccount(), jianZhou.getPassword(), mobile, message);
        if (result > 0) {
            jianZhouMsg.setResult(true);
            return jianZhouMsg;
        } else {
            String errorMessage = "";
            switch (result) {
                case -1:
                    errorMessage = "余额不足";
                    break;
                case -2:
                    errorMessage = "帐号或密码错误";
                    break;
                case -3:
                    errorMessage = "连接服务商失败";
                    break;
                case -4:
                    errorMessage = "超时";
                    break;
                case -5:
                    errorMessage = "其他错误，一般为网络问题，IP受限等";
                    break;
                case -6:
                    errorMessage = "短信内容为空";
                    break;
                case -7:
                    errorMessage = "目标号码为空";
                    break;
                case -8:
                    errorMessage = "用户通道设置不对，需要设置三个通道";
                    break;
                case -9:
                    errorMessage = "捕获未知异常";
                    break;
                case -10:
                    errorMessage = "超过最大定时时间限制";
                    break;
                case -11:
                    errorMessage = "目标号码在黑名单里";
                    break;
                case -12:
                    errorMessage = "消息内容包含禁用词语";
                    break;
                case -13:
                    errorMessage = "没有权限使用该网关";
                    break;
                case -14:
                    errorMessage = "找不到对应的Channel ID";
                    break;
                case -17:
                    errorMessage = "没有提交权限，客户端帐号无法使用接口提交";
                    break;
                case -20:
                    errorMessage = "超速提交";
                    break;
                default:
                    errorMessage = "未知错误";
                    break;
            }
            jianZhouMsg.setErrorMessage(errorMessage);
            return jianZhouMsg;
        }
    }
}

