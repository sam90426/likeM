package com.quygt.dkcs.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quygt.dkcs.model.*;
import com.quygt.dkcs.service.*;
import com.quygt.dkcs.utils.*;
import com.quygt.dkcs.utils.excel.JsGridReportBase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.quygt.dkcs.utils.JsonUtil.Encode;
import static com.quygt.dkcs.utils.JsonUtil.fromJson;

@Controller
@RequestMapping(value = "/user", produces = "text/plain;charset=UTF-8")
public class UserController extends BaseController {
    @Resource
    private AdminUserService adminUserService;
    @Resource
    private UserService userService;
    @Resource
    private AdminUserLoginLogService adminUserLoginLogService;
    @Resource
    private UserLoginLogService userLoginLogService;

    @Resource
    private ChannelService channelService;

    //region 后台用户

    //region 后台用户列表

    //region 列表页面
    @RequestMapping(value = "/adminuserlist")
    public String AdminUserList() {
        return "user/adminuserlist";
    }
    //endregion

    //region 列表获取数据
    @RequestMapping(value = "/adminuserlistp", method = RequestMethod.POST)
    @ResponseBody
    public String AdminUserListP(HttpServletRequest request) throws Exception {
        //查询条件
        String searchtype = request.getParameter("searchtype");
        String key = request.getParameter("key");
        //分页
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex")) + 1;
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //字段排序
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        AdminUser adminUser = new AdminUser();
        if (key != null&&key!="") {
            if (searchtype.equals("1")) {
                adminUser.setUserName(key);
            } else if (searchtype.equals("2")) {
                adminUser.setRealName(key);
            }
        }
        PageUtil<AdminUser> pagelist = adminUserService.getpagelist(adminUser, pageIndex, pageSize);
        HashMap result = new HashMap();
        result.put("data", pagelist.getData());
        result.put("total", pagelist.getTotalItems());
        String json = Encode(result);
        return json;
    }
    //endregion

    //region 更改状态
    @RequestMapping(value = "/upstate", method = RequestMethod.POST)
    @ResponseBody
    public String upstate(HttpServletRequest request) throws Exception {
        String state = request.getParameter("state");
        String id = request.getParameter("id");
        if (id == null || Long.parseLong(id) < 1)
            return ResponseMsg.New(0, "请选择记录").asJson();
        if (state == null || Integer.parseInt(state) < 0)
            return ResponseMsg.New(0, "操作失败").asJson();
        AdminUser adminUser = new AdminUser();
        adminUser.setState(Integer.parseInt(state));
        adminUser.setId(Long.parseLong(id));
        boolean result = false;
        result = adminUserService.update(adminUser);
        if (result)
            return ResponseMsg.New(1, "保存成功").asJson();
        else
            return ResponseMsg.New(0, "保存失败").asJson();
    }
    //endregion

    //endregion

    //region 后台用户编辑

    //region 页面加载
    @RequestMapping(value = "/adminuseredit")
    public String AdminUserEdit() {
        return "user/adminuseredit";
    }
    //endregion

    //region 数据加载
    @RequestMapping(value = "/adminusereditpl")
    @ResponseBody
    public String AdminUserEditPL(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        AdminUser adminUser = new AdminUser();
        if (id != null) {
            adminUser.setId(Long.parseLong(id));
        }
        adminUser = adminUserService.getmodel(adminUser);
        String key = ConfigUtil.getInstance().getString("desKey");
        String newpwd = DesUtil.desDecrypt(key, adminUser.getPassWord());
        adminUser.setPassWord(newpwd);
        String json = Encode(adminUser);
        System.out.println(json);
        return json;
    }
    //endregion

    //region 保存数据
    @RequestMapping(value = "/adminusereditsave", method = RequestMethod.POST)
    @ResponseBody
    public String AdminUserEditSave(HttpServletRequest request) throws Exception {
        String data = request.getParameter("data");
        AdminUser adminUser = fromJson(data, AdminUser.class);
        String key = ConfigUtil.getInstance().getString("desKey");
        String pwd = adminUser.getPassWord();
        String newpwd = DesUtil.desEncrypt(key, pwd);
        adminUser.setPassWord(newpwd);
        boolean result = false;
        if (adminUser.getId() > 0) {
            result = adminUserService.update(adminUser);
        } else {
            adminUser.setLoginCount(0);
            adminUser.setCreateTime(new Date());
            result = adminUserService.add(adminUser);
        }
        if (result)
            return ResponseMsg.New(1, "保存成功").asJson();
        else
            return ResponseMsg.New(0, "保存失败").asJson();
    }
    //endregion

    //endregion

    //region 后台用户登录日志

    //region 页面加载
    @RequestMapping(value = "/adminuserloginlog")
    public String adminuserloginlog() {
        return "/user/adminuserloginlog";
    }
    //endregion

    //region 页面数据
    @RequestMapping(value = "/adminuserloginlogp", method = RequestMethod.POST)
    @ResponseBody
    public String AdminUserLoginLogP(HttpServletRequest request) throws Exception {
        String searchtype = request.getParameter("searchtype");
        String key = request.getParameter("key");
        //分页
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex")) + 1;
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        AdminUserLoginLog adminUserLoginLog = new AdminUserLoginLog();
        if (key != null&&key!="") {
            if (searchtype.equals("1")) {
                adminUserLoginLog.setAdminUserName(key);
            } else if (searchtype.equals("2")) {
                adminUserLoginLog.setAdminRealName(key);
            }
        }

        PageUtil<AdminUserLoginLog> pagelist = adminUserLoginLogService.getpagelist(adminUserLoginLog, pageIndex, pageSize);
        HashMap result = new HashMap();
        result.put("data", pagelist.getData());
        result.put("total", pagelist.getTotalItems());
        String json = Encode(result);
        return json;
    }
    //endregion

    //endregion

    //endregion

    //region app用户列表
    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public ModelAndView userList() {
        ModelAndView modelAndView = new ModelAndView("user/userlist");
        List<Channel> list=channelService.getSearchList(null);
        JSONArray jsonArray=new JSONArray();
        JSONObject json=new JSONObject();
        json.put("id",0);
        json.put("text","全部");
        jsonArray.add(json);
        if(list!=null&&list.size()>0){
            for (int i = 0; i <list.size() ; i++) {
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("id",list.get(i).getId());
                jsonObject.put("text",list.get(i).getName());
                jsonArray.add(jsonObject);
            }
        }
        String str=jsonArray.toString();
        modelAndView.addObject("jsonObject",str);
        return modelAndView;
    }

    //region app获取用户列表
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ResponseBody
    public String getUserList(HttpServletRequest request,User user) throws Exception {
        int pageIndex = ParseUtil.toInt(request.getParameter("pageIndex"), 0) + 1;
        int pageSize = ParseUtil.toInt(request.getParameter("pageSize"), 20);
        int searchtype = ParseUtil.toInt(request.getParameter("searchtype"), 0);
        String key = request.getParameter("key");
        if (searchtype > 0) {
            if (searchtype == 1)
                user.setUserName(key);
            else if (searchtype == 2)
                user.setRealName(key);
        }
        PageUtil<User> userList = userService.getPageList(user, pageIndex, pageSize);
        Map data = new HashMap();
        data.put("data", userList.getData());
        data.put("total", userList.getTotalItems());
        return JsonUtil.Encode(data);
    }

    /**
     * @param user       注入 startTime and endTime
     * @param searchtype 搜索类型 1登录名；2真实姓名
     * @param key        搜索关键字
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/getUserListExport")
    @ResponseBody
    public void getUserListExport( User user,Integer searchtype,String key,HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (searchtype !=null&&searchtype > 0) {
            if (searchtype == 1) {
                user.setUserName(key);
            }else if (searchtype == 2){
                user.setRealName(key);
            }
        }
        String AdminUserName =SessionHelp.GetSessionByEncrypt(request, "AdminUserName");
        List users=userService.selectListAll(user);
        response.setContentType("application/msexcel;charset=UTF-8");
        String title = "用户信息Excel表";
        String[] hearders = {"登录名", "真实姓名", "性别", "手机号","状态", "登录次数", "最后登录时间", "注册时间"};
        String[] fields = {"userName", "realName", "sexStr", "mobile", "stateStr","loginCount", "lastLoginTime", "createTime"};
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportExcel(users, title, hearders, fields, AdminUserName);
    }
    //endregion

    //region app用户修改用户状态
    @RequestMapping(value = "/updateUserState", method = RequestMethod.POST)
    @ResponseBody
    public String updateUserState(HttpServletRequest request) throws Exception {
        int state = ParseUtil.toInt(request.getParameter("state"), 0);
        long id = ParseUtil.toLong(request.getParameter("id"), 0);
        User user = new User();
        user.setState(state);
        user.setId(id);
        boolean result = false;
        result = userService.update(user);
        if (result)
            return ResponseMsg.New(1, "保存成功").asJson();
        else
            return ResponseMsg.New(0, "保存失败").asJson();
    }
    //endregion

    //region app用户新增/编辑
    @RequestMapping(value = "/userEdit", method = RequestMethod.GET)
    public String userEdit() {
        return "user/useredit";
    }
    //endregion

    //region 加载app用户信息
    @RequestMapping(value = "/userEditLoad", method = RequestMethod.POST)
    @ResponseBody
    public String userEditLoad(HttpServletRequest request) throws Exception {
        long id = ParseUtil.toLong(request.getParameter("id"), 0);
        User user = new User();
        user.setId(id);
        user = userService.getmodel(user);
        user.setPassWord(DesUtil.desDecrypt(ConfigUtil.getInstance().getString("desKey"), user.getPassWord()));
        return JsonUtil.Encode(user);
    }
    //endregion

    //region 保存app用户信息
    @RequestMapping(value = "/userEditSave", method = RequestMethod.POST)
    @ResponseBody
    public String userEditSave(HttpServletRequest request) throws Exception {
        String data = request.getParameter("data");
        User user = JsonUtil.fromJson(data, User.class);
        user.setPassWord(DesUtil.desEncrypt(ConfigUtil.getInstance().getString("desKey"), user.getPassWord()));
        boolean result = false;
        if (user.getId() > 0) {
            result = userService.update(user);
        } else {
            user.setPicUrl("");
            user.setLoginCount(0);
            user.setCreateTime(new Date());
            user.setLastLoginTime(null);
            result = userService.insert(user);
        }
        if (result)
            return ResponseMsg.New(1, "保存成功").asJson();
        else
            return ResponseMsg.New(0, "保存失败").asJson();
    }
    //endregion

    //region app用户登录日志

    //region 页面加载
    @RequestMapping(value = "/userloginlog")
    public String userloginlog() {
        return "/user/userloginlog";
    }
    //endregion

    //region 页面数据
    @RequestMapping(value = "/userloginlogp", method = RequestMethod.POST)
    @ResponseBody
    public String UserLoginLogP(HttpServletRequest request) throws Exception {
        String searchtype = request.getParameter("searchtype");
        String key = request.getParameter("key");
        //分页
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex")) + 1;
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        UserLoginLog userLoginLog = new UserLoginLog();
        if (key != null&&key!="") {
            if (searchtype.equals("1")) {
                userLoginLog.setUserName(key);
            } else if (searchtype.equals("2")) {
                userLoginLog.setRealName(key);
            }
        }

        PageUtil<UserLoginLog> pagelist = userLoginLogService.getpagelist(userLoginLog, pageIndex, pageSize);
        HashMap result = new HashMap();
        result.put("data", pagelist.getData());
        result.put("total", pagelist.getTotalItems());
        String json = Encode(result);
        return json;
    }
    //endregion

    //endregion

    //endregion
}
