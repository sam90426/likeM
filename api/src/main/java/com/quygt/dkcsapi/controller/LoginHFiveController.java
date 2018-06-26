package com.quygt.dkcsapi.controller;

import com.quygt.dkcs.service.RegisterUserService;
import com.quygt.dkcs.service.UserService;
import com.quygt.dkcs.utils.ResponseMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/11/21.
 */
@Controller
@RequestMapping(value = "/hfive/account", produces = "text/plain;charset=UTF-8")
public class LoginHFiveController {
    @Resource
    private UserService userService;

    //登录接口
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String mobile) {
        return userService.insertOrSelect(mobile);
    }

    //获取登录短信验证码接口
    @ResponseBody
    @RequestMapping(value = "/getCode", method = RequestMethod.POST)
    public String getCode(String mobile) {
        try {
            return userService.getCode(mobile);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMsg.New(0,"短信发送异常").asJson();
        }
    }

    //注册
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "/account/register";
    }

}
