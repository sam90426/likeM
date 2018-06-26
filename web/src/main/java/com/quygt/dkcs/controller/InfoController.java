package com.quygt.dkcs.controller;

import com.quygt.dkcs.model.*;
import com.quygt.dkcs.service.*;
import com.quygt.dkcs.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.quygt.dkcs.utils.JsonUtil.Encode;
import static com.quygt.dkcs.utils.JsonUtil.fromJson;

@Controller
@RequestMapping(value = "/info", produces = "text/plain;charset=UTF-8")
public class InfoController extends BaseController {

    @Resource
    private InfoGGDicService infoGGDicService;
    @Resource
    private InfoGGDicContentService infoGGDicContentService;
    @Resource
    private InfoHelpDicService infoHelpDicService;
    @Resource
    private InfoHelpDicContentService infoHelpDicContentService;
    @Resource
    private InfoNewsService infoNewsService;
    @Resource
    private InfoSuggestService infoSuggestService;

    //region 广告位

    //region 广告列表

    //region 广告列表加载
    @RequestMapping(value = "/ggdiclist")
    public String ggdiclist() {
        return "info/ggdic/ggdiclist";
    }
    //endregion

    //region 获取数据
    @RequestMapping(value = "/ggdiclistp", method = RequestMethod.POST)
    @ResponseBody
    public String ggdiclistp(HttpServletRequest request) throws Exception {
        String key = request.getParameter("key");

        InfoGGDic infoGGDic = new InfoGGDic();
        if (key != null && key != "") {
            infoGGDic.setDicName(key);
        }

        List<InfoGGDic> list = infoGGDicService.gettreelist(infoGGDic);
        String json = Encode(list);
        return json;
    }
    //endregion

    //region 删除
    @RequestMapping(value = "/ggdicdel", method = RequestMethod.POST)
    @ResponseBody
    public String ggdicdel(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        if (id != null && id != "") {
            boolean result = false;
            result = infoGGDicService.deleteById(Long.parseLong(id));
            if (result)
                return ResponseMsg.New(1, "删除成功").asJson();
            else
                return ResponseMsg.New(0, "删除失败").asJson();
        } else {
            return ResponseMsg.New(0, "请选择记录").asJson();
        }

    }
    //endregion

    //endregion

    //region 广告编辑

    //region 页面加载
    @RequestMapping(value = "/ggdicedit")
    public String ggdicedit() {
        return "info/ggdic/ggdicedit";
    }
    //endregion

    //region 获取页面数据
    @RequestMapping(value = "/ggdiceditp", method = RequestMethod.POST)
    @ResponseBody
    public String ggdiceditp(HttpServletRequest request) throws Exception {

        String id = request.getParameter("id");
        InfoGGDic infoGGDic = new InfoGGDic();
        if (id != null && id != "")
            infoGGDic.setId(Long.parseLong(id));
        infoGGDic = infoGGDicService.getmodel(infoGGDic);

        InfoGGDic newinfo = new InfoGGDic();
        newinfo.setDicPath(infoGGDic.getDicPath().substring(0, 2));
        newinfo = infoGGDicService.getmodel(newinfo);
        if (newinfo != null)
            infoGGDic.setDicPath(infoGGDic.getDicPath() + "|" + newinfo.getDicName());
        String json = Encode(infoGGDic);
        return json;
    }
    //endregion

    //region 保存
    @RequestMapping(value = "/ggdiceditsave", method = RequestMethod.POST)
    @ResponseBody
    public String ggdiceditsave(HttpServletRequest request) throws Exception {

        String data = request.getParameter("data");
        InfoGGDic infoGGDic = fromJson(data, InfoGGDic.class);

        boolean result = false;
        if (infoGGDic.getId() > 0) {
            //region 路径判断
            InfoGGDic newgg=new InfoGGDic();
            newgg.setId(infoGGDic.getId());
            newgg=infoGGDicService.getmodel(newgg);
            String oldpath=newgg.getDicPath();
            String _oldpath=newgg.getDicPath();
            if(oldpath.length()>1)
                oldpath=oldpath.substring(0,oldpath.length()-3);
            if(!infoGGDic.getDicPath().equals(oldpath)){
                String path = GetGGPath(infoGGDic.getDicPath());//新路径
                infoGGDic.setDicPath(path);
                //region 移动该字典下的所有字典内容
                InfoGGDicContent ggcon=new InfoGGDicContent();
                ggcon.setDicPath(_oldpath);
                List<InfoGGDicContent> list=infoGGDicContentService.getList(ggcon);
                for (InfoGGDicContent item:list) {
                    item.setDicPath(path);
                    infoGGDicContentService.update(item);
                }
                //endregion
            }else{
                infoGGDic.setDicPath(_oldpath);
            }
            //endregion
            result = infoGGDicService.update(infoGGDic);
        } else {
            String path = infoGGDic.getDicPath();
            infoGGDic.setDicPath(GetGGPath(path));
            infoGGDic.setId(0);
            infoGGDic.setCreateTime(new Date());
            result = infoGGDicService.add(infoGGDic);
        }

        if (result)
            return ResponseMsg.New(1, "保存成功").asJson();
        else
            return ResponseMsg.New(0, "保存失败").asJson();
    }
    //endregion

    //region 计算节点
    private String GetGGPath(String path) {
        InfoGGDic infoGGDic = new InfoGGDic();
        infoGGDic.setDicPath(path);
        infoGGDic.setOrderIndex(path.length() + 3);
        String newpath = infoGGDicService.getmax(infoGGDic);
        if (newpath != null) {
            String max_Str = newpath;
            max_Str = max_Str.substring(max_Str.length() - 3);
            String cStr = Long.toString(Long.parseLong(max_Str) + 1);
            if (cStr.length() == 2)
                cStr = "0" + cStr;
            else if (cStr.length() == 1)
                cStr = "00" + cStr;
            path = newpath.substring(0, newpath.length() - 3) + cStr;
        } else {//初始值
            if (path.equals(""))
                path = "101";
            else
                path = path + "001";
        }
        return path;
    }
    //endregion
    //endregion

    //region 广告内容列表

    //region 列表加载
    @RequestMapping(value = "/ggdiccontentlist")
    public String ggdiccontentlist(HttpServletRequest request, ModelMap model) {
        String path = request.getParameter("path");
        model.addAttribute("path", path);
        return "info/ggdic/ggdiccontentlist";
    }
    //endregion

    //region 列表数据加载
    @RequestMapping(value = "ggdiccontentlistp", method = RequestMethod.POST)
    @ResponseBody
    public String ggdiccontentlistp(HttpServletRequest request) throws Exception {

        String path = request.getParameter("path");
        //分页
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex")) + 1;
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //字段排序
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");

        InfoGGDicContent infoGGDicContent = new InfoGGDicContent();
        if (path != null && path != "") {
            infoGGDicContent.setDicPath(path);
        }
        PageUtil<InfoGGDicContent> pagelist = infoGGDicContentService.getpagelist(infoGGDicContent, pageIndex, pageSize);
        HashMap result = new HashMap();
        result.put("data", pagelist.getData());
        result.put("total", pagelist.getTotalItems());
        String json = Encode(result);

        return json;
    }
    //endregion

    //region 删除
    @RequestMapping(value = "/ggdiccontentdel", method = RequestMethod.POST)
    @ResponseBody
    public String ggdiccontentdel(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String[] ids = id.split(",");
        if (ids.length > 0) {
            for (String delid : ids) {
                infoGGDicContentService.deleteById(Long.parseLong(delid));
            }
            return ResponseMsg.New(1, "删除成功").asJson();
        } else {
            return ResponseMsg.New(0, "请选择记录").asJson();
        }
    }
    //endregion

    //endregion

    //region 广告内容编辑

    //region 页面加载
    @RequestMapping(value = "ggdiccontentedit")
    public String ggdiccontentedit() {
        return "info/ggdic/ggdiccontentedit";
    }
    //endregion

    //region 页面数据获取
    @RequestMapping(value = "ggdiccontenteditp", method = RequestMethod.POST)
    @ResponseBody
    public String ggdiccontenteditp(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        InfoGGDicContent infoGGDicContent = new InfoGGDicContent();
        infoGGDicContent.setId(Long.parseLong(id));
        infoGGDicContent = infoGGDicContentService.getmodel(infoGGDicContent);
        String json = Encode(infoGGDicContent);
        return json;
    }
    //endregion

    //region 保存
    @RequestMapping(value = "/ggdiccontenteditsave", method = RequestMethod.POST)
    @ResponseBody
    public String ggdiccontenteditsave(HttpServletRequest request) throws Exception {
        String data = request.getParameter("data");
        InfoGGDicContent infoGGDicContent = fromJson(data, InfoGGDicContent.class);
        boolean result = false;
        if (infoGGDicContent.getId() > 0) {
            result = infoGGDicContentService.update(infoGGDicContent);
        } else {
            infoGGDicContent.setId(0);
            infoGGDicContent.setAdminUserId(Long.parseLong(CookieHelp.GetCookieByEncrypt(request,"AdminUserID")));
            infoGGDicContent.setAdminRealName(CookieHelp.GetCookieByEncrypt(request,"AdminUserName"));
            infoGGDicContent.setCreateTime(new Date());
            infoGGDicContent.setNumber1(0);
            infoGGDicContent.setFloat1(0);
            result = infoGGDicContentService.add(infoGGDicContent);
        }
        if (result)
            return ResponseMsg.New(1, "保存成功").asJson();
        else
            return ResponseMsg.New(0, "保存失败").asJson();
    }
    //endregion

    //endregion

    //endregion

    //region 帮助中心

    //region 帮助中心列表

    //region 帮助中心列表加载
    @RequestMapping(value = "/helpdiclist")
    public String helpdiclist() {
        return "/info/helpdic/helpdiclist";
    }
    //endregion

    //region 帮助中心列表数据加载
    @RequestMapping(value = "/helpdiclistp", method = RequestMethod.POST)
    @ResponseBody
    public String helpdiclistp(HttpServletRequest request) throws Exception {

        String key = request.getParameter("key");
        InfoHelpDic infoHelpDic = new InfoHelpDic();
        if (key != null && key != "")
            infoHelpDic.setDicName(key);
        List<InfoHelpDic> list = infoHelpDicService.gettreelist(infoHelpDic);
        String json = Encode(list);
        return json;
    }
    //endregion

    //region 删除
    @RequestMapping(value = "helpdicdel", method = RequestMethod.POST)
    @ResponseBody
    public String helpdicdel(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        if (id != null && id != "") {
            if (infoHelpDicService.deleteById(Long.parseLong(id)))
                return ResponseMsg.New(1, "删除成功").asJson();
            else
                return ResponseMsg.New(0, "删除失败").asJson();
        } else
            return ResponseMsg.New(0, "请选择记录").asJson();
    }
    //endregion

    //endregion

    //region 帮助中心编辑

    //region 编辑页面加载
    @RequestMapping(value = "/helpdicedit")
    public String helpdicedit() {
        return "/info/helpdic/helpdicedit";
    }
    //endregion

    //region 编辑页面数据加载
    @RequestMapping(value = "/helpdiceditp")
    @ResponseBody
    public String helpdiceditp(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");

        InfoHelpDic infoHelpDic = new InfoHelpDic();
        if (id != null && id != "")
            infoHelpDic.setId(Long.parseLong(id));
        infoHelpDic = infoHelpDicService.getmodel(infoHelpDic);

        InfoHelpDic newinfo = new InfoHelpDic();
        newinfo.setDicPath(infoHelpDic.getDicPath().substring(0, 2));
        newinfo = infoHelpDicService.getmodel(newinfo);
        if (newinfo != null)
            infoHelpDic.setDicPath(infoHelpDic.getDicPath() + "|" + newinfo.getDicName());
        String json = Encode(infoHelpDic);
        return json;

    }
    //endregion

    //region 保存
    @RequestMapping(value = "/helpdiceditsave")
    @ResponseBody
    public String helpdiceditsave(HttpServletRequest request) throws Exception {
        String data = request.getParameter("data");
        InfoHelpDic infoHelpDic = fromJson(data, InfoHelpDic.class);
        boolean result = false;
        if (infoHelpDic.getId() > 0) {
            //region 路径判断
            InfoHelpDic newhelp=new InfoHelpDic();
            newhelp.setId(infoHelpDic.getId());
            newhelp=infoHelpDicService.getmodel(newhelp);
            String oldpath=newhelp.getDicPath();
            String _oldpath=newhelp.getDicPath();
            if(oldpath.length()>1)
                oldpath=oldpath.substring(0,oldpath.length()-3);
            if(!infoHelpDic.getDicPath().equals(oldpath)){
                String path = GetHelpPath(infoHelpDic.getDicPath());//新路径
                infoHelpDic.setDicPath(path);
                //region 移动该字典下的所有字典内容
                InfoHelpDicContent helpcon=new InfoHelpDicContent();
                helpcon.setDicPath(_oldpath);
                List<InfoHelpDicContent> list=infoHelpDicContentService.getList(helpcon);
                for (InfoHelpDicContent item:list) {
                    item.setDicPath(path);
                    infoHelpDicContentService.update(item);
                }
                //endregion
            }else{
                infoHelpDic.setDicPath(_oldpath);
            }
            //endregion
            result = infoHelpDicService.update(infoHelpDic);
        } else {
            String path = infoHelpDic.getDicPath();
            infoHelpDic.setDicPath(GetHelpPath(path));
            infoHelpDic.setId(0);
            infoHelpDic.setCreateTime(new Date());
            result = infoHelpDicService.add(infoHelpDic);
        }
        if (result)
            return ResponseMsg.New(1, "保存成功").asJson();
        else
            return ResponseMsg.New(0, "保存失败").asJson();
    }
    //endregion

    //region 计算节点
    private String GetHelpPath(String path) {
        InfoHelpDic infoHelpDic = new InfoHelpDic();
        infoHelpDic.setDicPath(path);
        infoHelpDic.setOrderIndex(path.length() + 3);
        String newpath = infoHelpDicService.getmax(infoHelpDic);
        if (newpath != null) {
            String max_Str = newpath;
            max_Str = max_Str.substring(max_Str.length() - 3);
            String cStr = Long.toString(Long.parseLong(max_Str) + 1);
            if (cStr.length() == 2)
                cStr = "0" + cStr;
            else if (cStr.length() == 1)
                cStr = "00" + cStr;
            path = newpath.substring(0, newpath.length() - 3) + cStr;
        } else {//初始值
            if (path.equals(""))
                path = "101";
            else
                path = path + "001";
        }
        return path;
    }
    //endregion

    //endregion

    //region 帮助中心内容列表

    //region 页面加载
    @RequestMapping(value = "/helpdiccontentlist")
    public String helpdiccontentlist() {
        return "/info/helpdic/helpdiccontentlist";
    }
    //endregion

    //region 数据加载
    @RequestMapping(value = "/helpdiccontentlistp", method = RequestMethod.POST)
    @ResponseBody
    public String helpdiccontentlistp(HttpServletRequest request) throws Exception {
        String path = request.getParameter("path");
        //分页
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex")) + 1;
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //字段排序
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");

        InfoHelpDicContent infoHelpDicContent = new InfoHelpDicContent();
        if (path != null && path != "") {
            infoHelpDicContent.setDicPath(path);
        }
        PageUtil<InfoHelpDicContent> pagelist = infoHelpDicContentService.getpagelist(infoHelpDicContent, pageIndex, pageSize);
        HashMap result = new HashMap();
        result.put("data", pagelist.getData());
        result.put("total", pagelist.getTotalItems());
        String json = Encode(result);

        return json;
    }
    //endregion

    //region 删除
    @RequestMapping(value = "/helpdiccontentdel", method = RequestMethod.POST)
    @ResponseBody
    public String helpdiccontentdel(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String[] ids = id.split(",");
        if (ids.length > 0) {
            for (String delid : ids) {
                infoHelpDicContentService.deleteById(Long.parseLong(delid));
            }
            return ResponseMsg.New(1, "删除成功").asJson();
        } else {
            return ResponseMsg.New(0, "请选择记录").asJson();
        }
    }
    //endregion

    //endregion

    //region 帮助中心内容编辑

    //region 页面加载
    @RequestMapping(value = "helpdiccontentedit")
    public String helpdiccontentedit() {
        return "/info/helpdic/helpdiccontentedit";
    }
    //endregion

    //region 页面数据获取
    @RequestMapping(value = "helpdiccontenteditp", method = RequestMethod.POST)
    @ResponseBody
    public String helpdiccontenteditp(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        InfoHelpDicContent infoHelpDicContent = new InfoHelpDicContent();
        infoHelpDicContent.setId(Long.parseLong(id));
        infoHelpDicContent = infoHelpDicContentService.getmodel(infoHelpDicContent);
        String json = Encode(infoHelpDicContent);
        return json;
    }
    //endregion

    //region 保存
    @RequestMapping(value = "/helpdiccontenteditsave", method = RequestMethod.POST)
    @ResponseBody
    public String helpdiccontenteditsave(HttpServletRequest request) throws Exception {
        String data = request.getParameter("data");
        InfoHelpDicContent infoHelpDicContent = fromJson(data, InfoHelpDicContent.class);
        boolean result = false;
        if (infoHelpDicContent.getId() > 0) {
            result = infoHelpDicContentService.update(infoHelpDicContent);
        } else {
            infoHelpDicContent.setId(0);
            infoHelpDicContent.setAdminUserId(Long.parseLong(CookieHelp.GetCookieByEncrypt(request,"AdminUserID")));
            infoHelpDicContent.setAdminRealName(CookieHelp.GetCookieByEncrypt(request,"AdminUserName"));
            infoHelpDicContent.setCreateTime(new Date());
            result = infoHelpDicContentService.add(infoHelpDicContent);
        }
        if (result)
            return ResponseMsg.New(1, "保存成功").asJson();
        else
            return ResponseMsg.New(0, "保存失败").asJson();
    }
    //endregion

    //endregion

    //endregion

    //region 极光推送

    //region 页面加载
    @RequestMapping(value = "/sendpush")
    public String sendpush(){
        return "/info/push/sendpush";
    }
    //endregion

    //region 发送push
    @RequestMapping(value = "/getpush",method = RequestMethod.POST)
    @ResponseBody
    public String getpush(HttpServletRequest request){
        String content=request.getParameter("content");
        String apptype=request.getParameter("pushObj");
        String pushtype=request.getParameter("pushObjPar");
        String pushusers=request.getParameter("pushObjParValue");
        String pushurl=request.getParameter("addValue1");

        boolean result= JPushUtil.sendPush(Integer.parseInt(apptype),content,Integer.parseInt(pushtype),pushurl,pushusers.split(","));
        if(result)
            return ResponseMsg.New(1,"发送成功").asJson();
        else
            return ResponseMsg.New(0,"发送失败").asJson();
    }
    //endregion

    //endregion

    //region 资讯

    //region 资讯列表

    //region 页面加载
    @RequestMapping(value = "/newslist")
    public String newslist(){
        return "/info/news/newslist";
    }
    //endregion

    //region 获取数据
    @RequestMapping(value = "/newslistp",method = RequestMethod.POST)
    @ResponseBody
    public String newslistp(HttpServletRequest request)throws Exception{

        int type=Integer.parseInt(request.getParameter("Type"));
        int state=Integer.parseInt(request.getParameter("State"));
        int pageIndex = ParseUtil.toInt(request.getParameter("pageIndex"), 0) + 1;
        int pageSize = ParseUtil.toInt(request.getParameter("pageSize"), 20);
        InfoNews infoNews=new InfoNews();
        if(type>0) {
            infoNews.setType(type);
        }
        if(state>0) {
            infoNews.setState(state);
        }
        PageUtil<InfoNews> page=infoNewsService.getpagelist(infoNews, pageIndex, pageSize);
        Map data = new HashMap();
        data.put("data", page.getData());
        data.put("total", page.getTotalItems());
        return Encode(data);
    }
    //endregion

    //region 删除
    @RequestMapping(value = "/newsdel", method = RequestMethod.POST)
    @ResponseBody
    public String newsdel(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String[] ids = id.split(",");
        if (ids.length > 0) {
            for (String delid : ids) {
                infoNewsService.deleteById(Long.parseLong(delid));
            }
            return ResponseMsg.New(1, "删除成功").asJson();
        } else {
            return ResponseMsg.New(0, "请选择记录").asJson();
        }
    }
    //endregion

    //endregion

    //region 资讯编辑
    @RequestMapping(value = "/newsedit")
    public String newsedit(){
        return "/info/news/newsedit";
    }

    //region 获取页面数据
    @RequestMapping(value = "/newseditp", method = RequestMethod.POST)
    @ResponseBody
    public String newseditp(HttpServletRequest request) throws Exception {

        String id = request.getParameter("id");
        InfoNews infoNews = new InfoNews();
        if (id != null && id != "")
            infoNews.setId(Long.parseLong(id));
        infoNews = infoNewsService.getmodel(infoNews);
        String json = Encode(infoNews);
        return json;
    }
    //endregion

    //region 保存
    @RequestMapping(value = "/newseditsave", method = RequestMethod.POST)
    @ResponseBody
    public String newseditsave(HttpServletRequest request) throws Exception {

        String data = request.getParameter("data");
        InfoNews infoNews = fromJson(data, InfoNews.class);

        boolean result = false;
        if (infoNews.getId() > 0) {
            result = infoNewsService.update(infoNews);
        } else {
            infoNews.setId(0);
            infoNews.setAdminUserId(Long.parseLong(CookieHelp.GetCookieByEncrypt(request,"AdminUserID")));
            infoNews.setAdminRealName(CookieHelp.GetCookieByEncrypt(request,"AdminUserName"));
            infoNews.setCreateTime(new Date());
            result = infoNewsService.add(infoNews);
        }

        if (result)
            return ResponseMsg.New(1, "保存成功").asJson();
        else
            return ResponseMsg.New(0, "保存失败").asJson();
    }
    //endregion
    //endregion

    //endregion

    //region 反馈信息

    //region 页面加载
    @RequestMapping(value = "/suggestlist")
    public String suggestlist(){
        return "/info/suggest/suggestlist";
    }
    //endregion

    //region 获取数据
    @RequestMapping(value = "/suggestlistp",method = RequestMethod.POST)
    @ResponseBody
    public String suggestlistp(HttpServletRequest request)throws Exception{

        int state=Integer.parseInt(request.getParameter("State"));
        int pageIndex = ParseUtil.toInt(request.getParameter("pageIndex"), 0) + 1;
        int pageSize = ParseUtil.toInt(request.getParameter("pageSize"), 20);
        InfoSuggest infoSuggest=new InfoSuggest();
        if(state>0) {
            infoSuggest.setIsRead(state);
        }
        PageUtil<InfoSuggest> page=infoSuggestService.getpagelist(infoSuggest, pageIndex, pageSize);
        Map data = new HashMap();
        data.put("data", page.getData());
        data.put("total", page.getTotalItems());
        return Encode(data);
    }
    //endregion

    //region 删除
    @RequestMapping(value = "/suggestdel", method = RequestMethod.POST)
    @ResponseBody
    public String suggestdel(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String[] ids = id.split(",");
        if (ids.length > 0) {
            for (String delid : ids) {
                infoSuggestService.deleteById(Long.parseLong(delid));
            }
            return ResponseMsg.New(1, "删除成功").asJson();
        } else {
            return ResponseMsg.New(0, "请选择记录").asJson();
        }
    }
    //endregion

    //region 查看
    @RequestMapping(value = "/suggestedit")
    public String suggestedit(){
        return "/info/suggest/suggestedit";
    }

    //region 获取页面数据
    @RequestMapping(value = "/suggesteditp", method = RequestMethod.POST)
    @ResponseBody
    public String suggesteditp(HttpServletRequest request) throws Exception {

        String id = request.getParameter("id");
        InfoSuggest infoSuggest = new InfoSuggest();
        if (id != null && id != "")
            infoSuggest.setId(Long.parseLong(id));
        infoSuggest = infoSuggestService.getmodel(infoSuggest);
        String json = Encode(infoSuggest);
        infoSuggest.setIsRead(1);
        infoSuggestService.update(infoSuggest);
        return json;
    }
    //endregion
    //endregion

    //endregion
}
