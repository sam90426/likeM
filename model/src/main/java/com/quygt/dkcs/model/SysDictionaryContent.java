package com.quygt.dkcs.model;

import java.util.Date;

public class SysDictionaryContent {
    private long id;
    private  String dicPath;
    private  String string1;
    private  String string2;
    private  String string3;
    private  String string4;
    private  Integer zs1;
    private  Integer zs2;
    private  Integer zs3;
    private  Integer zs4;
    private  String intro1;
    private  String intro2;
    private  String content;
    private  int orderIndex;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDicPath() {
        return dicPath;
    }

    public void setDicPath(String dicPath) {
        this.dicPath = dicPath;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public String getString3() {
        return string3;
    }

    public void setString3(String string3) {
        this.string3 = string3;
    }

    public String getString4() {
        return string4;
    }

    public void setString4(String string4) {
        this.string4 = string4;
    }

    public Integer getZs1() {
        return zs1;
    }

    public void setZs1(Integer zs1) {
        this.zs1 = zs1;
    }

    public Integer getZs2() {
        return zs2;
    }

    public void setZs2(Integer zs2) {
        this.zs2 = zs2;
    }

    public Integer getZs3() {
        return zs3;
    }

    public void setZs3(Integer zs3) {
        this.zs3 = zs3;
    }

    public Integer getZs4() {
        return zs4;
    }

    public void setZs4(Integer zs4) {
        this.zs4 = zs4;
    }

    public String getIntro1() {
        return intro1;
    }

    public void setIntro1(String intro1) {
        this.intro1 = intro1;
    }

    public String getIntro2() {
        return intro2;
    }

    public void setIntro2(String intro2) {
        this.intro2 = intro2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
