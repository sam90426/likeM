package com.quygt.dkcs.model;

import java.io.Serializable;

public class BaseModel {
    public int offset;
    public int rows;

    /**
     * 起始时间
     */
    public String startTime;

    /**
     * 结束时间
     */
    public String endTime;

    /**
     * 状态数组
     */
    private Serializable [] states;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Serializable[] getStates() {
        return states;
    }

    public void setStates(Serializable[] states) {
        this.states = states;
    }
}
