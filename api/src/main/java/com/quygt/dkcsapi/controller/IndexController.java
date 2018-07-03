package com.quygt.dkcsapi.controller;

import com.quygt.dkcsapi.common.ServletUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: wuxianxin
 * @Date: 2018/7/3 15:07
 * @Description:
 */
@RequestMapping(value = "/index", produces = "text/plain;charset=UTF-8")
public class IndexController extends BaseController {


    //region 首页

    /**
     * 首页
     *
     * @param userId
     * @param label
     * @param lastcircleId
     * @param pageindex
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/findIndex")
    public void findIndex(@RequestParam(value = "userId", required = true) Long userId,
                          @RequestParam(value = "label", required = true) String label,
                          @RequestParam(value = "lastcircleId", required = true) Long lastcircleId,
                          @RequestParam(value = "pageindex", required = true) Integer pageindex,
                          HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();

        ServletUtils.writeToResponse(response, result);
    }
    //endregion
}
