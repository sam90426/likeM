package com.wxx.like.service.base;

import com.github.pagehelper.Page;
import com.wxx.like.utils.PageUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基类接口定义
 */
public interface BaseService<T> {

    boolean save(T model);

    boolean delete(T model);

    boolean update(T model);

    T findSelective(Map<String,Object> map);

    boolean updateSelective(Map<String,Object> map);

    T findByPrimary(Long id);

    List<T> listSelective (Map<String,Object> map);

    Integer selectcount(T model);

    Page<T> getPageList(Map<String,Object> map, Integer pageIndex, Integer pageSize);
}
