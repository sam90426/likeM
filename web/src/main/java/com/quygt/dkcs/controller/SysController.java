package com.quygt.dkcs.controller;

import com.quygt.dkcs.model.SysDictionary;
import com.quygt.dkcs.model.SysDictionaryContent;
import com.quygt.dkcs.service.SysDictionaryContentService;
import com.quygt.dkcs.service.SysDictionaryService;
import com.quygt.dkcs.utils.PageUtil;
import com.quygt.dkcs.utils.ResponseMsg;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.quygt.dkcs.utils.JsonUtil.Encode;
import static com.quygt.dkcs.utils.JsonUtil.fromJson;

@Controller
@RequestMapping(value = "/sys", produces = "text/plain;charset=UTF-8")
public class SysController extends BaseController {
    @Resource
    private SysDictionaryService sysDictionaryService;
    @Resource
    private SysDictionaryContentService sysDictionaryContentService;

    //region 数据字典

    //region 数据字典列表

    //region 数据字典列表加载
    @RequestMapping(value = "/sysdiclist")
    public String sysdiclist() {
        return "/sys/sysdiclist";
    }
    //endregion

    //region 数据字典列表数据加载
    @RequestMapping(value = "/sysdiclistp", method = RequestMethod.POST)
    @ResponseBody
    public String sysdiclistp(HttpServletRequest request) throws Exception {

        String key = request.getParameter("key");
        SysDictionary sysDictionary = new SysDictionary();
        if (key != null && key != "")
            sysDictionary.setDicName(key);
        List<SysDictionary> list = sysDictionaryService.gettreelist(sysDictionary);
        String json = Encode(list);
        return json;
    }
    //endregion

    //region 删除
    @RequestMapping(value = "sysdicdel", method = RequestMethod.POST)
    @ResponseBody
    public String sysdicdel(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        if (id != null && id != "") {
            if (sysDictionaryService.deleteById(Long.parseLong(id))) {
                return ResponseMsg.New(1, "删除成功").asJson();
            } else {
                return ResponseMsg.New(0, "删除失败").asJson();
            }
        } else {
            return ResponseMsg.New(0, "请选择记录").asJson();
        }
    }
    //endregion

    //endregion

    //region 数据字典编辑

    //region 编辑页面加载
    @RequestMapping(value = "/sysdicedit")
    public String sysdicedit() {
        return "/sys/sysdicedit";
    }
    //endregion

    //region 编辑页面数据加载
    @RequestMapping(value = "/sysdiceditp")
    @ResponseBody
    public String sysdiceditp(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");

        SysDictionary sysDictionary = new SysDictionary();
        if (id != null && id != "")
            sysDictionary.setId(Long.parseLong(id));
        sysDictionary = sysDictionaryService.getmodel(sysDictionary);

        SysDictionary newinfo = new SysDictionary();
        newinfo.setDicPath(sysDictionary.getDicPath().substring(0, 2));
        newinfo = sysDictionaryService.getmodel(newinfo);
        if (newinfo != null)
            sysDictionary.setDicPath(sysDictionary.getDicPath() + "|" + newinfo.getDicName());
        String json = Encode(sysDictionary);
        return json;

    }
    //endregion

    //region 保存
    @RequestMapping(value = "/sysdiceditsave")
    @ResponseBody
    public String sysdiceditsave(HttpServletRequest request) throws Exception {
        String data = request.getParameter("data");
        SysDictionary sysDictionary = fromJson(data, SysDictionary.class);
        boolean result = false;
        if (sysDictionary.getId() > 0) {
            //region 路径判断
            SysDictionary newsysdic=new SysDictionary();
            newsysdic.setId(sysDictionary.getId());
            newsysdic=sysDictionaryService.getmodel(newsysdic);
            String oldpath=newsysdic.getDicPath();
            String _oldpath=newsysdic.getDicPath();
            if(oldpath.length()>1)
                oldpath=oldpath.substring(0,oldpath.length()-3);
            if(!sysDictionary.getDicPath().equals(oldpath)){
                String path = GetSysPath(sysDictionary.getDicPath());//新路径
                sysDictionary.setDicPath(path);
                //region 移动该字典下的所有字典内容
                SysDictionaryContent sysdiccon=new SysDictionaryContent();
                sysdiccon.setDicPath(_oldpath);
                List<SysDictionaryContent> list=sysDictionaryContentService.getList(sysdiccon);
                for (SysDictionaryContent item:list) {
                    item.setDicPath(path);
                    sysDictionaryContentService.update(item);
                }
                //endregion
            }else{
                sysDictionary.setDicPath(_oldpath);
            }
            //endregion
            result = sysDictionaryService.update(sysDictionary);
        } else {
            String path = sysDictionary.getDicPath();
            sysDictionary.setDicPath(GetSysPath(path));
            sysDictionary.setId(0);
            sysDictionary.setCreateTime(new Date());
            result = sysDictionaryService.add(sysDictionary);
        }
        if (result)
            return ResponseMsg.New(1, "保存成功").asJson();
        else
            return ResponseMsg.New(0, "保存失败").asJson();
    }
    //endregion

    //region 计算节点
    private String GetSysPath(String path) {
        SysDictionary sysDictionary = new SysDictionary();
        sysDictionary.setDicPath(path);
        sysDictionary.setOrderIndex(path.length() + 3);
        String newpath = sysDictionaryService.getmax(sysDictionary);
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

    //region 数据字典内容列表

    //region 页面加载
    @RequestMapping(value = "/sysdiccontentlist")
    public String sysdiccontentlist() {
        return "/sys/sysdiccontentlist";
    }
    //endregion

    //region 数据加载
    @RequestMapping(value = "/sysdiccontentlistp", method = RequestMethod.POST)
    @ResponseBody
    public String sysdiccontentlistp(HttpServletRequest request) throws Exception {
        String path = request.getParameter("path");
        //分页
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex")) + 1;
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //字段排序
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");

        SysDictionaryContent sysDictionaryContent = new SysDictionaryContent();
        if (path != null && path != "") {
            sysDictionaryContent.setDicPath(path);
        }
        PageUtil<SysDictionaryContent> pagelist = sysDictionaryContentService.getpagelist(sysDictionaryContent, pageIndex, pageSize);
        HashMap result = new HashMap();
        result.put("data", pagelist.getData());
        result.put("total", pagelist.getTotalItems());
        String json = Encode(result);

        return json;
    }
    //endregion

    //region 删除
    @RequestMapping(value = "/sysdiccontentdel", method = RequestMethod.POST)
    @ResponseBody
    public String sysdiccontentdel(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String[] ids = id.split(",");
        if (ids.length > 0) {
            for (String delid : ids) {
                sysDictionaryContentService.deleteById(Long.parseLong(delid));
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
    @RequestMapping(value = "sysdiccontentedit")
    public String sysdiccontentedit() {
        return "/sys/sysdiccontentedit";
    }
    //endregion

    //region 页面数据获取
    @RequestMapping(value = "sysdiccontenteditp", method = RequestMethod.POST)
    @ResponseBody
    public String sysdiccontenteditp(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        SysDictionaryContent sysDictionaryContent = new SysDictionaryContent();
        sysDictionaryContent.setId(Long.parseLong(id));
        sysDictionaryContent = sysDictionaryContentService.getmodel(sysDictionaryContent);
        String json = Encode(sysDictionaryContent);
        return json;
    }
    //endregion

    //region 保存
    @RequestMapping(value = "/sysdiccontenteditsave", method = RequestMethod.POST)
    @ResponseBody
    public String sysdiccontenteditsave(HttpServletRequest request) throws Exception {
        String data = request.getParameter("data");
        SysDictionaryContent sysDictionaryContent = fromJson(data, SysDictionaryContent.class);
        boolean result = false;
        if (sysDictionaryContent.getId() > 0) {
            result = sysDictionaryContentService.update(sysDictionaryContent);
        } else {
            sysDictionaryContent.setId(0);
            sysDictionaryContent.setCreateTime(new Date());
            result = sysDictionaryContentService.add(sysDictionaryContent);
        }
        if (result)
            return ResponseMsg.New(1, "保存成功").asJson();
        else
            return ResponseMsg.New(0, "保存失败").asJson();
    }
    //endregion

    //endregion

    //endregion

    //region 短信测试

    //region 发送POST方法的请求

    /**
     * 发送POST请求
     *
     * @param url        目的地址
     * @param parameters 请求参数，Map类型。
     * @return 远程响应结果
     */
    public static String sendPost(String url, Map<String, String> parameters) {
        String result = "";// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        PrintWriter out = null;
        StringBuffer sb = new StringBuffer();// 处理请求参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(url);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn
                    .getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    //endregion

    //region 页面
    @RequestMapping(value = "/sendmsg")
    public String sendmsg() {
        return "/sys/sendmsg";
    }
    //endregion

    //region 发送短信
    @RequestMapping(value = "/sendmsgp")
    @ResponseBody
    public String sendmsgp(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String content = request.getParameter("content");
        String url = "http://119.23.249.213:8888/sms.aspx";

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("action", "send");
        param.put("userid", "385");
        param.put("account", "zhouzonghy");
        param.put("password", "123456");
        param.put("mobile", mobile);
        param.put("content", content);
        param.put("sendTime", "");
        param.put("extno", "");

        String result = sendPost(url, param);
        Map aa = readStringXmlOut(result);

        System.out.println("m=" + mobile + ";c=" + content + "@@@@@@@@@@@*************@@@@@@@@@@@@" + result);
        if (aa.get("returnstatus").toString().equals("Success")) {
            if (aa.get("message").toString().equals("ok"))
                return ResponseMsg.New(1, "发送成功").asJson();
            else
                return ResponseMsg.New(0, aa.get("message").toString()).asJson();
        } else {
            return ResponseMsg.New(0, "发送失败").asJson();
        }
    }
    //endregion

    //region xml解析

    /**
     * @param xml
     * @return Map
     * @description 将xml字符串转换成map
     */
    public static Map readStringXmlOut(String xml) {
        Map map = new HashMap();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            map.put("returnstatus", rootElt.elementText("returnstatus"));
            map.put("message", rootElt.elementText("message"));
            map.put("remainpoint", rootElt.elementText("remainpoint"));
            map.put("taskID", rootElt.elementText("taskID"));
            map.put("successCounts", rootElt.elementText("successCounts"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    //endregion

    //endregion
}
