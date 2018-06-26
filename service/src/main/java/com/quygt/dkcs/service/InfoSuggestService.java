package com.quygt.dkcs.service;

import com.quygt.dkcs.model.InfoSuggest;
import com.quygt.dkcs.utils.PageUtil;

import java.util.List;

public interface InfoSuggestService {
    /**
     * 添加
     */
    boolean add(InfoSuggest infoSuggest);

    /**
     * 根据ID删除
     */
    boolean deleteById(long id);

    /**
     * 修改信息
     */
    boolean update(InfoSuggest infoSuggest);

    /**
     * 按条件查找实体
     */
    InfoSuggest getmodel(InfoSuggest infoSuggest);

    /**
     * 按条件查找所有
     */
    List<InfoSuggest> getList(InfoSuggest infoSuggest);

    /**
     * 按条件查询分页
     */
    PageUtil<InfoSuggest> getpagelist(InfoSuggest infoSuggest, int currentPage, int pagesize);
}
