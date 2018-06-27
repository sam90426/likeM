package com.quygt.dkcs.dao.base;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface BaseMapper<T,ID extends Serializable> {

    /**
     * 条数
     * @param record
     * @return
     */
    Integer selectCount(T record);

    boolean insert(T model);

    boolean delete(T model);

    boolean update(T model);

    boolean updateByIds(@Param("t") T model,@Param("ids") Serializable [] ids);

    T getmodel(T model);

    T getById(Serializable id);

    List<T> getlist(T model);
}
