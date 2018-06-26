package com.quygt.dkcsapi.controller;

import com.quygt.dkcs.model.InfoGGDicContent;
import com.quygt.dkcs.model.Supply;
import com.quygt.dkcs.model.SupplyLog;
import com.quygt.dkcs.model.User;
import com.quygt.dkcs.service.InfoGGDicContentService;
import com.quygt.dkcs.service.SupplyLogService;
import com.quygt.dkcs.service.SupplyService;
import com.quygt.dkcs.service.UserService;
import com.quygt.dkcs.utils.ConfigUtil;
import com.quygt.dkcs.utils.PageUtil;
import com.quygt.dkcs.utils.ResponseMsg;
import com.quygt.dkcsapi.common.ServletUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/supply", produces = "text/plain;charset=UTF-8")
public class SupplyController {

    @Resource
    private SupplyService supplyService;
    @Resource
    private SupplyLogService supplyLogService;
    @Resource
    private InfoGGDicContentService infoGGDicContentService;
    @Resource
    private UserService userService;

    //region 供应商列表
    @RequestMapping(value = "/getSupplyList", method = RequestMethod.POST)
    @ResponseBody
    public String getSupplyList(int supplyType, int recommend, int currPage, HttpServletRequest request) {
        return supplyService.getSupplyList(supplyType, recommend, 0, currPage, 10, request);
    }

    //endregion
    //region 供应商访问日志
    @RequestMapping(value = "/addSupplyLog", method = RequestMethod.POST)
    @ResponseBody
    public String addSupplyLog(String uid, long supplyId) {
        return supplyService.insertUpdate(supplyId, 1,uid,null);//app端访问类型
    }


    //endregion

    //region 蜂优贷API接口

    //region 供应商列表
    /*
     *type 1=android 2=ios
     */
    @RequestMapping(value = "/fydgetSupplyList", method = RequestMethod.POST)
    @ResponseBody
    public void fydgetSupplyList(int supplyType, int currPage, HttpServletRequest request, HttpServletResponse response) {
        String typestr = request.getParameter("type");
        int type = 1;
        if (typestr != null) {
            type = Integer.parseInt(typestr);
        }
        String path = ConfigUtil.getInstance().getString("fydonlineswitch");
        InfoGGDicContent infoGGDicContent = new InfoGGDicContent();
        infoGGDicContent.setDicPath(path);
        List<InfoGGDicContent> list = infoGGDicContentService.getList(infoGGDicContent);

        infoGGDicContent = new InfoGGDicContent();
        for (InfoGGDicContent item : list) {
            if (type == 1) {
                if (item.getLinkUrl().equals("Android"))
                    infoGGDicContent = item;
            } else if (type == 2) {
                if (item.getLinkUrl().equals("IOS"))
                    infoGGDicContent = item;
            }
        }

        int pageSize = 10;
        Supply supply = new Supply();
        if (supplyType > 0) {
            supply.setSupplyType(supplyType);
        }
        if (infoGGDicContent.getState() == 1) {
            supply.setState(3);
        } else {
            supply.setState(1);
        }
        PageUtil<Supply> page = supplyService.getPageList(supply, currPage, pageSize);
        Map data = new HashMap<String, Object>();
        data.put("list", page.getData());
        Map<String, Object> resultpage = new HashMap<String, Object>();
        resultpage.put("current", page.getCurrPage());
        resultpage.put("pageSize", page.getPageSize());
        resultpage.put("pages", page.getTotalPages());
        resultpage.put("total", page.getTotalItems());
        Map<String, Object> result = new HashMap<String, Object>();

        result.put("code", 200);
        result.put("msg", "操作成功");
        result.put("data", data);
        result.put("page", resultpage);

        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 供应商访问日志
    @RequestMapping(value = "/fydaddSupplyLog", method = RequestMethod.POST)
    @ResponseBody
    public void fydaddSupplyLog(String mobile, long supplyId, HttpServletResponse response) throws Exception {

        Map<String, Object> data = new HashMap<String, Object>();

        if (mobile.isEmpty() || supplyId < 1) {
            data.put("code", 400);
            data.put("msg", "参数错误");
        } else {

            Supply supply = supplyService.getSupplyById(supplyId);
            if (supply != null && supply.getState() == 1) {
                User user = new User();
                user.setMobile(mobile);
                user = userService.getmodel(user);
                if (user == null) {
                    //该手机号没有注册则先进行注册
                    user = new User();
                    user.setMobile(mobile);
                    user.setUserName(mobile);
                    user.setRealName(mobile);
                    user.setPicUrl("");
                    user.setState(1);
                    user.setSex(1);
                    user.setPassWord("GtGYlxCzam8=");
                    user.setCreateTime(new Date());
                    user.setLoginCount(0);

                    userService.insert(user);
                }
                SupplyLog supplyLog = new SupplyLog();
                supplyLog.setUserId(user.getId());
                supplyLog.setSupplyId(supplyId);
                supplyLog = supplyLogService.getmodel(supplyLog);
                //判断是否存在日志记录
                if (supplyLog == null) {
                    supplyLog = new SupplyLog();
                    supplyLog.setUserId(user.getId());
                    supplyLog.setUserName(user.getUserName());
                    supplyLog.setRealName(user.getRealName());
                    supplyLog.setSupplyId(supply.getId());
                    supplyLog.setSupplyName(supply.getSupplyName());
                    supplyLog.setSource(1);
                    supplyLog.setCreateTime(new Date());
                    boolean result = supplyLogService.insert(supplyLog);

                    if (!result) {
                        data.put("code", 400);
                        data.put("msg", "添加供应商访问记录失败");
                    } else {
                        data.put("code", 200);
                        data.put("msg", "添加供应商访问记录成功");
                    }
                    //修改供应商点击量
                    supply.setHits(supply.getHits() + 1);
                    supplyService.update(supply);
                } else {
                    data.put("code", 200);
                }
            } else {
                data.put("code", 200);
                data.put("msg", "添加供应商访问记录成功");
            }
        }
        ServletUtils.writeToResponse(response, data);
    }

    /**
     * 融e宝：app端查询供应商列表 新
     * 说明：当审核开关开启时，IOS显示商品，安卓显示资讯，当关闭时显示正常数据
     *
     * @param supply
     * @param currPage 页码
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/selectListAll", method = RequestMethod.POST)
    @ResponseBody
    public String selectListAll(Supply supply, int currPage, Integer pageSize, String createTimeSort, String hitsSort, String orderIndexSort,
                                String raidersIndexSort, Integer minLendingSpeed, Integer maxLendingSpeed, BigDecimal minPassProbability, BigDecimal maxPassProbability, Integer type) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer getState = infoGGDicContentService.getState(type);
        if (getState == 1) {//审核开关开启
            if (type == 2) {//判断type==2为IOS时，显示商品数据
                supply.setState(4);
            } else {//当为安卓时，显示资讯数据
                supply.setState(3);
            }
        } else {
            //审核开关关闭显示正常供应商数据
            supply.setState(1);
        }
        map.put("supply", supply);
        map.put("createTimeSort", createTimeSort);
        map.put("hitsSort", hitsSort);
        map.put("orderIndexSort", orderIndexSort);
        map.put("raidersIndexSort", raidersIndexSort);
        map.put("minLendingSpeed", minLendingSpeed);
        map.put("maxLendingSpeed", maxLendingSpeed);
        map.put("minPassProbability", minPassProbability);
        map.put("maxPassProbability", maxPassProbability);
        PageUtil<Supply> supplyPageUtil = supplyService.selectListAll(map, currPage, pageSize);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", supplyPageUtil);
        return ResponseMsg.New(1, "查询成功", data).asJson();
    }

    /**
     * 首页滚动消息 假数据
     *
     * @return
     */
    @RequestMapping(value = "/selectRollingMsg", method = RequestMethod.POST)
    @ResponseBody
    public String selectRollingMsg() {
        Map map = supplyService.selectRollingMsg();
        return ResponseMsg.New(1, "查询成功", map).asJson();
    }

    /**
     * 根据id查询供应商全部信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.POST)
    @ResponseBody
    public String selectById(Long id) {
        if (id == null || id.equals("")) {
            return ResponseMsg.New(0, "请选择需要查询的供应商", null).asJson();
        }
        Supply supply = supplyService.selectById(id);
        return ResponseMsg.New(1, "查询成功", supply).asJson();
    }

    /**
     * 首页弹框
     *
     * @return
     */
    @RequestMapping(value = "/selectMainAlert", method = RequestMethod.POST)
    @ResponseBody
    public String selectMainAlert() {
        String key = ConfigUtil.getInstance().getString("appalert");
        Map<String, Object> map = null;
        if (key != null && !key.equals("")) {
            InfoGGDicContent infoGGDicContent = new InfoGGDicContent();
            infoGGDicContent.setDicPath(key);
            InfoGGDicContent content = infoGGDicContentService.getmodel(infoGGDicContent);
            if (content != null) {
                map = new HashMap<String, Object>();
                map.put("linkUrl", content.getLinkUrl());//弹框链接到的位置
                map.put("BackgroundMap", content.getPicUrl());//弹框背景图url
                map.put("name", content.getTitle());//弹框名称
                map.put("switchStatus", content.getState());//弹框状态，1=启用/开启；2=停用/关闭；
                return ResponseMsg.New(1, "查询成功", map).asJson();
            }
        }
        return ResponseMsg.New(0, "无弹框", null).asJson();
    }
}
