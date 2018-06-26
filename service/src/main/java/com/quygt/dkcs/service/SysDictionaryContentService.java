package com.quygt.dkcs.service;

import com.quygt.dkcs.model.SysDictionaryContent;
import com.quygt.dkcs.utils.PageUtil;

import java.util.List;

public interface SysDictionaryContentService {
    /**
     * 添加
     */
    boolean add(SysDictionaryContent sysDictionaryContent);

    /**
     * 根据ID删除
     */
    boolean deleteById(long id);

    /**
     * 修改信息
     */
    boolean update(SysDictionaryContent sysDictionaryContent);

    /**
     * 按条件查找实体
     */
    SysDictionaryContent getmodel(SysDictionaryContent sysDictionaryContent);

    /**
     * 按条件查找所有
     */
    List<SysDictionaryContent> getList(SysDictionaryContent sysDictionaryContent);

    /**
     * 按条件查询分页
     */
    PageUtil<SysDictionaryContent> getpagelist(SysDictionaryContent sysDictionaryContent, int currentPage, int pagesize);
}
