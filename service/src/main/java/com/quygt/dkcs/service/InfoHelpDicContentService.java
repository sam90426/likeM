package com.quygt.dkcs.service;

import com.quygt.dkcs.model.InfoHelpDicContent;
import com.quygt.dkcs.utils.PageUtil;

import java.util.List;

public interface InfoHelpDicContentService {
    /**
     * 添加
     */
    boolean add(InfoHelpDicContent infoHelpDicContent);

    /**
     * 根据ID删除
     */
    boolean deleteById(long id);

    /**
     * 修改信息
     */
    boolean update(InfoHelpDicContent infoHelpDicContent);

    /**
     * 按条件查找实体
     */
    InfoHelpDicContent getmodel(InfoHelpDicContent infoHelpDicContent);

    /**
     * 按条件查找所有
     */
    List<InfoHelpDicContent> getList(InfoHelpDicContent infoHelpDicContent);

    /**
     * 按条件查询分页
     */
    PageUtil<InfoHelpDicContent> getpagelist(InfoHelpDicContent infoHelpDicContent, int currentPage, int pagesize);
}
