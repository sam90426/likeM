package com.wxx.like.utils;

public class TokenUtil {
    private long id;
    private String userName;
    private String passWord;
    private long expiredTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public TokenUtil(long _id, String _userName, String _passWord, long _expiredTime) {
        this.id = _id;
        this.userName = _userName;
        this.passWord = _passWord;
        this.expiredTime = _expiredTime;
    }

    //获取token
    public String getToken() {
        return DesUtil.desEncrypt(ConfigUtil.getInstance().getString("desKey"), this.toString());
    }

    @Override
    public String toString() {
        return this.id + "|" + this.userName + "|" + this.passWord + "|" + this.expiredTime;
    }
}
