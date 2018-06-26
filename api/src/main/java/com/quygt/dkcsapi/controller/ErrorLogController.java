package com.quygt.dkcsapi.controller;

import com.quygt.dkcs.model.AppErrorLog;
import com.quygt.dkcs.service.AppErrorLogService;
import com.quygt.dkcs.utils.ResponseMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping(value = "/errorlog",produces = "text/plain;charset=UTF-8")
public class ErrorLogController {
    @Resource
    private AppErrorLogService appErrorLogService;

    /**
     *  添加App错误日志
     *  URL：/errorlog/addErrorLog
     * @param userId 用户ID
     * @param userRealName 用户姓名
     * @param appType 设备类型：1=IOS，2=安卓
     * @param category 分类：1=网络错误，2=系统错误，3=其他错误
     * @param ip
     * @param errorUrl 接口地址
     * @param appIntro 设备信息描述格式：（手机型号：iPhone 6s；app版本号：3.21；操作系统版本：ios 10.2；网络：4G；运营商：移动；剩余内存大小：200MB；剩余磁盘空间大小：200MB）
     * @param errorIntro 错误内容格式：（功能：蜂文发布；错误：error 500）
     * @param sign 签名
     * @return 返回操作信息
     */
    @RequestMapping(value = "/addErrorLog", method = RequestMethod.POST)
    @ResponseBody
    public String addErrorLog(long userId,String userRealName,int appType,int category,String ip,String errorUrl,String appIntro,String errorIntro){
        if (appType !=1 && appType !=2)
            return ResponseMsg.New(-1,"设备类型不正确").asJson();
        if (category!=1&&category!=2&&category!=3)
            return ResponseMsg.New(-1,"分类不正确").asJson();
        if (appIntro.trim()=="")
            return ResponseMsg.New(-1,"设备信息描述为空").asJson();
        if (errorIntro.trim()=="")
            return ResponseMsg.New(-1,"错误内容为空").asJson();

        AppErrorLog model = new AppErrorLog();
        model.setUserId(userId);
        model.setUserRealName(userRealName);
        model.setAppType(appType);
        model.setCategory(category);
        model.setIp(ip);
        model.setErrorUrl(errorUrl);
        model.setAppIntro(appIntro);
        model.setErrorIntro(errorIntro);
        model.setIsRead(1);
        model.setCreateTime(new Date());
        boolean result = appErrorLogService.insert(model);
        if (result)
            return ResponseMsg.New(1, "App错误日志添加成功").asJson();
        else
            return ResponseMsg.New(0, "App错误日志添加失败").asJson();
    }
}
