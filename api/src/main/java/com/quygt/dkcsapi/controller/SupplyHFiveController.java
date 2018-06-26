package com.quygt.dkcsapi.controller;

import com.quygt.dkcs.model.*;
import com.quygt.dkcs.service.*;
import com.quygt.dkcs.utils.ConfigUtil;
import com.quygt.dkcs.utils.PageUtil;
import com.quygt.dkcs.utils.ResponseMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import tool.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping(value = "/hfive/supply", produces = "text/plain;charset=UTF-8")
public class SupplyHFiveController {

    @Resource
    private SupplyService supplyService;

    //region 供应商列表
    @RequestMapping(value = "/getSupplyList", method = RequestMethod.POST)
    @ResponseBody
    public String getSupplyList(int supplyType, int recommend, int currPage, int pageSize, HttpServletRequest request) {
        return supplyService.getSupplyList(supplyType, recommend, 1, currPage, pageSize, request);
    }

}
