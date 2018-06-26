package com.quygt.dkcs.utils.model;

import com.quygt.dkcs.utils.ConfigUtil;

public class JianZhou {
    private String account;
    private String password;
    private String sign;

    public String getAccount() {
        return ConfigUtil.getInstance().getString("jz_account");
    }

    public String getPassword() {
        return ConfigUtil.getInstance().getString("jz_password");
    }

    public String getSign() {
        return ConfigUtil.getInstance().getString("sms_sign");
    }
}
