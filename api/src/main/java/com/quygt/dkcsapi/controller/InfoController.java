package com.quygt.dkcsapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.quygt.dkcs.model.*;
import com.quygt.dkcs.service.*;
import com.quygt.dkcs.utils.ConfigUtil;
import com.quygt.dkcs.utils.Md5Util;
import com.quygt.dkcs.utils.PageUtil;
import com.quygt.dkcs.utils.ResponseMsg;
import com.quygt.dkcsapi.common.ServletUtils;
import com.sun.org.glassfish.external.probe.provider.annotations.ProbeParam;
import javafx.collections.ObservableMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping(value = "/info", produces = "text/plain;charset=UTF-8")
public class InfoController extends BaseController {
    @Resource
    private InfoGGDicContentService infoGGDicContentService;
    @Resource
    private InfoHelpDicContentService infoHelpDicContentService;
    @Resource
    private InfoNewsService infoNewsService;
    @Resource
    private SupplyService supplyService;
    @Resource
    private InfoSuggestService infoSuggestService;

    //region 获取广告Banner
    @RequestMapping(value = "/getbanner", method = RequestMethod.POST)
    @ResponseBody
    public String getBanner() {
        String key = ConfigUtil.getInstance().getString("indexBanner");
        if (!key.isEmpty()) {
            Map data = new HashMap<String, Object>();
            InfoGGDicContent infoGGDicContent = new InfoGGDicContent();
            infoGGDicContent.setDicPath(key);
            infoGGDicContent.setState(1);
            List<InfoGGDicContent> list = infoGGDicContentService.getList(infoGGDicContent);
            data.put("data", list);
            return ResponseMsg.New(1, "success", data).asJson();
        } else {
            return ResponseMsg.New(-1, "获取失败").asJson();
        }

    }
    //endregion

    //region 获取信用人生广告Banner
    @RequestMapping(value = "/getdkdrbanner", method = RequestMethod.POST)
    @ResponseBody
    public String getdkdrbanner() {
        String key = ConfigUtil.getInstance().getString("dkdrindexBanner");
        if (!key.isEmpty()) {
            Map data = new HashMap<String, Object>();
            InfoGGDicContent infoGGDicContent = new InfoGGDicContent();
            infoGGDicContent.setDicPath(key);
            infoGGDicContent.setState(1);
            List<InfoGGDicContent> list = infoGGDicContentService.getList(infoGGDicContent);
            data.put("data", list);
            return ResponseMsg.New(1, "success", data).asJson();
        } else {
            return ResponseMsg.New(-1, "获取失败").asJson();
        }

    }
    //endregion

    //region APP启动广告
    @RequestMapping(value = "/getlaunchbanner", method = RequestMethod.POST)
    @ResponseBody
    public String getLaunchBanner() {
        String key = ConfigUtil.getInstance().getString("applaunchbanner");
        if (!key.isEmpty()) {
            Map data = new HashMap<String, Object>();
            InfoGGDicContent infoGGDicContent = new InfoGGDicContent();
            infoGGDicContent.setDicPath(key);
            infoGGDicContent.setState(1);
            infoGGDicContent = infoGGDicContentService.getmodel(infoGGDicContent);
            data.put("data", infoGGDicContent);
            return ResponseMsg.New(1, "success", data).asJson();
        } else {
            return ResponseMsg.New(-1, "获取失败").asJson();
        }

    }
    //endregion

    //region H5页面
    @RequestMapping(value = "/registerprotocol")
    public String registerprotocol(Model model, HttpServletRequest request) {
        String data = request.getParameter("type");
        int type = 0;
        if (data != null && data != "")
            type = Integer.parseInt(data);
        String path = "";
        if (type == 0)
            path = ConfigUtil.getInstance().getString("registerprotocol");
        else if (type == 1)
            path = ConfigUtil.getInstance().getString("registerprotocoltg");
        InfoHelpDicContent infoHelpDicContent = new InfoHelpDicContent();
        infoHelpDicContent.setDicPath(path);
        infoHelpDicContent = infoHelpDicContentService.getmodel(infoHelpDicContent);
        model.addAttribute("infodata", infoHelpDicContent);
        return "/h5/registerprotocol";
    }
    //endregion

    //region 生成签名也面
    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    public String sign() {
        return "info/sign";
    }

    @RequestMapping(value = "/getSign", method = RequestMethod.POST)
    @ResponseBody
    public String getSign(HttpServletRequest request) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iterator = requestParams.keySet().iterator(); iterator.hasNext(); ) {
            String name = (String) iterator.next();
            String[] values = (String[]) requestParams.get(name);
            String value = "";
            for (int i = 0; i < values.length; i++)
                value = (i == values.length - 1) ? value + values[i] : value + values[i] + ",";
            params.put(name, value);
        }
        StringBuffer queryString = new StringBuffer();
        List<String> keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); ++i) {
            String key = (String) keys.get(i);
            String value = (String) params.get(key);
            queryString.append((i == 0 ? "" : "&") + key + "=" + value);
        }
        String sign = Md5Util.md5(queryString.toString() + ConfigUtil.getInstance().getString("md5AppKey"));
        Map data = new HashMap<String, String>();
        data.put("sign", sign);
        return ResponseMsg.New(0, "生成签名成功", data).asJson();
    }
    //endregion

    //region 咨询列表
    @RequestMapping(value = "/getnewslist")
    @ResponseBody
    public void getNewsList(int type, int currPage,HttpServletResponse response) throws Exception {

        Map<String,Object> data = new HashMap<String, Object>();
        Map<String,Object> result=new HashMap<String,Object>();
        if (type <= 0 || type > 3) {
            result.put("code", 0);
            result.put("message", "请重试");
        }else {
            //region  banner获取
            List<InfoGGDicContent> bannerlist = null;
            if (currPage == 1) {
                String path = "";
                switch (type) {
                    case 1:
                        path = ConfigUtil.getInstance().getString("infonews1");
                        break;
                    case 2:
                        path = ConfigUtil.getInstance().getString("infonews2");
                        break;
                    case 3:
                        path = ConfigUtil.getInstance().getString("infonews3");
                        break;
                }
                InfoGGDicContent infoGGDicContent = new InfoGGDicContent();
                infoGGDicContent.setState(1);
                infoGGDicContent.setDicPath(path);
                bannerlist = infoGGDicContentService.getList(infoGGDicContent);
            }
            data.put("banner", bannerlist);
            //endregion

            //region 咨询获取
            InfoNews infoNews = new InfoNews();
            infoNews.setState(1);
            infoNews.setType(type);
            PageUtil<InfoNews> pagedata = infoNewsService.getpagelist(infoNews, currPage, 10);
            data.put("newsList", pagedata);
            //endregion

            result.put("code", 1);
            result.put("message", "success");
            result.put("data", data);
        }
        ServletUtils.writeToResponse(response,result);
    }
    //endregion

    //region 咨询阅读量
    @RequestMapping(value = "/newsread", method = RequestMethod.POST)
    @ResponseBody
    public String newsread(long id) throws Exception {
        InfoNews infoNews = new InfoNews();
        infoNews.setId(id);
        infoNews = infoNewsService.getmodel(infoNews);
        if (infoNews == null)
            return ResponseMsg.New(0, "请重试").asJson();
        int readcount = infoNews.getReadCount();
        infoNews = new InfoNews();
        infoNews.setId(id);
        infoNews.setReadCount(readcount + 1);
        if (infoNewsService.update(infoNews))
            return ResponseMsg.New(1, "success").asJson();
        else
            return ResponseMsg.New(0, "请重试").asJson();
    }
    //endregion

    //region 用户反馈信息
    @RequestMapping(value = "/usersuggest", method = RequestMethod.POST)
    @ResponseBody
    public String usersuggest(long uid, String content) throws Exception {
        if (content.isEmpty())
            return ResponseMsg.New(0, "请输入反馈意见后提交").asJson();
        User user = (User) (RequestContextHolder.getRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_REQUEST));
        InfoSuggest infoSuggest = new InfoSuggest();
        infoSuggest.setUserId(user.getId());
        infoSuggest.setRealName(user.getRealName());
        infoSuggest.setMobile(user.getMobile());
        infoSuggest.setContent(content);
        infoSuggest.setIsRead(2);
        infoSuggest.setCreateTime(new Date());

        if (infoSuggestService.add(infoSuggest))
            return ResponseMsg.New(1, "提交成功").asJson();
        else
            return ResponseMsg.New(0, "提交失败").asJson();
    }
    //endregion

    //region 常见问题
    @RequestMapping(value = "/help")
    public String help(Model model) throws Exception{
        String path = ConfigUtil.getInstance().getString("help");
        InfoHelpDicContent infoHelpDicContent=new InfoHelpDicContent();
        infoHelpDicContent.setDicPath(path);
        infoHelpDicContent.setState(1);
        List<InfoHelpDicContent> list=infoHelpDicContentService.getList(infoHelpDicContent);
        model.addAttribute("data",list);
        return "info/help";
    }
    //endregion

    //region 资讯详情
    @RequestMapping(value = "/newsdetail")
    public String newsDetail(Model model,long id) throws Exception{

        InfoNews infoNews=new InfoNews();
        infoNews.setId(id);
        infoNews=infoNewsService.getmodel(infoNews);
        model.addAttribute("data",infoNews);

        return "/info/newsdetail";
    }
    //endregion

    //region 首页
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @ResponseBody
    public void index(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String,Object> data = new HashMap<String, Object>();
        Map<String,Object> result=new HashMap<String,Object>();

        //region 轮播图
        List<InfoGGDicContent> bannerlist = null;
        String key = ConfigUtil.getInstance().getString("indexBanner");
        InfoGGDicContent infoGGDicContent = new InfoGGDicContent();
        infoGGDicContent.setDicPath(key);
        infoGGDicContent.setState(1);
        bannerlist = infoGGDicContentService.getList(infoGGDicContent);
        data.put("banner", bannerlist);
        //endregion

        //region 热点资讯
        List<InfoNews> newslist = null;
        InfoNews infoNews = new InfoNews();
        infoNews.setIsHot(1);
        newslist = infoNewsService.getpagelist(infoNews, 1, 2).getData();
        data.put("newsList", newslist);
        //endregion

        //region 热门推荐贷款
        String typestr=request.getParameter("type");
        int type=1;
        if(typestr!=null)
        {
            type=Integer.parseInt(typestr);
        }
        String path = ConfigUtil.getInstance().getString("onlineswitch");
        infoGGDicContent=new InfoGGDicContent();
        infoGGDicContent.setDicPath(path);
        List<InfoGGDicContent> list=infoGGDicContentService.getList(infoGGDicContent);

        infoGGDicContent=new InfoGGDicContent();
        for (InfoGGDicContent item:list) {
            if(type==1){
                if(item.getLinkUrl().equals("Android"))
                    infoGGDicContent=item;
            }else if(type==2){
                if(item.getLinkUrl().equals("IOS"))
                    infoGGDicContent=item;
            }
        }

        List<Supply> supplylist = null;
        Supply supply = new Supply();
        supply.setRecommend(1);
        if(infoGGDicContent.getState()==1)
        {
            supply.setState(3);
        }else{
            supply.setState(1);
        }
        supplylist = supplyService.getPageList(supply, 1, 3).getData();
        data.put("supplyList", supplylist);
        //endregion

        //region 底部banner
        String footkey = ConfigUtil.getInstance().getString("footBanner");
        infoGGDicContent = new InfoGGDicContent();
        infoGGDicContent.setDicPath(footkey);
        infoGGDicContent.setState(1);
        infoGGDicContent = infoGGDicContentService.getmodel(infoGGDicContent);
        data.put("footBanner", infoGGDicContent);
        //endregion
        result.put("code",1);
        result.put("message","success");
        result.put("data",data);
        ServletUtils.writeToResponse(response,result);
    }

    /**
     * 融e宝：app首页信息 新
     * @param type 查询类型 type 1=android 2=ios
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/appIndex")
    @ResponseBody
    public void appIndex(Integer type,HttpServletResponse response){
        Map<String,Object> data=new HashMap<String, Object>();
        Map<String,Object> result=new HashMap<String, Object>();
        try{
            //region 轮播图
            String key = ConfigUtil.getInstance().getString("indexBanner");
            InfoGGDicContent infoGGDicContent = new InfoGGDicContent();
            infoGGDicContent.setDicPath(key);
            infoGGDicContent.setState(1);
            List<InfoGGDicContent> bannerlist = infoGGDicContentService.getList(infoGGDicContent);
            data.put("banner", bannerlist);

            //region 滚动广告信息
            Map rollingMsg=supplyService.selectRollingMsg();
            data.put("rollingMsg", rollingMsg);

            //region 蜂贷攻略模板
            Supply supply=new Supply();
            Integer getState=infoGGDicContentService.getState(type);//获取是展示资讯还是供应商类型
            if (getState == 1) {//审核开关开启，显示伪数据
                if (type == 2){
                    //判断如果type==2为IOS时读取商品数据
                    supply.setState(4);
                }else
                {//为安卓时读取资讯数据
                    supply.setState(3);
                }
            } else {
                //审核开关关闭，显示正常数据
                supply.setState(1);
            }
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("raidersIndexSort","desc");
            map.put("supply",supply);
            PageUtil<Supply> raidersList=supplyService.selectListAll(map,1,4);
            data.put("raidersList",raidersList.getData());

            //region 热点资讯
            InfoNews infoNews = new InfoNews();
            infoNews.setIsHot(1);
            List<InfoNews> newslist = infoNewsService.getpagelist(infoNews, 1, 2).getData();
            data.put("newsList", newslist);


            //region 热门推荐贷款
            map.remove("raidersIndexSort");//移除蜂贷攻略模板的排序
            map.put("hitsSort","desc");//按照点击量倒序排序
            supply.setRecommend(1);//是推荐
            PageUtil<Supply> hotList=supplyService.selectListAll(map,1,3);
            data.put("hotList",hotList.getData());

            //region 底部banner
            String footKey = ConfigUtil.getInstance().getString("footBanner");
            infoGGDicContent = new InfoGGDicContent();
            infoGGDicContent.setDicPath(footKey);
            infoGGDicContent.setState(1);
            infoGGDicContent = infoGGDicContentService.getmodel(infoGGDicContent);
            data.put("footBanner", infoGGDicContent);
            result.put("code",1);
            result.put("message","success");
            result.put("data",data);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",0);
            result.put("message","error");
        }
        ServletUtils.writeToResponse(response,result);
    }

    //endregion

    //region 应用上线开关接口
    @RequestMapping(value = "/onlineswitch")
    @ResponseBody
    public String getOnLineSwitch(@RequestParam(value = "type",required = true) int type,
                                  @RequestParam(value = "version",required = false) String version,
                                  HttpServletRequest request)throws Exception{

        String path = ConfigUtil.getInstance().getString("onlineswitch");
        InfoGGDicContent infoGGDicContent=new InfoGGDicContent();
        infoGGDicContent.setDicPath(path);
        if(version!=null) {
            if (!version.isEmpty()) {
                infoGGDicContent.setTitle(version);
            }
        }
        List<InfoGGDicContent> list=infoGGDicContentService.getList(infoGGDicContent);
        infoGGDicContent=new InfoGGDicContent();
        for (InfoGGDicContent item:list) {
            if(type==1){
                if(item.getLinkUrl().equals("Android"))
                    infoGGDicContent=item;
            }else if(type==2){
                if(item.getLinkUrl().equals("IOS"))
                    infoGGDicContent=item;
            }
        }
        String json=infoGGDicContent.getIntro();
        JSONObject myJsonObject = JSONObject.parseObject(json);
        Map data=new HashMap<String,Object>();
        data.put("data",infoGGDicContent);
        data.put("json",myJsonObject);

        return  ResponseMsg.New(1,"success",data).asJson();
    }
    //endregion

    //region 途购管家
    @RequestMapping(value = "/tgmanageswitch", method = RequestMethod.POST)
    public void tgManageSwitch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String version = request.getParameter("version");

        String path = ConfigUtil.getInstance().getString("TG_Manage_Switch");

        Map<String, Object> result = new HashMap<String, Object>();

        InfoGGDicContent infoGGDicContent = new InfoGGDicContent();
        infoGGDicContent.setState(1);
        infoGGDicContent.setTitle(version);
        infoGGDicContent.setDicPath(path);

        infoGGDicContent = infoGGDicContentService.getmodel(infoGGDicContent);
        if (infoGGDicContent != null && infoGGDicContent.getId() > 0) {
            result.put("code", 200);
            result.put("msg", infoGGDicContent.getIntro());
        } else {
            result.put("code", 400);
            result.put("msg", "failed");
        }
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 融e宝IOS审核开关接口
    @RequestMapping(value = "/rebswith", method = RequestMethod.POST)
    @ResponseBody
    public String rebSwith(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String version = request.getParameter("version");

        String path = ConfigUtil.getInstance().getString("reb_ios_check_switch");

        Map<String, Object> data = new HashMap<String, Object>();

        InfoGGDicContent infoGGDicContent=new InfoGGDicContent();

        //infoGGDicContent.setState(1);
        infoGGDicContent.setTitle(version);
        infoGGDicContent.setDicPath(path);

        infoGGDicContent = infoGGDicContentService.getmodel(infoGGDicContent);
        if(infoGGDicContent!=null) {
            String json = infoGGDicContent.getIntro();
            JSONObject myJsonObject = JSONObject.parseObject(json);
            data.put("data", infoGGDicContent);
            data.put("json", myJsonObject);
        }

       return ResponseMsg.New(1,"success",data).asJson();
    }
    //endregion
}
