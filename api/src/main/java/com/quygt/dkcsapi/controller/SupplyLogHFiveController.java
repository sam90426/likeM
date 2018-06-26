package com.quygt.dkcsapi.controller;

import com.quygt.dkcs.service.SupplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 该控制器的接口需要token验证  不需要sign验证
 * Created by Administrator on 2017/11/21.
 */

@Controller
@RequestMapping(value = "/html/supplyLog", produces = "text/plain;charset=UTF-8")
public class SupplyLogHFiveController {

    @Resource
    private SupplyService supplyService;

    /**
     * 供应商访问日志 h5
     * @param uid
     * @param supplyId
     * @param specificSource 具体访问来源
     * @return
     */
    @RequestMapping(value = "/addSupplyLog", method = RequestMethod.POST)
    @ResponseBody
    public String addSupplyLog(String uid, Long supplyId,Integer specificSource) {
        return supplyService.insertUpdate(supplyId,2,uid,specificSource);//h5类型的访问日志
    }
}
