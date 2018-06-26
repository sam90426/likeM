package com.quygt.dkcsapi.controller;

import com.quygt.dkcs.model.User;
import com.quygt.dkcs.model.UserLoginLog;
import com.quygt.dkcs.service.UserLoginLogService;
import com.quygt.dkcs.service.UserService;
import com.quygt.dkcs.utils.ResponseMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping(value = "/user",produces = "text/plain;charset=UTF-8")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserLoginLogService userLoginLogService;

    @RequestMapping(value = "/addLoginLog", method = RequestMethod.POST)
    @ResponseBody
    public String addLoginLog(long uid, String version, String deviceInfo) {
        if (version == "")
            return ResponseMsg.New(-1, "版本号不能为空").asJson();
        if (deviceInfo == "")
            return ResponseMsg.New(-1, "设备信息不能为空").asJson();
        User user = (User) (RequestContextHolder.getRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_REQUEST));
        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setUserId(user.getId());
        userLoginLog.setUserName(user.getUserName());
        userLoginLog.setRealName(user.getRealName());
        userLoginLog.setState(1);
        userLoginLog.setIsApp(1);
        userLoginLog.setVersion(version);
        userLoginLog.setDeviceInfo(deviceInfo);
        userLoginLog.setCreateTime(new Date());
        boolean result = userLoginLogService.insert(userLoginLog);
        if (!result)
            return ResponseMsg.New(0, "添加用户登录日志失败").asJson();
        //修改登录次数、最后登录时间
        user.setLoginCount(user.getLoginCount() + 1);
        user.setLastLoginTime(new Date());
        userService.update(user);
        return ResponseMsg.New(1, "添加用户登录日志成功").asJson();
    }
}
