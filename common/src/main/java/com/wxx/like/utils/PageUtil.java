package com.wxx.like.utils;

import java.util.List;

public class PageUtil<T> {
    //当前页码
    private int currPage;
    //每页条数
    private int pageSize;
    //总页数
    private int totalPages;
    //总记录条数
    private long totalItems;
    //数据
    private List<T> data;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public PageUtil(int _currPage, int _pageSize, int _totalPages, long _totalItems, List<T> _data) {
        this.currPage = _currPage;
        this.pageSize = _pageSize;
        this.totalPages = _totalPages;
        this.totalItems = _totalItems;
        this.data = _data;
    }

    /**
     *  totalPages由该类根据size和total计算不用特意注入
     * @param _currPage
     * @param _pageSize
     * @param _totalItems
     * @param _data
     */
    public PageUtil(int _currPage, int _pageSize, long _totalItems, List<T> _data) {
        this.currPage = _currPage;
        this.pageSize = _pageSize;
        this.totalItems = _totalItems;
        this.totalPages = this.pageSize == 0?1:(int)Math.ceil((double)this.totalItems / (double)this.pageSize);
        this.data = _data;
    }
}

