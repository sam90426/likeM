package com.wxx.like.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 动态表实体
 * 
 */
 public class CircleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户编号
    */
    private Long userId;

    /**
    * 用户名称
    */
    private String userName;

    /**
    * 性别 1=男 2=女
    */
    private Integer sex;

    /**
    * 用户头像
    */
    private String logo;

    /**
    * 等级
    */
    private Integer leave;

    /**
    * 内容
    */
    private String content;

    /**
    * 图片
    */
    private String picUrl;

    /**
    * 点赞次数
    */
    private Integer zanCount;

    /**
    * 评论次数
    */
    private Integer commentCount;

    /**
    * 标签
    */
    private String lable;

    /**
    * 城市定位
    */
    private String country;

    /**
    * 是否公开 1=公开 2=不公开
    */
    private Integer isOut;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
     * 类型 1=纯文 2=有图 3=有视频
     */
    private Integer type;

    private List<CircleZan> zanList;

    private List<CircleComment> commentList;


    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取用户编号
    *
    * @return 用户编号
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户编号
    * 
    * @param userId 要设置的用户编号
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取用户名称
    *
    * @return 用户名称
    */
    public String getUserName(){
        return userName;
    }

    /**
    * 设置用户名称
    * 
    * @param userName 要设置的用户名称
    */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
    * 获取性别 1=男 2=女
    *
    * @return 性别 1=男 2=女
    */
    public Integer getSex(){
        return sex;
    }

    /**
    * 设置性别 1=男 2=女
    * 
    * @param sex 要设置的性别 1=男 2=女
    */
    public void setSex(Integer sex){
        this.sex = sex;
    }

    /**
    * 获取用户头像
    *
    * @return 用户头像
    */
    public String getLogo(){
        return logo;
    }

    /**
    * 设置用户头像
    * 
    * @param logo 要设置的用户头像
    */
    public void setLogo(String logo){
        this.logo = logo;
    }

    /**
    * 获取等级
    *
    * @return 等级
    */
    public Integer getLeave(){
        return leave;
    }

    /**
    * 设置等级
    * 
    * @param leave 要设置的等级
    */
    public void setLeave(Integer leave){
        this.leave = leave;
    }

    /**
    * 获取内容
    *
    * @return 内容
    */
    public String getContent(){
        return content;
    }

    /**
    * 设置内容
    * 
    * @param content 要设置的内容
    */
    public void setContent(String content){
        this.content = content;
    }

    /**
    * 获取图片
    *
    * @return 图片
    */
    public String getPicUrl(){
        return picUrl;
    }

    /**
    * 设置图片
    * 
    * @param picUrl 要设置的图片
    */
    public void setPicUrl(String picUrl){
        this.picUrl = picUrl;
    }

    /**
    * 获取点赞次数
    *
    * @return 点赞次数
    */
    public Integer getZanCount(){
        return zanCount;
    }

    /**
    * 设置点赞次数
    * 
    * @param zanCount 要设置的点赞次数
    */
    public void setZanCount(Integer zanCount){
        this.zanCount = zanCount;
    }

    /**
    * 获取评论次数
    *
    * @return 评论次数
    */
    public Integer getCommentCount(){
        return commentCount;
    }

    /**
    * 设置评论次数
    * 
    * @param commentCount 要设置的评论次数
    */
    public void setCommentCount(Integer commentCount){
        this.commentCount = commentCount;
    }

    /**
    * 获取标签
    *
    * @return 标签
    */
    public String getLable(){
        return lable;
    }

    /**
    * 设置标签
    * 
    * @param lable 要设置的标签
    */
    public void setLable(String lable){
        this.lable = lable;
    }

    /**
    * 获取城市定位
    *
    * @return 城市定位
    */
    public String getCountry(){
        return country;
    }

    /**
    * 设置城市定位
    * 
    * @param country 要设置的城市定位
    */
    public void setCountry(String country){
        this.country = country;
    }

    /**
    * 获取是否公开 1=公开 2=不公开
    *
    * @return 是否公开 1=公开 2=不公开
    */
    public Integer getIsOut(){
        return isOut;
    }

    /**
    * 设置是否公开 1=公开 2=不公开
    * 
    * @param isOut 要设置的是否公开 1=公开 2=不公开
    */
    public void setIsOut(Integer isOut){
        this.isOut = isOut;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置创建时间
    * 
    * @param createTime 要设置的创建时间
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public List<CircleZan> getZanList() {
        return zanList;
    }

    public void setZanList(List<CircleZan> zanList) {
        this.zanList = zanList;
    }

    public List<CircleComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CircleComment> commentList) {
        this.commentList = commentList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}