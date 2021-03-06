package com.wxx.like.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 官方文章评论实体
 * 
 */
 public class LikeComment implements Serializable {

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
    * 头像
    */
    private String logo;

    /**
    * 官方文章ID
    */
    private Long likeId;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 回复的用户ID
    */
    private Long replyUserId;

    /**
    * 回复的用户名称
    */
    private String replyUserName;

    /**
    * 评论内容
    */
    private String comment;

    /**
    * 官方文章标题
    */
    private String likeTitle;


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
    * 获取头像
    *
    * @return 头像
    */
    public String getLogo(){
        return logo;
    }

    /**
    * 设置头像
    * 
    * @param logo 要设置的头像
    */
    public void setLogo(String logo){
        this.logo = logo;
    }

    /**
    * 获取官方文章ID
    *
    * @return 官方文章ID
    */
    public Long getLikeId(){
        return likeId;
    }

    /**
    * 设置官方文章ID
    * 
    * @param likeId 要设置的官方文章ID
    */
    public void setLikeId(Long likeId){
        this.likeId = likeId;
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
    * 获取回复的用户ID
    *
    * @return 回复的用户ID
    */
    public Long getReplyUserId(){
        return replyUserId;
    }

    /**
    * 设置回复的用户ID
    * 
    * @param replyUserId 要设置的回复的用户ID
    */
    public void setReplyUserId(Long replyUserId){
        this.replyUserId = replyUserId;
    }

    /**
    * 获取回复的用户名称
    *
    * @return 回复的用户名称
    */
    public String getReplyUserName(){
        return replyUserName;
    }

    /**
    * 设置回复的用户名称
    * 
    * @param replyUserName 要设置的回复的用户名称
    */
    public void setReplyUserName(String replyUserName){
        this.replyUserName = replyUserName;
    }

    /**
    * 获取评论内容
    *
    * @return 评论内容
    */
    public String getComment(){
        return comment;
    }

    /**
    * 设置评论内容
    * 
    * @param comment 要设置的评论内容
    */
    public void setComment(String comment){
        this.comment = comment;
    }

    /**
    * 获取官方文章标题
    *
    * @return 官方文章标题
    */
    public String getLikeTitle(){
        return likeTitle;
    }

    /**
    * 设置官方文章标题
    * 
    * @param likeTitle 要设置的官方文章标题
    */
    public void setLikeTitle(String likeTitle){
        this.likeTitle = likeTitle;
    }

}