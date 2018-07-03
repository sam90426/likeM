package com.quygt.dkcs.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 好友表实体
 * 
 */
 public class Friends implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户ID
    */
    private Long userId;

    /**
    * 用户昵称
    */
    private String userName;

    /**
    * 好友用户ID
    */
    private Long friendUserId;

    /**
    * 好友用户昵称
    */
    private String friendUserName;

    /**
    * 性别 1=男 2=女
    */
    private Integer friendSex;

    /**
    * 状态 1=申请中 2=申请通过 3=拒绝
    */
    private Integer state;

    /**
    * 创建时间
    */
    private Date createTime;


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
    * 获取用户ID
    *
    * @return 用户ID
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户ID
    * 
    * @param userId 要设置的用户ID
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取用户昵称
    *
    * @return 用户昵称
    */
    public String getUserName(){
        return userName;
    }

    /**
    * 设置用户昵称
    * 
    * @param userName 要设置的用户昵称
    */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
    * 获取好友用户ID
    *
    * @return 好友用户ID
    */
    public Long getFriendUserId(){
        return friendUserId;
    }

    /**
    * 设置好友用户ID
    * 
    * @param friendUserId 要设置的好友用户ID
    */
    public void setFriendUserId(Long friendUserId){
        this.friendUserId = friendUserId;
    }

    /**
    * 获取好友用户昵称
    *
    * @return 好友用户昵称
    */
    public String getFriendUserName(){
        return friendUserName;
    }

    /**
    * 设置好友用户昵称
    * 
    * @param friendUserName 要设置的好友用户昵称
    */
    public void setFriendUserName(String friendUserName){
        this.friendUserName = friendUserName;
    }

    /**
    * 获取性别 1=男 2=女
    *
    * @return 性别 1=男 2=女
    */
    public Integer getFriendSex(){
        return friendSex;
    }

    /**
    * 设置性别 1=男 2=女
    * 
    * @param friendSex 要设置的性别 1=男 2=女
    */
    public void setFriendSex(Integer friendSex){
        this.friendSex = friendSex;
    }

    /**
    * 获取状态 1=申请中 2=申请通过 3=拒绝
    *
    * @return 状态 1=申请中 2=申请通过 3=拒绝
    */
    public Integer getState(){
        return state;
    }

    /**
    * 设置状态 1=申请中 2=申请通过 3=拒绝
    * 
    * @param state 要设置的状态 1=申请中 2=申请通过 3=拒绝
    */
    public void setState(Integer state){
        this.state = state;
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

}