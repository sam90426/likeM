package com.wxx.like.service.impl.base;

import com.wxx.like.service.base.BaseService;
import com.wxx.like.utils.PageUtil;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    @Override
    public Integer selectCount(T record) {
        return null;
    }

    @Override
    public boolean insert(T record) {
        return false;
    }

    @Override
    public boolean updateById(T record) {
        return false;
    }

    @Override
    public boolean updateByIds(T record, Serializable[] ids) {
        return false;
    }

    @Override
    public T getById(Serializable id) {
        return null;
    }

    @Override
    public boolean delete(T e) {
        return false;
    }

    @Override
    public PageUtil<T> getPageList(T e, Integer currPage, Integer pageSize) {
        return null;
    }

    @Override
    public List<T> getlist(T e) {
        return null;
    }

    @Override
    public T getmodel(T e) {
        return null;
    }

    @Override
    public boolean update(T e) {
        return false;
    }
}