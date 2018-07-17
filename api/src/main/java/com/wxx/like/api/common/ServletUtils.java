package com.wxx.like.api.common;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;


public class ServletUtils {

	private static final String CONTENT_TYPE = "content-type";

	public static final String JS_TYPE = "text/javascript";

	public static final String CODE_UTF8 = "UTF-8";

	public static void writeToResponse(HttpServletResponse response, Map<? extends Object, Object> res) {
		response.addHeader(CONTENT_TYPE, JS_TYPE);
		response.setContentType("application/json");
		response.setCharacterEncoding(CODE_UTF8);
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(response.getOutputStream(), CODE_UTF8);
		} catch (UnsupportedEncodingException e) {
			//logger.error(e.getMessage(), e);
		} catch (IOException e) {
			//logger.error(e.getMessage(), e);
		}
		JsonUtil.write(out, res);
	}

}
