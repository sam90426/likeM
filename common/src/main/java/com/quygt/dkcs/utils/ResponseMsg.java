package com.quygt.dkcs.utils;

import com.google.gson.Gson;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseMsg<T> {
    //状态码：
    //-1=验证失败
    //0=操作失败
    //1=成功
    //2=登录失败或者token验证失败（重新登录）
    //3=token过期（静默重新获取token）
    private int code;
    //消息内容
    private String message;
    //数据实体
    private T data;

    public String asJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static <T> ResponseMsg<T> New(int _code) {
        ResponseMsg<T> responseMsg = new ResponseMsg<T>();
        responseMsg.code = _code;
        responseMsg.message = "";
        responseMsg.data = null;
        return responseMsg;
    }

    public static <T> ResponseMsg<T> New(int _code, String _message) {
        ResponseMsg<T> responseMsg = new ResponseMsg<T>();
        responseMsg.code = _code;
        responseMsg.message = _message;
        responseMsg.data = null;
        return responseMsg;
    }

    public static <T> ResponseMsg<T> New(int _code, String _message, T _data) {
        ResponseMsg<T> responseMsg = new ResponseMsg<T>();
        responseMsg.code = _code;
        responseMsg.message = _message;
        responseMsg.data = _data;
        return responseMsg;
    }

    public static <T> String dataPage(PageUtil pageUtil) {
        Map<String,Object> map=new HashMap<>();
        ResponseMsg<T> responseMsg = new ResponseMsg<T>();
        responseMsg.code = 1;
        map.put("code",1);
        map.put("message","查询成功");
        map.put("data",pageUtil.getData());
        map.put("total", pageUtil.getTotalItems());
        JSONSerializer serializer = new JSONSerializer();
        serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Date.class);
        serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Timestamp.class);
        return serializer.deepSerialize(map);
    }
}
