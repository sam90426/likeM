package com.quygt.dkcs.model;

import java.util.Date;

/**
 * App错误日志
 */
public class AppErrorLog extends BaseModel {
    /**
     *id
     */
    private  long id;
    /**
     *用户ID
     */
    private  long userId;
    /**
     * 用户姓名
     */
    private  String userRealName;
    /**
     * 设备类型：1=IOS，2=安卓
     */
    private  int appType;
    /**
     * 分类：1=网络错误，2=系统错误，3=其他错误
     */
    private  int category;
    /**
     * IP
     */
    private  String ip;
    /**
     * 接口地址
     */
    private  String errorUrl;
    /**
     * 设备信息描述格式：（手机型号：iPhone 6s；app版本号：3.21；操作系统版本：ios 10.2；网络：4G；运营商：移动；剩余内存大小：200MB；剩余磁盘空间大小：200MB）
     */
    private  String appIntro;
    /**
     * 错误内容格式：（功能：蜂文发布；错误：error 500）
     */
    private  String errorIntro;
    /**
     * 是否处理：1=未处理，2=已处理
     */
    private  int isRead;
    /**
     * 创建时间
     */
    private Date createTime;

    public AppErrorLog() { }
    public AppErrorLog(long id, long userId, String userRealName, int appType, int category, String ip, String errorUrl, String appIntro, String errorIntro, int isRead, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.userRealName = userRealName;
        this.appType = appType;
        this.category = category;
        this.ip = ip;
        this.errorUrl = errorUrl;
        this.appIntro = appIntro;
        this.errorIntro = errorIntro;
        this.isRead = isRead;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    public String getAppIntro() {
        return appIntro;
    }

    public void setAppIntro(String appIntro) {
        this.appIntro = appIntro;
    }

    public String getErrorIntro() {
        return errorIntro;
    }

    public void setErrorIntro(String errorIntro) {
        this.errorIntro = errorIntro;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
