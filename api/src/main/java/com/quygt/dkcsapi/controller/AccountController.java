package com.quygt.dkcsapi.controller;

import com.quygt.dkcs.model.*;
import com.quygt.dkcs.service.*;
import com.quygt.dkcs.utils.*;
import com.quygt.dkcs.utils.sms.SMS580Util;
import com.quygt.dkcs.utils.sms.TianRuiSms;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping(value = "/account",produces = "text/plain;charset=UTF-8")
public class AccountController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private RegisterUserService registerUserService;

    @Resource
    private SysDictionaryContentService sysDictionaryContentService;

    @Resource
    private ChannelService channelService;

    @Resource
    private UserOtherInfoService userOtherInfoService;

    @Resource
    private UserInviteService userInviteService;

    //登录接口
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String mobile) {
        return userService.insertOrSelect(mobile);
    }

    //刷新token接口
    @ResponseBody
    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    public String refreshToken(String userName, String passWord) {
        if (userName == "")
            return ResponseMsg.New(-1, "帐号不能为空").asJson();
        if (passWord == "")
            return ResponseMsg.New(-1, "密码不能为空").asJson();
        User user = userService.getUserByAccount(userName, passWord);
        if (user == null)
            return ResponseMsg.New(2, "验证失败").asJson();
        if (user.getState() != 1)
            return ResponseMsg.New(2, "帐号已停用").asJson();
        TokenUtil tokenUtil = new TokenUtil(user.getId(), user.getUserName(), user.getPassWord(), System.currentTimeMillis() + (24 * 60 * 60 * 1000));
        String token = tokenUtil.getToken();
        Map data = new HashMap<String, Object>();
        data.put("token", token);
        return ResponseMsg.New(1, "刷新token成功", data).asJson();
    }

    //获取登录短信验证码接口
    @ResponseBody
    @RequestMapping(value = "/getCode", method = RequestMethod.POST)
    public String getCode(String mobile) {
        return userService.getCode(mobile);
    }

    //注册
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "/account/register";
    }

    //注册
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String registerUser(String mobile,String code,String pwd,String channelId,String channelCode,String invitationCode,HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if(session==null){
            return ResponseMsg.New(-1, "验证码已过期或不存在").asJson();
        }
        Map map=(Map) session.getAttribute("smsMap");
        if(map==null){
            return ResponseMsg.New(-1, "验证码已失效").asJson();
        }
        if (!map.get("type").toString().equals("register")) {
            return ResponseMsg.New(-1, "请先发送注册验证码").asJson();
        }
        if(!mobile.equals(map.get("mobile").toString())){
            return ResponseMsg.New(-1, "请先发送验证码").asJson();
        }
        if(!code.equals(map.get("code").toString())){
            return ResponseMsg.New(-1, "验证码错误").asJson();
        }
        User user = userService.getUserByMobile(mobile);
        if(user!=null){
            return ResponseMsg.New(5, "已注册,请下载或打开app登录").asJson();
        }
        Channel channel=null;
        if(channelId!=null){
            channel=channelService.getById(channelId);
        }
        if(channel==null&&channelCode!=null){
            channel=channelService.getByCode(channelCode);
        }

        user = new User();
        user.setUserName(mobile);
        user.setPassWord(DesUtil.desEncrypt(ConfigUtil.getInstance().getString("desKey"), pwd));
        user.setRealName(mobile);
        user.setPicUrl("");
        user.setSex(1);
        user.setMobile(mobile);
        user.setLoginCount(0);
        user.setState(1);
        user.setLastLoginTime(new Date());
        user.setCreateTime(new Date());
        if(channel!=null){
            user.setChannelId(channel.getId());
            user.setChannelName(channel.getName());
        }
        boolean result = userService.insert(user);
        if(result){
            UserOtherInfo otherInfo=new UserOtherInfo();//新增注册用户其他信息
            otherInfo.setInvitationCode(userOtherInfoService.randomInvitationCode(6));
            otherInfo.setIsApp(0);//此接口暂时定为未下载过app
            otherInfo.setUserId(user.getId());
            userOtherInfoService.insert(otherInfo);

            if(StringUtils.isNotEmpty(invitationCode)){//邀请注册的
                UserOtherInfo search=new UserOtherInfo();
                search.setInvitationCode(invitationCode);
                UserOtherInfo userOtherInfo=userOtherInfoService.getmodel(search);//邀请人其他信息
                if(userOtherInfo!=null){
                    //邀请配置字典编码
                    String key = ConfigUtil.getInstance().getString("invitation_config");
                    SysDictionaryContent sysDictionaryContent=new SysDictionaryContent();
                    sysDictionaryContent.setDicPath(key);
                    List<SysDictionaryContent> list=sysDictionaryContentService.getList(sysDictionaryContent);
                    BigDecimal bounty=new BigDecimal("1");//默认为1 string3:邀请好友成功奖励金额
                    if(list!=null&&list.size()>0){//存在该配置
                        bounty=(StringUtils.isEmpty(list.get(0).getString3())?new BigDecimal("1"):new BigDecimal(list.get(0).getString3()));
                    }
                    UserInvite userInvite=new UserInvite();
                    userInvite.setInviteId(user.getId());
                    userInvite.setInviteName(user.getRealName());
                    userInvite.setUserId(userOtherInfo.getUserId());
                    userInvite.setInviteTime(new Date());
                    userInvite.setWithdrawalsState(0);
                    userInvite.setBounty(bounty);//奖励金
                    User user1=new User();
                    user1.setId(userOtherInfo.getUserId());
                    User user2=userService.getmodel(user1);//邀请人账户信息
                    if(user2!=null){
                        userInvite.setUserName(user2.getRealName());
                    }
                    userInviteService.insert(userInvite);
                }
            }
            return ResponseMsg.New(1, "注册成功，跳转中...").asJson();
        }else{
            return ResponseMsg.New(0, "注册失败请稍后重试").asJson();
        }
    }

    //验证码验证
    @RequestMapping(value = "/validateVcode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String validateVcode(HttpServletRequest request) throws Exception {
        String validateVcode = request.getParameter("key");
        if (validateVcode == null || validateVcode == "")
            return ResponseMsg.New(-1, "验证码为空").asJson();
        HttpSession session = request.getSession(true);
        if (session == null)
            return ResponseMsg.New(-1, "验证码为空").asJson();
        String vcode = session.getAttribute("vcode").toString();
        if (vcode == null || vcode == "")
            return ResponseMsg.New(-1, "验证码为空").asJson();
        if (!validateVcode.toLowerCase().equals(vcode.toLowerCase()))
            return ResponseMsg.New(-1, "验证码错误").asJson();
        return ResponseMsg.New(1, "验证成功").asJson();
    }

    //获取注册短信验证码
    @RequestMapping(value = "/getRegisterCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getRegisterCode(HttpServletRequest request) throws Exception {
        String mobile = request.getParameter("mobile");
        if (mobile == "")
            return ResponseMsg.New(-1, "手机不能为空").asJson();
        else {
            if (mobile.length() != 11 || !ValidateUtil.isMobile(mobile))
                return ResponseMsg.New(-1, "手机格式不正确").asJson();
        }
        User user = userService.getUserByMobile(mobile);
        if(user!=null){
            return ResponseMsg.New(2, "该账号已注册，请直接下载/打开App登录").asJson();
        }
        String code = RandomUtil.number(4);
        String message = ConfigUtil.getInstance().getString("sms_sign")+String.format(ConfigUtil.getInstance().getString("register"), code);

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
            flag= TianRuiSms.sendMsg(mobile,"register",code);//1=天瑞；2=580
        }else{
            flag= SMS580Util.sendSms(mobile,message);
        }

        if(flag){
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("code", code);
            HttpSession session = request.getSession(true);
            session.setAttribute("code", code);
            Map<String,Object> map=new HashMap<>();
            map.put("type","register");
            map.put("code", code);
            map.put("mobile",mobile);
            session.setAttribute("smsMap", map);
            session.setMaxInactiveInterval(60*5);//有效期5分钟
            return ResponseMsg.New(1, "获取验证码成功", data).asJson();
        }else{
            return ResponseMsg.New(0,"短信发送失败").asJson();
        }
    }

    //提交申请
    @RequestMapping(value = "/registerSubmit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String registerSubmit(HttpServletRequest request) throws Exception {
        String mobile = request.getParameter("mobile");
        if (mobile == "")
            return ResponseMsg.New(-1, "手机不能为空").asJson();
        else {
            if (mobile.length() != 11 || !ValidateUtil.isMobile(mobile))
                return ResponseMsg.New(-1, "手机格式不正确").asJson();
        }
        String yzm = request.getParameter("yzm");
        if (yzm == "")
            return ResponseMsg.New(-1, "手机验证码不能为空").asJson();
        HttpSession session = request.getSession(true);
        if (session == null)
            return ResponseMsg.New(-1, "验证码验证失败").asJson();
        String code = session.getAttribute("code").toString();
        if (!code.equals(yzm))
            return ResponseMsg.New(-1, "验证码不正确").asJson();
        String key = request.getParameter("key");
        RegisterUser registerUser = new RegisterUser();
        registerUser.setMobile(mobile);
        RegisterUser registerUser1 = registerUserService.getmodel(registerUser);
        if (registerUser1 != null && registerUser1.getId() > 0)
            return ResponseMsg.New(1, "申请成功").asJson();
        registerUser.setAgentKey(key);
        registerUser.setCreateTime(new Date());
        boolean result = registerUserService.insert(registerUser);
        if (!result)
            return ResponseMsg.New(0, "申请失败").asJson();
        return ResponseMsg.New(1, "申请成功").asJson();
    }

}
