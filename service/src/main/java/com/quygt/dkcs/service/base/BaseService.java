package com.quygt.dkcs.service.base;

import com.quygt.dkcs.utils.PageUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 基类接口定义
 */
public interface BaseService<T, ID extends Serializable> {

    /**
     * 条数
     * @param record
     * @return
     */
    Integer selectCount(T record);

    boolean insert(T record);


    boolean updateById(T record);

    boolean updateByIds(T record,Serializable [] ids);

    T getById(Serializable id);

    /**
     * 根据实体删除
     *
     * @param e
     * @return
     */
    boolean delete(T e);

    PageUtil<T> getPageList(T e, Integer currPage, Integer pageSize);

    /**
     * 查询列表 未分页
     *
     * @param e
     * @return
     */
    List<T> getlist(T e);

    T getmodel(T e);

    boolean update(T e);

}
