package com.quygt.dkcs.model.vo;

/**
 * 渠道量统计图VO
 */
public class SourceChartVO {

    private Integer count;

    private String time;

    private String sourceName;

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
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
