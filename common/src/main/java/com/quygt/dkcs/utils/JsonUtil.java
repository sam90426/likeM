package com.quygt.dkcs.utils;

import com.google.gson.Gson;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

import java.sql.Timestamp;
import java.util.Date;

public class JsonUtil {
    //Json序列化
    public static String Encode(Object obj) {
        if (obj == null) return null;
        if (obj.getClass() == String.class) {
            return obj.toString();
        }
        JSONSerializer serializer = new JSONSerializer();
        serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Date.class);
        serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Timestamp.class);
        return serializer.deepSerialize(obj);
    }

    //json转实体
    public static <T> T fromJson(String json, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
}
