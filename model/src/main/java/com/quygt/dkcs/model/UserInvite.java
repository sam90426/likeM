package com.quygt.dkcs.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户邀请记录表
 * @Project:dkcs
 * @PackageName:com.quygt.dkcs.model
 * @Author:fujian
 * @CreationDate:2018年01月19日19:35
 */
public class UserInvite extends BaseModel{

    /**
     * 主键
     */
    private Long id;

    /**
     * 邀请时间
     */
    private Date inviteTime;

    /**
     * 被邀请人id
     */
    private Long inviteId;

    /**
     * 被邀请人名称
     */
    private String inviteName;

    /**
     * 邀请人id
     */
    private Long userId;

    /**
     * 邀请人名称
     */
    private String userName;

    /**
     * 奖励金
     */
    private BigDecimal bounty;

    /**
     * 提现状态 0,未申请；1提现中；2提现成功；3提现失败
     */
    private Integer withdrawalsState;

    /**
     * 被邀请人是否已下载app 数据库不需要有该字段
     */
    private String isApp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInviteTime() {
        return inviteTime;
    }

    public void setInviteTime(Date inviteTime) {
        this.inviteTime = inviteTime;
    }

    public Long getInviteId() {
        return inviteId;
    }

    public void setInviteId(Long inviteId) {
        this.inviteId = inviteId;
    }

    public String getInviteName() {
        return inviteName;
    }

    public void setInviteName(String inviteName) {
        this.inviteName = inviteName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getBounty() {
        return bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public Integer getWithdrawalsState() {
        return withdrawalsState;
    }

    public void setWithdrawalsState(Integer withdrawalsState) {
        this.withdrawalsState = withdrawalsState;
    }

    public String getIsApp() {
        return isApp;
    }

    public void setIsApp(String isApp) {
        this.isApp = isApp;
    }
}
