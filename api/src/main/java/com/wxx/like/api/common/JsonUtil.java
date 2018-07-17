package com.wxx.like.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.Writer;
import java.text.SimpleDateFormat;

public class JsonUtil extends tool.util.JsonUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); //去掉默认的时间戳格式
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true); //单引号处理
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //反序列化时，属性不存在的兼容处理
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false); //设置不写NULLmap值
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); //空值不序列化
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); //序列化时，日期的统一格式
	}

	public static void write(Writer writer,Object o){
		try {
			mapper.writeValue(writer,o);
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
