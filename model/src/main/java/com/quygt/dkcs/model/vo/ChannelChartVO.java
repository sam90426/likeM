package com.quygt.dkcs.model.vo;

/**
 * 渠道量统计图VO
 */
public class ChannelChartVO {

    private Integer count;

    private String time;

    private String supplyName;

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
