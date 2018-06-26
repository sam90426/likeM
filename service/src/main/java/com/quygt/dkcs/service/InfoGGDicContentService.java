package com.quygt.dkcs.service;

import com.quygt.dkcs.model.InfoGGDicContent;
import com.quygt.dkcs.utils.PageUtil;

import java.util.List;

public interface InfoGGDicContentService {
    /**
     * 添加
     */
    boolean add(InfoGGDicContent infoGGDicContent);

    /**
     * 根据ID删除
     */
    boolean deleteById(long id);

    /**
     * 修改信息
     */
    boolean update(InfoGGDicContent infoGGDicContent);

    /**
     * 按条件查找实体
     */
    InfoGGDicContent getmodel(InfoGGDicContent infoGGDicContent);

    /**
     * 按条件查找所有
     */
    List<InfoGGDicContent> getList(InfoGGDicContent infoGGDicContent);

    /**
     * 按条件查询分页
     */
    PageUtil<InfoGGDicContent> getpagelist(InfoGGDicContent infoGGDicContent, int currentPage, int pagesize);

    /**
     * 开关
     * @return
     */
    Integer getState(Integer type);
}
