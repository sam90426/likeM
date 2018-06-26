package com.quygt.dkcs.model;

/**
 * 用户其他信息表
 *
 * @Project:dkcs
 * @PackageName:com.quygt.dkcs.model
 * @Author:fujian
 * @CreationDate:2018年01月19日19:35
 */
public class UserOtherInfo extends BaseModel {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 邀请码
     */
    private String invitationCode;

    /**
     * 是否下载过app 0否；1是
     */
    private Integer isApp;

    /**
     * 支付宝账户
     */
    private String alipayAccount;

    /**
     * 支付宝名称
     */
    private String alipayName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Integer getIsApp() {
        return isApp;
    }

    public void setIsApp(Integer isApp) {
        this.isApp = isApp;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public String getAlipayName() {
        return alipayName;
    }

    public void setAlipayName(String alipayName) {
        this.alipayName = alipayName;
    }
}
