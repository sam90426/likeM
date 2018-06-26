package com.quygt.dkcs.model;

import java.util.Date;

public class SupplyLog extends BaseModel {
    private long id ;
    private long userId;
    private String userName;
    private String realName ;
    private long supplyId;
    private String supplyName;
    private int source;
    private Date createTime;
    private String sourceStr;

    /**
     * 具体访问来源 1蜂优贷首页弹框,2蜂优贷首页banner,3信用人生首页弹框，4信用人生首页banner
     */
    private Integer specificSource;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public long getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(long supplyId) {
        this.supplyId = supplyId;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSourceStr() {
        String str="";
        switch (this.source){
            case 1:
                str="APP";
                break;
            case 2:
                str="H5";
                break;
            case 3:
                str="PC";
                break;
        }
        return str;
    }

    public void setSourceStr(String sourceStr) {
        this.sourceStr = sourceStr;
    }

    public Integer getSpecificSource() {
        return specificSource;
    }

    public void setSpecificSource(Integer specificSource) {
        this.specificSource = specificSource;
    }


}
