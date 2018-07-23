package com.wxx.like.service.impl.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wxx.like.dao.base.BaseMapper;
import com.wxx.like.service.base.BaseService;
import com.wxx.like.utils.PageUtil;

import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    public abstract BaseMapper<T> getMapper();

    @Override
    public boolean save(T model) {
        return getMapper().save(model);
    }

    @Override
    public boolean delete(T model) {
        return getMapper().delete(model);
    }

    @Override
    public boolean update(T model) {
        return getMapper().update(model);
    }

    @Override
    public T findSelective(Map<String, Object> map) {
        return getMapper().findSelective(map);
    }

    @Override
    public boolean updateSelective(Map<String, Object> map) {
        return getMapper().updateSelective(map);
    }

    @Override
    public T findByPrimary(Long id) {
        return getMapper().findByPrimary(id);
    }

    @Override
    public List<T> listSelective(Map<String, Object> map) {
        return getMapper().listSelective(map);
    }

    @Override
    public Integer selectcount(T model) {
        return getMapper().selectcount(model);
    }

    @Override
    public Page<T> getPageList(Map<String,Object> map, Integer pageIndex, Integer pageSize) {

        PageHelper.startPage(pageIndex, pageSize);
        List<T> list = getMapper().listSelective(map);
        return (Page<T>) list;
    }
}
