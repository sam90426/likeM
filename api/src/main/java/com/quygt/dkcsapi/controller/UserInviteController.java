package com.quygt.dkcsapi.controller;

import com.quygt.dkcs.model.UserInvite;
import com.quygt.dkcs.service.UserInviteService;
import com.quygt.dkcs.utils.PageUtil;
import com.quygt.dkcsapi.common.ServletUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project:dkcs
 * @PackageName:com.quygt.dkcsapi.controller
 * @Author:fujian
 * @CreationDate:2018年01月22日16:17
 */
@RestController
@RequestMapping(value = "/userInvite", produces = "text/plain;charset=UTF-8")
public class UserInviteController {

    @Resource
    private UserInviteService userInviteService;

    /**
     * 邀请记录
     * @param uid
     * @return
     */
    @RequestMapping(value = "/myInvite", method = RequestMethod.POST)
    @ResponseBody
    public void myInvite(Long uid, HttpServletResponse response) {
        List<Map<String,Object>> mapList=userInviteService.selectCountAll(uid);
        Map<String,Object> json=new HashMap<>();
        json.put("numOk",0);
        json.put("numNo",0);
        json.put("numAll",0);
        if(mapList!=null){
            for (int i = 0; i < mapList.size(); i++) {
                Integer num;
                if(mapList.get(i).get("count")!=null){
                    num=Integer.parseInt(mapList.get(i).get("count").toString());
                    if(mapList.get(i).get("type").toString().equals("0")){
                        json.put("numNo",num);
                    }else if(mapList.get(i).get("type").toString().equals("1")){
                        json.put("numOk",num);
                    }else if(mapList.get(i).get("type").toString().equals("2")){
                        json.put("numAll",num);
                    }
                }

            }
        }

        Map<String,Object> result=new HashMap<>();
        result.put("code", 1);
        result.put("message", "success");
        result.put("data", json);
        ServletUtils.writeToResponse(response,result);
    }

    /**
     * 邀请记录
     * @param userInvite
     * @return
     */
    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    @ResponseBody
    public void selectList(UserInvite userInvite,Long uid,Integer currPage, Integer pageSize,String type, HttpServletResponse response) {
        if(userInvite==null){
            userInvite=new UserInvite();
        }
        userInvite.setUserId(uid);
        PageUtil<UserInvite> list=userInviteService.selectListAll(userInvite,currPage,pageSize,type);
        Map<String,Object> result=new HashMap<>();
        result.put("code", 1);
        result.put("message", "success");
        result.put("data", list);
        ServletUtils.writeToResponse(response,result);
    }
}
