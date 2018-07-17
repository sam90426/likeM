package com.wxx.like.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 标签表实体
 * 
 */
 public class Lable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 标签名称
    */
    private String lableName;

    /**
    * 状态 1=显示 2=不显示
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
    * 获取标签名称
    *
    * @return 标签名称
    */
    public String getLableName(){
        return lableName;
    }

    /**
    * 设置标签名称
    * 
    * @param lableName 要设置的标签名称
    */
    public void setLableName(String lableName){
        this.lableName = lableName;
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