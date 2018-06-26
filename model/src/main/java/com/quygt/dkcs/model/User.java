package com.quygt.dkcs.model;

import java.util.Date;

public class User extends BaseModel{
    private long id ;
    private String userName;
    private String passWord;
    private String realName ;
    private String picUrl;
    private int sex;
    private String mobile;
    private int loginCount;
    private int state;
    private Date lastLoginTime;
    private Date createTime;

    /**
     * 渠道商名称
     */
    private String channelName;

    /**
     * 渠道商id
     */
    private Long channelId;

    /**
     * 性别字符
     */
    private String sexStr;

    /**
     * 状态字符
     */
    private String stateStr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSexStr() {
        return this.sex==1?"男":"女";
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

    public String getStateStr() {
        return this.state==1?"正常":"停用";
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public User(long id) {
        this.id = id;
    }

    public User() {
    }
}
