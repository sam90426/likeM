package com.quygt.dkcs.model;

import java.util.Date;

/**
 * 渠道商
 * @Project:dkcs
 * @PackageName:com.quygt.dkcs.model
 * @Author:fujian
 * @CreationDate:2018年01月15日11:14
 */
public class Channel extends BaseModel{
    /**
     * 主键id
     */
    private Long id;

    /**
     * 渠道代码
     */
    private String code;

    /**
     * 渠道商名称
     */
    private String name;

    /**
     * 渠道商登录密码
     */
    private String channelPassword;

    /**
     * 联系人
     */
    private String linker;

    /**
     * 联系人手机号
     */
    private String phone;

    /**
     * 渠道类型  (备用)
     */
    private String type;

    /**
     * 状态 10：启用20：禁用
      */
    private Integer state;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 绑定的用户数量
     */
    private Integer userCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannelPassword() {
        return channelPassword;
    }

    public void setChannelPassword(String channelPassword) {
        this.channelPassword = channelPassword;
    }

    public String getLinker() {
        return linker;
    }

    public void setLinker(String linker) {
        this.linker = linker;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }
}
