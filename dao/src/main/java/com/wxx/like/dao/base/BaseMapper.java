package com.wxx.like.dao.base;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {

    boolean save(T model);

    boolean delete(T model);

    boolean update(T model);

    T findSelective(Map<String,Object> map);

    boolean updateSelective(Map<String,Object> map);

    T findByPrimary(Long id);

    List<T> listSelective (Map<String,Object> map);

    Integer selectcount(T model);
}
