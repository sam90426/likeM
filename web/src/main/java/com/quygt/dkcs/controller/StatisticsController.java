package com.quygt.dkcs.controller;

import com.quygt.dkcs.model.vo.ChannelChartVO;
import com.quygt.dkcs.model.vo.SourceChartVO;
import com.quygt.dkcs.service.SupplyLogService;
import com.quygt.dkcs.utils.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.quygt.dkcs.utils.JsonUtil.Encode;

@Controller
@RequestMapping(value = "/statistics", produces = "text/plain;charset=UTF-8")
public class StatisticsController {

    @Autowired
    private SupplyLogService supplyLogService;

    private static final String CHANNEL_CHART = "statistics/channelChart";

    private static final String SOURCE_CHART = "statistics/sourceChart";

    @RequestMapping("/channelChart")
    public String channelChart() {
        return CHANNEL_CHART;
    }

    @RequestMapping("/sourceChart")
    public String sourceChart() {
        return SOURCE_CHART;
    }

    @RequestMapping("/channelChartData")
    @ResponseBody
    public String channelChartData(@RequestParam String startTime, @RequestParam String endTime) {
        List<ChannelChartVO> channelChartVOS = supplyLogService.selectChannelChartData(startTime, endTime);
        return Encode(channelChartVOS);
    }

    @RequestMapping("/sourceChartData")
    @ResponseBody
    public String sourceChartData(@RequestParam String startTime, @RequestParam String endTime) {
        //邀请配置字典编码
        String dicPath = ConfigUtil.getInstance().getString("specific_source");
        List<SourceChartVO> sourceChartVOS = supplyLogService.selectSourceChartData(startTime, endTime, dicPath);
        return Encode(sourceChartVOS);
    }
}
