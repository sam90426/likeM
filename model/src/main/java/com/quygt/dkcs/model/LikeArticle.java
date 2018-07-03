package com.quygt.dkcs.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 官方文章表实体
 * 
 */
 public class LikeArticle implements Serializable {

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
    * 创建时间
    */
    private Date createTime;

    /**
    * 标题
    */
    private String title;

    /**
    * 状态 1=显示 2=不显示
    */
    private Integer state;

    /**
    * 浏览次数
    */
    private Integer hits;


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

    /**
    * 获取标题
    *
    * @return 标题
    */
    public String getTitle(){
        return title;
    }

    /**
    * 设置标题
    * 
    * @param title 要设置的标题
    */
    public void setTitle(String title){
        this.title = title;
    }

    /**
    * 获取状态 1=显示 2=不显示
    *
    * @return 状态 1=显示 2=不显示
    */
    public Integer getState(){
        return state;
    }

    /**
    * 设置状态 1=显示 2=不显示
    * 
    * @param state 要设置的状态 1=显示 2=不显示
    */
    public void setState(Integer state){
        this.state = state;
    }

    /**
    * 获取浏览次数
    *
    * @return 浏览次数
    */
    public Integer getHits(){
        return hits;
    }

    /**
    * 设置浏览次数
    * 
    * @param hits 要设置的浏览次数
    */
    public void setHits(Integer hits){
        this.hits = hits;
    }

}