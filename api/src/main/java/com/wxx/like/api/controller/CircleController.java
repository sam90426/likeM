package com.wxx.like.api.controller;

import com.wxx.like.api.common.ServletUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @Auther: wuxianxin
 * @Date: 2018/7/17 18:02
 * @Description:
 */
public class CircleController extends BaseController {


    //region 好友动态列表

    /**
     * @param userId
     * @param pageIndex
     * @param response
     * @throws Exception
     */
    public void friendsCircle(@RequestParam(value = "userId", required = true) Long userId,
                              @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
                              HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();

        ServletUtils.writeToResponse(response, result);
    }
    //endregion
}
