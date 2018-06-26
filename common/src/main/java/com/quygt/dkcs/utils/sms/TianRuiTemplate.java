package com.quygt.dkcs.utils.sms;

/**
 * 天瑞模板枚举类
 */
public enum TianRuiTemplate {

    REGISTER(1, "2181", "注册验证码"),
    LOGIN(2, "2180", "登录验证码");

    TianRuiTemplate(Integer code, String templateId, String message) {
        this.templateId = templateId;
        this.code = code;
        this.message = message;
    }

    private String templateId;

    private Integer code;

    private String message;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
