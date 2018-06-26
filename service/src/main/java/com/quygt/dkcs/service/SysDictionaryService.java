package com.quygt.dkcs.service;

import com.quygt.dkcs.model.SysDictionary;
import com.quygt.dkcs.utils.PageUtil;

import java.util.List;

public interface SysDictionaryService {
    /**
     * 添加
     */
    boolean add(SysDictionary sysDictionary);

    /**
     * 根据ID删除
     */
    boolean deleteById(long id);

    /**
     * 修改信息
     */
    boolean update(SysDictionary sysDictionary);

    /**
     * 按条件查找实体
     */
    SysDictionary getmodel(SysDictionary sysDictionary);

    /**
     * 按条件查找所有
     */
    List<SysDictionary> getList(SysDictionary sysDictionary);

    /**
     * 按条件查询分页
     */
    PageUtil<SysDictionary> getpagelist(SysDictionary sysDictionary, int currentPage, int pagesize);

    /**
     * 获取DicPath的最大值
     */
    String getmax(SysDictionary sysDictionary);

    /*
    *树状图列表查询
     */
    List<SysDictionary> gettreelist(SysDictionary sysDictionary);
}
