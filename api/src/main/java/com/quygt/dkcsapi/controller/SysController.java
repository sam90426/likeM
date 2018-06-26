package com.quygt.dkcsapi.controller;

import com.quygt.dkcs.model.SysDictionaryContent;
import com.quygt.dkcs.service.SysDictionaryContentService;
import com.quygt.dkcs.utils.ConfigUtil;
import com.quygt.dkcs.utils.ResponseMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/sys")
public class SysController {
    @Resource
    private SysDictionaryContentService sysDictionaryContentService;

    //region 获取APP版本
    @RequestMapping(value = "/getversion",method = RequestMethod.POST)
    @ResponseBody
    public String getVersion(int type) {
        String key = ConfigUtil.getInstance().getString("appversion");
        SysDictionaryContent sysDictionaryContent = new SysDictionaryContent();
        if (type == 1 || type == 2) {
            String typename = type == 1 ? "Android" : type == 2 ? "IOS" : "";
            if (!typename.equals("")) {
                sysDictionaryContent.setString1(typename);
                sysDictionaryContent = sysDictionaryContentService.getmodel(sysDictionaryContent);
                Map data = new HashMap<String, Object>();
                data.put("data", sysDictionaryContent);
                return ResponseMsg.New(1, "success", data).asJson();
            } else {
                return ResponseMsg.New(-1, "获取失败").asJson();
            }
        } else {
            return ResponseMsg.New(-1, "获取失败").asJson();
        }
    }
    //endregion
}
