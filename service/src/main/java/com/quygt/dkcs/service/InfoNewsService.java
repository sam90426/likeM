package com.quygt.dkcs.service;

import com.quygt.dkcs.model.InfoNews;
import com.quygt.dkcs.utils.PageUtil;

import java.util.List;

public interface InfoNewsService {
    /**
     * 添加
     */
    boolean add(InfoNews infoNews);

    /**
     * 根据ID删除
     */
    boolean deleteById(long id);

    /**
     * 修改信息
     */
    boolean update(InfoNews infoNews);

    /**
     * 按条件查找实体
     */
    InfoNews getmodel(InfoNews infoNews);

    /**
     * 按条件查找所有
     */
    List<InfoNews> getList(InfoNews infoNews);

    /**
     * 按条件查询分页
     */
    PageUtil<InfoNews> getpagelist(InfoNews infoNews, int currentPage, int pagesize);
}
