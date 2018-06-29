package com.quygt.dkcsapi.controller;

import com.quygt.dkcsapi.common.ServletUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/account", produces = "text/plain;charset=UTF-8")
public class AccountController extends BaseController {

    //region 登陆
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public void login(@RequestParam(value = "mobile",required = true) String mobile,
                      @RequestParam(value = "password",required = true)String password,
                      HttpServletResponse response){
        Map<String,Object> result=new HashMap<>();

        ServletUtils.writeToResponse(response,result);
    }
    //endregion

}
