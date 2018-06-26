package com.quygt.dkcs.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户提现记录表
 *
 * @Project:dkcs
 * @PackageName:com.quygt.dkcs.model
 * @Author:fujian
 * @CreationDate:2018年01月19日19:35
 */
public class Withdrawals extends BaseModel {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户主键id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 提现状态：1、提现中；2 打款成功；3 打款失败；
     */
    private Integer state;

    /**
     * 申请提现金额
     */
    private BigDecimal amount;

    /**
     * 创建时间/申请时间
     */
    private Date createTime;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 失败原因
     */
    private String failReason;

    /**
     * 操作人id
     */
    private Long sysAdminId;

    /**
     * 操作人名称
     */
    private String sysAdminName;

    /**
     * 支付宝账号
     */
    private String alipayAccount;

    /**
     * 支付宝姓名
     */
    private String alipayName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public Long getSysAdminId() {
        return sysAdminId;
    }

    public void setSysAdminId(Long sysAdminId) {
        this.sysAdminId = sysAdminId;
    }

    public String getSysAdminName() {
        return sysAdminName;
    }

    public void setSysAdminName(String sysAdminName) {
        this.sysAdminName = sysAdminName;
    }
}
