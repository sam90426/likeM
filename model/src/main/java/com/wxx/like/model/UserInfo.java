package com.wxx.like.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表实体
 * 
 */
 public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 登录名
    */
    private String loginName;

    /**
    * 登陆密码
    */
    private String password;

    /**
    * 用户昵称
    */
    private String userName;

    /**
    * 个性签名
    */
    private String sign;

    /**
    * 性别 1=男 2=女 默认为1
    */
    private Integer sex;

    /**
    * 用户状态 1=正常 2=停用
    */
    private Integer state;

    /**
    * 头像
    */
    private String logo;

    /**
    * 手机号
    */
    private String mobile;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 保存标签ID 用英文逗号隔开
    */
    private String label;

    /**
    * 等级
    */
    private Integer level;

    /**
    * 经验值
    */
    private Integer experience;

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
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取登录名
    *
    * @return 登录名
    */
    public String getLoginName(){
        return loginName;
    }

    /**
    * 设置登录名
    * 
    * @param loginName 要设置的登录名
    */
    public void setLoginName(String loginName){
        this.loginName = loginName;
    }

    /**
    * 获取登陆密码
    *
    * @return 登陆密码
    */
    public String getPassword(){
        return password;
    }

    /**
    * 设置登陆密码
    * 
    * @param password 要设置的登陆密码
    */
    public void setPassword(String password){
        this.password = password;
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
    * 获取个性签名
    *
    * @return 个性签名
    */
    public String getSign(){
        return sign;
    }

    /**
    * 设置个性签名
    * 
    * @param sign 要设置的个性签名
    */
    public void setSign(String sign){
        this.sign = sign;
    }

    /**
    * 获取性别 1=男 2=女 默认为1
    *
    * @return 性别 1=男 2=女 默认为1
    */
    public Integer getSex(){
        return sex;
    }

    /**
    * 设置性别 1=男 2=女 默认为1
    * 
    * @param sex 要设置的性别 1=男 2=女 默认为1
    */
    public void setSex(Integer sex){
        this.sex = sex;
    }

    /**
    * 获取用户状态 1=正常 2=停用
    *
    * @return 用户状态 1=正常 2=停用
    */
    public Integer getState(){
        return state;
    }

    /**
    * 设置用户状态 1=正常 2=停用
    * 
    * @param state 要设置的用户状态 1=正常 2=停用
    */
    public void setState(Integer state){
        this.state = state;
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
    * 获取手机号
    *
    * @return 手机号
    */
    public String getMobile(){
        return mobile;
    }

    /**
    * 设置手机号
    * 
    * @param mobile 要设置的手机号
    */
    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    /**
    * 获取邮箱
    *
    * @return 邮箱
    */
    public String getEmail(){
        return email;
    }

    /**
    * 设置邮箱
    * 
    * @param email 要设置的邮箱
    */
    public void setEmail(String email){
        this.email = email;
    }

    /**
    * 获取保存标签ID 用英文逗号隔开
    *
    * @return 保存标签ID 用英文逗号隔开
    */
    public String getLabel(){
        return label;
    }

    /**
    * 设置保存标签ID 用英文逗号隔开
    * 
    * @param label 要设置的保存标签ID 用英文逗号隔开
    */
    public void setLabel(String label){
        this.label = label;
    }

    /**
    * 获取等级
    *
    * @return 等级
    */
    public Integer getLevel(){
        return level;
    }

    /**
    * 设置等级
    * 
    * @param level 要设置的等级
    */
    public void setLevel(Integer level){
        this.level = level;
    }

    /**
    * 获取经验值
    *
    * @return 经验值
    */
    public Integer getExperience(){
        return experience;
    }

    /**
    * 设置经验值
    * 
    * @param experience 要设置的经验值
    */
    public void setExperience(Integer experience){
        this.experience = experience;
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