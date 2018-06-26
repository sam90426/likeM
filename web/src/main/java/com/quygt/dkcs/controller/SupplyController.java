package com.quygt.dkcs.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quygt.dkcs.model.Supply;
import com.quygt.dkcs.model.SupplyLog;
import com.quygt.dkcs.model.SysDictionary;
import com.quygt.dkcs.model.SysDictionaryContent;
import com.quygt.dkcs.service.SupplyLogService;
import com.quygt.dkcs.service.SupplyService;
import com.quygt.dkcs.service.SysDictionaryContentService;
import com.quygt.dkcs.service.SysDictionaryService;
import com.quygt.dkcs.utils.*;
import com.quygt.dkcs.utils.excel.JsGridReportBase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.quygt.dkcs.utils.JsonUtil.Encode;

@Controller
@RequestMapping(value = "/supply", produces = "text/plain;charset=UTF-8")
public class SupplyController {

    @Resource
    private SupplyService supplyService;
    @Resource
    private SupplyLogService supplyLogService;

    @Resource
    private SysDictionaryContentService sysDictionaryContentService;

    @RequestMapping(value = "/supplylist", method = RequestMethod.GET)
    public String supplyList() {
        return "supply/supplyList";
    }

    @RequestMapping(value = "/getSupplyList", method = RequestMethod.POST)
    @ResponseBody
    public String getSupplyList(HttpServletRequest request) throws Exception {
        int supplyType = ParseUtil.toInt(request.getParameter("supplyType"), 0);
        String supplyName = request.getParameter("supplyName");
        int pageIndex = ParseUtil.toInt(request.getParameter("pageIndex"), 0) + 1;
        int pageSize = ParseUtil.toInt(request.getParameter("pageSize"), 20);
        Supply supply = new Supply();
        if (supplyType > 0)
            supply.setSupplyType(supplyType);
        if (supplyName != null && supplyName != "")
            supply.setSupplyName(supplyName);
        PageUtil<Supply> supplyList = supplyService.getPageList(supply, pageIndex, pageSize);
        Map data = new HashMap();
        data.put("data", supplyList.getData());
        data.put("total", supplyList.getTotalItems());
        return Encode(data);
    }

    @RequestMapping(value = "/supplyEdit", method = RequestMethod.GET)
    public String supplyEdit() {
        return "supply/supplyEdit";
    }

    @RequestMapping(value = "/supplyEditLoad", method = RequestMethod.POST)
    @ResponseBody
    public String supplyEditLoad(HttpServletRequest request) throws Exception {
        long id = ParseUtil.toLong(request.getParameter("id"), 0);
        Supply supply = new Supply();
        supply.setId(id);
        supply = supplyService.getmodel(supply);
        return Encode(supply);
    }

    @RequestMapping(value = "/supplyEditSave", method = RequestMethod.POST)
    @ResponseBody
    public String supplyEditSave(HttpServletRequest request) throws Exception {
        String data = request.getParameter("data");
        Supply supply = JsonUtil.fromJson(data, Supply.class);
        boolean result = false;
        if (supply.getScore() == null || supply.getScore() == 0) {
            supply.setState(5);//评分默认为5
        }
        if (supply.getId() > 0) {
            result = supplyService.update(supply);
        } else {
            supply.setCreateTime(new Date());
            result = supplyService.insert(supply);
        }
        if (result)
            return ResponseMsg.New(1, "保存成功").asJson();
        else
            return ResponseMsg.New(0, "保存失败").asJson();
    }

    @RequestMapping(value = "/supplyDel", method = RequestMethod.POST)
    @ResponseBody
    public String supplyDel(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String[] ids = id.split(",");
        if (ids.length > 0) {
            for (String delid : ids) {
                supplyService.deleteById(ParseUtil.toLong(delid, 0));
            }
            return ResponseMsg.New(1, "删除成功").asJson();
        } else {
            return ResponseMsg.New(0, "请先选择删除记录").asJson();
        }
    }

    //region 供应商日志

    //region 页面加载
    @RequestMapping(value = "/supplylog")
    public ModelAndView supplylog() {
        ModelAndView modelAndView = new ModelAndView("/supply/supplylog");
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        json.put("id", 0);
        json.put("text", "全部");
        jsonArray.add(json);
        //邀请配置字典编码
        String key = ConfigUtil.getInstance().getString("specific_source");
        SysDictionaryContent sysDictionaryContent=new SysDictionaryContent();
        sysDictionaryContent.setDicPath(key);
        List<SysDictionaryContent> list=sysDictionaryContentService.getList(sysDictionaryContent);
        if(list!=null&&list.size()>0){//存在该配置
            for (int i = 0; i <list.size(); i++) {
                if(list.get(i).getZs1()!=null&&list.get(i).getZs1()!=0){
                    JSONObject object = new JSONObject();
                    object.put("id", list.get(i).getZs1());
                    object.put("text", list.get(i).getString1());
                    jsonArray.add(object);
                }
            }
        }

        modelAndView.addObject("specificSource", jsonArray.toString());
        return modelAndView;
    }
    //endregion

    //region 页面数据
    @RequestMapping(value = "/supplylogp", method = RequestMethod.POST)
    @ResponseBody
    public String supplylogp(HttpServletRequest request,Integer specificSource) throws Exception {
        String searchtype = request.getParameter("searchtype");
        String key = request.getParameter("key");
        String starttime = request.getParameter("startTime");
        String endtime = request.getParameter("endTime");
        //分页
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex")) + 1;
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        SupplyLog supplyLog = new SupplyLog();
        if (key != null && key != "") {
            if (searchtype.equals("1")) {
                supplyLog.setUserName(key);
            } else if (searchtype.equals("2")) {
                supplyLog.setRealName(key);
            } else if (searchtype.equals("3"))
                supplyLog.setSupplyName(key);
        }
        if (starttime != null) {
            supplyLog.setStartTime(starttime);
        }
        if (endtime != null) {
            supplyLog.setEndTime(endtime);
        }
        supplyLog.setSpecificSource(specificSource);
        PageUtil<SupplyLog> pagelist = supplyLogService.getpagelist(supplyLog, pageIndex, pageSize);
        HashMap result = new HashMap();
        result.put("data", pagelist.getData());
        result.put("total", pagelist.getTotalItems());
        String json = Encode(result);
        return json;
    }
    //endregion

    //region 列表导出

    /**
     * @param user       注入 startTime and endTime
     * @param searchtype 搜索类型 1登录名；2真实姓名
     * @param key        搜索关键字
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/getSupplyLogExport")
    @ResponseBody
    public void getUserListExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String searchtype = request.getParameter("searchtype");
        String key = request.getParameter("key");
        String starttime = request.getParameter("startTime");
        String endtime = request.getParameter("endTime");

        SupplyLog supplyLog = new SupplyLog();
        if (key != null && key != "") {
            if (searchtype.equals("1")) {
                supplyLog.setUserName(key);
            } else if (searchtype.equals("2")) {
                supplyLog.setRealName(key);
            } else if (searchtype.equals("3"))
                supplyLog.setSupplyName(key);
        }
        if (starttime != null) {
            supplyLog.setStartTime(starttime);
        }
        if (endtime != null) {
            supplyLog.setEndTime(endtime);
        }
        String AdminUserName = SessionHelp.GetSessionByEncrypt(request, "AdminUserName");
        List users = supplyLogService.getAllList(supplyLog);
        response.setContentType("application/msexcel;charset=UTF-8");
        String title = "供应商日志";
        String[] hearders = {"编号", "用户编号", "登录账号", "联系人", "供应商ID", "供应商名称", "来源", "注册时间"};
        String[] fields = {"id", "userId", "userName", "realName", "supplyId", "supplyName", "sourceStr", "createTime"};
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportExcel(users, title, hearders, fields, AdminUserName);
    }
    //endregion

    //endregion
}
