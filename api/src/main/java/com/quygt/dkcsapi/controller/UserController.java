package com.quygt.dkcsapi.controller;

import com.quygt.dkcs.model.UserInfo;
import com.quygt.dkcs.service.UserInfoService;
import com.quygt.dkcsapi.common.ServletUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: wuxianxin
 * @Date: 2018/7/10 16:27
 * @Description:
 */
@RequestMapping(value = "/user", produces = "text/plain;charset=UTF-8")
public class UserController extends BaseController {

    @Resource
    private UserInfoService userInfoService;

    //region 更新个性签名

    /**
     * @param userId
     * @param content
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/updateSign", method = RequestMethod.POST)
    public void updateSign(@RequestParam(value = "userId", required = true) Long userId,
                           @RequestParam(value = "content", required = true) String content,
                           HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (content.isEmpty()) {
            result.put("code", 400);
            result.put("msg", "请输入个性签名内容");
        } else {
            UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
            if (userInfo == null) {
                result.put("code", 400);
                result.put("msg", "用户不存在");
            } else {
                userInfo.setSign(content);
                userInfoService.update(userInfo);
                result.put("code", 200);
                result.put("msg", "保存成功");
            }
        }
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 更新昵称

    /**
     * @param userId
     * @param nickName
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/updateNickName", method = RequestMethod.POST)
    public void updateNickName(@RequestParam(value = "userId", required = true) Long userId,
                               @RequestParam(value = "nickName", required = true) String nickName,
                               HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (nickName.isEmpty()) {
            result.put("code", 400);
            result.put("msg", "请输入用户名称");
        } else {
            UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
            if (userInfo == null) {
                result.put("code", 400);
                result.put("msg", "用户不存在");
            } else {
                userInfo.setUserName(nickName);
                userInfoService.update(userInfo);
                result.put("code", 200);
                result.put("msg", "保存成功");
            }
        }

        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 更换头像

    //endregion

    //region 修改密码

    /**
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public void updatePwd(@RequestParam(value = "userId", required = true) Long userId,
                          @RequestParam(value = "oldPwd", required = true) String oldPwd,
                          @RequestParam(value = "newPwd", required = true) String newPwd,
                          HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
        if (userInfo == null) {
            result.put("code", 400);
            result.put("msg", "用户不存在");
        } else {
            if (userInfo.getPassword().equals(oldPwd)) {
                userInfo.setPassword(newPwd);
                userInfoService.update(userInfo);
                result.put("code", 200);
                result.put("msg", "保存成功");
            } else {
                result.put("code", 400);
                result.put("msg", "旧密码不正确");
            }
        }

        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 绑定手机

    /**
     *
     * @param userId
     * @param mobile
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/updateMobile",method = RequestMethod.POST)
    public void updateMobile(@RequestParam(value = "userId",required = true)Long userId,
                             @RequestParam(value = "mobile",required = true)String mobile,
                             HttpServletResponse response)throws Exception{
        Map<String,Object> result=new HashMap<>();

        ServletUtils.writeToResponse(response,result);
    }
    //endregion

}
