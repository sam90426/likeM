package com.wxx.like.api.controller;

import com.wxx.like.model.UserInfo;
import com.wxx.like.service.UserInfoService;
import com.wxx.like.api.common.ServletUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/account", produces = "text/plain;charset=UTF-8")
public class AccountController extends BaseController {

    @Resource
    private UserInfoService userInfoService;

    //region 登陆

    /**
     * @param mobile
     * @param password
     * @param response
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public void login(@RequestParam(value = "mobile", required = true) String mobile,
                      @RequestParam(value = "password", required = true) String password,
                      HttpServletResponse response) throws Exception{
        Map<String, Object> result = new HashMap<>();
        if (mobile.isEmpty() || password.isEmpty()) {
            result.put("code", 400);
            result.put("msg", "账号或密码不能为空");
        } else {
            UserInfo userInfo = userInfoService.findUserByLoginName(mobile);
            if (userInfo == null) {
                result.put("code", 400);
                result.put("msg", "该账号未注册");
            } else {
                if (password.equals(userInfo.getPassword())) {
                    result.put("code", 200);
                    result.put("msg", "登陆成功");
                    result.put("data", userInfo);
                } else {
                    result.put("code", 400);
                    result.put("msg", "密码错误");
                }
            }
        }

        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 注册

    /**
     * 注册
     *
     * @param mobile
     * @param password
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public void register(@RequestParam(value = "mobile", required = true) String mobile,
                         @RequestParam(value = "password", required = true) String password,
                         HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (mobile.isEmpty() || password.isEmpty()) {
            result.put("code", 400);
            result.put("msg", "账号或密码不能为空");
        } else {
            UserInfo userInfo = userInfoService.findUserByLoginName(mobile);
            if (userInfo != null) {
                result.put("code", 400);
                result.put("msg", "该账号已存在");
            } else {
                userInfo = new UserInfo();
                userInfo.setLoginName(mobile);
                userInfo.setPassword(password);
                userInfo.setUserName(mobile);
                userInfo.setSign("赶快更新你的个性签名吧");
                userInfo.setMobile(mobile);
                userInfo.setSex(1);
                userInfo.setState(1);
                userInfo.setLevel(1);
                userInfo.setExperience(0);
                userInfo.setCreateTime(new Date());
                boolean save = userInfoService.save(userInfo);
                if (save) {
                    result.put("code", 200);
                    result.put("msg", "注册成功");
                } else {
                    result.put("code", 400);
                    result.put("msg", "注册失败");
                }
            }
        }

        ServletUtils.writeToResponse(response, result);
    }    //endregion

    //region 找回密码

    /**
     * 找回密码
     *
     * @param mobile
     * @param password
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/user/findpwd", method = RequestMethod.POST)
    @ResponseBody
    public void findPwd(@RequestParam(value = "mobile", required = true) String mobile,
                        @RequestParam(value = "password", required = true) String password,
                        HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (mobile.isEmpty() || password.isEmpty()) {
            result.put("code", 400);
            result.put("msg", "账号或密码不能为空");
        } else {
            UserInfo userInfo = userInfoService.findUserByLoginName(mobile);
            if (userInfo == null) {
                result.put("code", 400);
                result.put("msg", "该账号不存在");
            } else {
                userInfo.setPassword(password);
                boolean save = userInfoService.update(userInfo);
                if (save) {
                    result.put("code", 200);
                    result.put("msg", "找回成功");
                } else {
                    result.put("code", 400);
                    result.put("msg", "找回失败");
                }
            }
        }

        ServletUtils.writeToResponse(response, result);
    }
    //endregion
}
