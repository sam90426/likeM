package com.quygt.dkcs.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Supply {
    private long id;
    private String supplyName;
    private int supplyType;
    private String logo;
    private String intro;
    private String linkUrl;
    private BigDecimal minMoney;
    private BigDecimal maxMoney;
    private BigDecimal rate;
    private int rateUnit;
    private int state;
    private int orderIndex;
    private int hits;
    private Date createTime;
    private int recommend;

    /**
     * 评分 1-5 默认 5
     */
    private Integer score;

    /**
     * 放款速度 单位分钟
     */
    private Integer lendingSpeed;

    /**
     * 申请条件and要点
     */
    private String applyCondition;

    /**
     * 借款审核细节
     */
    private String examineDetails;

    /**
     * 新手指导
     */
    private String noviceGuidance;

    /**
     * 产品优势
     */
    private String productAdvantage;

    /**
     * 评价人数
     */
    private Integer evaluateNum;

    /**
     * 通过概率 进度条 百分比
     */
    private BigDecimal passProbability;

    /**
     * 欢迎程度 进度条 百分比
     */
    private BigDecimal welcomeDegree;

    /**
     * 放款速度 进度条 百分比
     */
    private BigDecimal lendingSpeedBar;

    /**
     * 借款期限起始天数
     */
    private Integer minLoanTerm;

    /**
     * 借款期限最大天数
     */
    private Integer maxLoanTerm;

    /**
     * 攻略文本
     */
    private String raiders;

    /**
     * 攻略排序
     */
    private Integer raidersIndex;

    /**
     * 是否需要银行卡认证 1=需要，2=不需要
     */
    private Integer isBank;

    /**
     * 是否需要运营商认证 1=需要，2=不需要
     */
    private Integer isOperator;

    /**
     * 是否需要填写基本信息 1=需要，2=不需要
     */
    private Integer isEssential;

    /**
     * 是否需要身份认证 1=需要，2=不需要
     */
    private Integer isIdentity;

    /**
     * 是否需要人脸识别 1=需要，2=不需要
     */
    private Integer isFace;

    /**
     * 是否需要芝麻信用 1=需要，2=不需要
     */
    private Integer isSesame;

    /**
     * 是否可以一次性还清 1=可以，2=不可以
     */
    private Integer isOncePay;

    /**
     * 是否查征信 1=查 2=不查
     */
    private Integer isCredit;

    /**
     * 热门关键字 20个字符以内
     */
    private String hotKeyWord;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public int getSupplyType() {
        return supplyType;
    }

    public void setSupplyType(int supplyType) {
        this.supplyType = supplyType;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public BigDecimal getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }

    public BigDecimal getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public int getRateUnit() {
        return rateUnit;
    }

    public void setRateUnit(int rateUnit) {
        this.rateUnit = rateUnit;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getLendingSpeed() {
        return lendingSpeed;
    }

    public void setLendingSpeed(Integer lendingSpeed) {
        this.lendingSpeed = lendingSpeed;
    }

    public String getApplyCondition() {
        return applyCondition;
    }

    public void setApplyCondition(String applyCondition) {
        this.applyCondition = applyCondition;
    }

    public String getExamineDetails() {
        return examineDetails;
    }

    public void setExamineDetails(String examineDetails) {
        this.examineDetails = examineDetails;
    }

    public String getNoviceGuidance() {
        return noviceGuidance;
    }

    public void setNoviceGuidance(String noviceGuidance) {
        this.noviceGuidance = noviceGuidance;
    }

    public String getProductAdvantage() {
        return productAdvantage;
    }

    public void setProductAdvantage(String productAdvantage) {
        this.productAdvantage = productAdvantage;
    }

    public Integer getEvaluateNum() {
        return evaluateNum;
    }

    public void setEvaluateNum(Integer evaluateNum) {
        this.evaluateNum = evaluateNum;
    }

    public BigDecimal getPassProbability() {
        return passProbability;
    }

    public void setPassProbability(BigDecimal passProbability) {
        this.passProbability = passProbability;
    }

    public BigDecimal getWelcomeDegree() {
        return welcomeDegree;
    }

    public void setWelcomeDegree(BigDecimal welcomeDegree) {
        this.welcomeDegree = welcomeDegree;
    }

    public BigDecimal getLendingSpeedBar() {
        return lendingSpeedBar;
    }

    public void setLendingSpeedBar(BigDecimal lendingSpeedBar) {
        this.lendingSpeedBar = lendingSpeedBar;
    }

    public Integer getMinLoanTerm() {
        return minLoanTerm;
    }

    public void setMinLoanTerm(Integer minLoanTerm) {
        this.minLoanTerm = minLoanTerm;
    }

    public Integer getMaxLoanTerm() {
        return maxLoanTerm;
    }

    public void setMaxLoanTerm(Integer maxLoanTerm) {
        this.maxLoanTerm = maxLoanTerm;
    }

    public String getRaiders() {
        return raiders;
    }

    public void setRaiders(String raiders) {
        this.raiders = raiders;
    }

    public Integer getRaidersIndex() {
        return raidersIndex;
    }

    public void setRaidersIndex(Integer raidersIndex) {
        this.raidersIndex = raidersIndex;
    }

    public Integer getIsBank() {
        return isBank;
    }

    public void setIsBank(Integer isBank) {
        this.isBank = isBank;
    }

    public Integer getIsOperator() {
        return isOperator;
    }

    public void setIsOperator(Integer isOperator) {
        this.isOperator = isOperator;
    }

    public Integer getIsEssential() {
        return isEssential;
    }

    public void setIsEssential(Integer isEssential) {
        this.isEssential = isEssential;
    }

    public Integer getIsIdentity() {
        return isIdentity;
    }

    public void setIsIdentity(Integer isIdentity) {
        this.isIdentity = isIdentity;
    }

    public Integer getIsFace() {
        return isFace;
    }

    public void setIsFace(Integer isFace) {
        this.isFace = isFace;
    }

    public Integer getIsSesame() {
        return isSesame;
    }

    public void setIsSesame(Integer isSesame) {
        this.isSesame = isSesame;
    }

    public Integer getIsOncePay() {
        return isOncePay;
    }

    public void setIsOncePay(Integer isOncePay) {
        this.isOncePay = isOncePay;
    }

    public Integer getIsCredit() {
        return isCredit;
    }

    public void setIsCredit(Integer isCredit) {
        this.isCredit = isCredit;
    }

    public String getHotKeyWord() {
        return hotKeyWord;
    }

    public void setHotKeyWord(String hotKeyWord) {
        this.hotKeyWord = hotKeyWord;
    }
}
