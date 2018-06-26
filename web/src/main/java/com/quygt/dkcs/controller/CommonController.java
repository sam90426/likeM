package com.quygt.dkcs.controller;

import com.quygt.dkcs.utils.ConfigUtil;
import com.quygt.dkcs.utils.ResponseMsg;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "common", produces = "text/plain;charset=UTF-8")
public class CommonController {

    //region 页面
    @RequestMapping(value = "/uploadpage")
    public String uploadpage() {
        return "common/upload";
    }
    //endregion

    //region 上传
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("uploadFile") MultipartFile multiFile, HttpServletRequest request) throws IOException, FileUploadException {
        //获取服务器物理路径
        //String dir = request.getSession().getServletContext().getRealPath("/UploadFile");
        String dir = ConfigUtil.getInstance().getString("PicPath");
        //路径
        Calendar cal = Calendar.getInstance();
        String path = request.getParameter("saveDir");
        path = path + "/" + cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH);
        dir = dir + path;

        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = multiFile.getOriginalFilename();
        //防止文件被覆盖，以纳秒生成文件
        Long _l = System.nanoTime();
        String _extfilename = filename.substring(filename.indexOf("."));
        filename = _l + _extfilename;
        try {
            FileUtils.writeByteArrayToFile(new File(dir, filename), multiFile.getBytes());
            Map data = new HashMap<String, Object>();
            data.put("fileName", filename);
            data.put("fileSize", multiFile.getSize() / 1024 / 1024);
            data.put("fileUrl", "/UploadFile" + path + "/" + filename);
            return ResponseMsg.New(1, "上传成功", data).asJson();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMsg.New(1, "上传失败").asJson();
        }
    }
    //endregion

    //region 删除
    @RequestMapping(value = "/filedel", method = RequestMethod.POST)
    @ResponseBody
    public String filedel(HttpServletRequest request, String path) throws Exception {
        //String dir = request.getSession().getServletContext().getRealPath("/");
        String dir = ConfigUtil.getInstance().getString("PicPath");
        dir = dir.replace("/UploadFile", "");
        int count = path.lastIndexOf("/");
        dir = dir + path.substring(0, count + 1);
        String fileName = path.substring(count + 1);
        if (new File(dir, fileName).delete())
            return ResponseMsg.New(1, "删除成功").asJson();
        else
            return ResponseMsg.New(0, "删除失败").asJson();
    }
    //endregion

    //region 单图上传
    @RequestMapping(value = "/imgupload", method = RequestMethod.POST)
    @ResponseBody
    public String imgupload(@RequestParam("uploadFile") MultipartFile multiFile, HttpServletRequest request) throws IOException, FileUploadException {
        //获取服务器物理路径
        //String dir = request.getSession().getServletContext().getRealPath("/UploadFile");
        String dir = ConfigUtil.getInstance().getString("PicPath");
        //路径
        Calendar cal = Calendar.getInstance();
        String path = request.getParameter("saveDir");
        path = path + "/" + cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH);
        dir = dir + path;

        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = multiFile.getOriginalFilename();
        //防止文件被覆盖，以纳秒生成文件
        Long _l = System.nanoTime();
        String _extfilename = filename.substring(filename.indexOf("."));
        filename = _l + _extfilename;
        try {
            FileUtils.writeByteArrayToFile(new File(dir, filename), multiFile.getBytes());
            return ResponseMsg.New(1, "上传成功", "/UploadFile" + path + "/" + filename).asJson();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMsg.New(1, "上传失败").asJson();
        }
    }
    //endregion

    //region KindEditor图片上传

    //最大文件大小
    private static final long MAX_SIZE = 1000000;

    //region 图片保存
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(@RequestParam("imgFile") MultipartFile multiFile, HttpServletRequest request, HttpServletResponse response) throws FileUploadException {

        //获取服务器物理路径
        String dir = ConfigUtil.getInstance().getString("PicPath");
        //路径
        Calendar cal = Calendar.getInstance();
        String path = request.getParameter("saveDir");
        path = path + "/" + cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH);
        dir = dir + path;
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }

        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");

        response.setContentType("text/html; charset=UTF-8");

        if (!ServletFileUpload.isMultipartContent(request)) {
            return getError("请选择文件。");
        }
        //检查目录
        File uploadDir = new File(dir);
        if (!uploadDir.isDirectory()) {
            return getError("上传目录不存在。");
        }
        //检查目录写权限
        if (!uploadDir.canWrite()) {
            return getError("上传目录没有写权限。");
        }
        String filename = multiFile.getOriginalFilename();
        //防止文件被覆盖，以纳秒生成文件
        Long _l = System.nanoTime();
        String _extfilename = filename.substring(filename.indexOf("."));
        filename = _l + _extfilename;
        if (multiFile.getSize() > MAX_SIZE)
            return getError("上传文件大小超过限制。");
        //检查扩展名
        String fileExt = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)) {
            return getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get("image") + "格式。");
        }
        //写入文件
        try {
            FileUtils.writeByteArrayToFile(new File(dir, filename), multiFile.getBytes());
            JSONObject obj = new JSONObject();
            obj.put("error", 0);
            obj.put("url", "/UploadFile" + path + "/" + filename);
            return obj.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
            return getError("上传文件失败。");
        }
    }
    //endregion

    //region 错误消息公共方法
    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toJSONString();
    }
    //endregion

    //endregion

    //region 浏览器相关

    //region 浏览器信息
    public static String BrowserInfo(HttpServletRequest request){
        //获取浏览器信息
        Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
        //获取浏览器版本号
        Version version = browser.getVersion(request.getHeader("User-Agent"));
        String info = browser.getName() + "/" + version.getVersion();
        return info;
    }
    //endregion

    //region 浏览器IP
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    //endregion

    //endregion
}
