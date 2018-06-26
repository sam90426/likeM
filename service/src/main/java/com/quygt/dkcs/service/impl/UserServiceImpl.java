package com.quygt.dkcs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.UserDao;
import com.quygt.dkcs.dao.UserOtherInfoDao;
import com.quygt.dkcs.model.SysDictionaryContent;
import com.quygt.dkcs.model.User;
import com.quygt.dkcs.model.UserOtherInfo;
import com.quygt.dkcs.service.SysDictionaryContentService;
import com.quygt.dkcs.service.UserOtherInfoService;
import com.quygt.dkcs.service.UserService;
import com.quygt.dkcs.utils.*;
import com.quygt.dkcs.utils.sms.SMS580Util;
import com.quygt.dkcs.utils.sms.TianRuiSms;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private SysDictionaryContentService sysDictionaryContentService;

    @Resource
    private UserOtherInfoService userOtherInfoService;

    public User getUserByMobile(String mobile) {
        return userDao.getUserByMobile(mobile);
    }

    public User getUserByAccount(String userName, String passWord) {
        return userDao.getUserByAccount(userName,passWord);
    }

    public boolean insert(User user) {
        return userDao.insert(user);
    }

    public boolean update(User user) {
        return userDao.update(user);
    }

    public PageUtil<User> getPageList(User user, int currPage, int pageSize) {
        Page<User> page = PageHelper.startPage(currPage, pageSize);
        userDao.getlist(user);
        PageUtil<User> data = new PageUtil<User>(currPage, pageSize, page.getPages(), page.getTotal(), page.getResult());
        return data;
    }

    public User getmodel(User user) {
        return userDao.getmodel(user);
    }

    /**
     * 获取登录验证码
     * @param mobile
     * @return
     */
    public String getCode(String mobile){
        if (mobile == "")
            return ResponseMsg.New(-1, "手机不能为空").asJson();
        else {
            if (mobile.length() != 11 || !ValidateUtil.isMobile(mobile))
                return ResponseMsg.New(-1, "手机格式不正确").asJson();
        }
        String code = RandomUtil.number(4);
        String message = ConfigUtil.getInstance().getString("sms_sign")+String.format(ConfigUtil.getInstance().getString("dynamic_login"), code);

        //短信渠道商类型的字典编码
        String key = ConfigUtil.getInstance().getString("sms_channel");
        SysDictionaryContent sysDictionaryContent=new SysDictionaryContent();
        sysDictionaryContent.setDicPath(key);
        List<SysDictionaryContent> list=sysDictionaryContentService.getList(sysDictionaryContent);
        Integer sms_channel_num=2;//默认为2
        if(list!=null&&list.size()>0){//存在该配置
            sms_channel_num=list.get(0).getZs1()==0?2:list.get(0).getZs1();
        }
        boolean flag=false;
        if(sms_channel_num==1){
            flag= TianRuiSms.sendMsg(mobile,"dynamic_login",code);//1=天瑞；2=580
        }else{
            flag= SMS580Util.sendSms(mobile,message);
        }
        if(flag){
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("code", code);
            return ResponseMsg.New(1, "获取验证码成功", data).asJson();
        }else{
            return ResponseMsg.New(0,"短信发送失败").asJson();
        }
    }

    /**
     * 登录
     * @param mobile
     * @return
     */
    public String insertOrSelect(String mobile){
        if (mobile == "")
            return ResponseMsg.New(-1, "手机不能为空").asJson();
        else {
            if (mobile.length() != 11 || !ValidateUtil.isMobile(mobile))
                return ResponseMsg.New(-1, "手机格式不正确").asJson();
        }
        User user = userDao.getUserByMobile(mobile);
        UserOtherInfo userOtherInfo=null;
        if (user != null) {
            if (user.getState() != 1) {
                return ResponseMsg.New(0, "帐号已停用").asJson();
            }
            userOtherInfo=userOtherInfoService.getByUserId(user.getId());
            if(userOtherInfo==null){
                userOtherInfo=new UserOtherInfo();
                userOtherInfo.setInvitationCode(userOtherInfoService.randomInvitationCode(6));
                userOtherInfo.setIsApp(1);
                userOtherInfo.setUserId(user.getId());
                userOtherInfoService.insert(userOtherInfo);
            }else{
                if(userOtherInfo.getIsApp()==null||userOtherInfo.getIsApp()!=1){//设置为已下载过app
                    UserOtherInfo userOtherInfo1=new UserOtherInfo();
                    userOtherInfo1.setIsApp(1);
                    userOtherInfo1.setId(userOtherInfo.getId());
                    userOtherInfoService.updateById(userOtherInfo1);
                    userOtherInfo.setIsApp(1);
                }
            }
        } else {
            user = new User();
            user.setUserName(mobile);
            user.setPassWord(DesUtil.desEncrypt(ConfigUtil.getInstance().getString("desKey"), "123456"));
            user.setRealName(mobile);
            user.setPicUrl("");
            user.setSex(1);
            user.setMobile(mobile);
            user.setLoginCount(0);
            user.setState(1);
            user.setLastLoginTime(new Date());
            user.setCreateTime(new Date());
            boolean result = userDao.insert(user);
            if (!result) {
                return ResponseMsg.New(0, "登录失败").asJson();
            }
            userOtherInfo=new UserOtherInfo();//新增注册用户其他信息
            userOtherInfo.setInvitationCode(userOtherInfoService.randomInvitationCode(6));
            userOtherInfo.setIsApp(1);
            userOtherInfo.setUserId(user.getId());
            userOtherInfoService.insert(userOtherInfo);
        }
        TokenUtil tokenUtil = new TokenUtil(user.getId(), user.getUserName(), user.getPassWord(), System.currentTimeMillis() + (24 * 60 * 60 * 1000));
        String token = tokenUtil.getToken();
        Map data = new HashMap<String, Object>();
        data.put("token", token);
        data.put("user", user);
        data.put("userOtherInfo", userOtherInfo);
        return ResponseMsg.New(1, "登录成功", data).asJson();
    }

    /**
     * 查询用户信息列表 未分页
     * @param user
     * @return
     */
    public List<User> selectListAll(User user){
        return userDao.selectListAll(user);
    }
}
