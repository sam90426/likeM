package com.quygt.dkcs.service;

import com.quygt.dkcs.model.InfoGGDic;
import com.quygt.dkcs.utils.PageUtil;

import java.util.List;

public interface InfoGGDicService {
    /**
     * 添加
     */
    boolean add(InfoGGDic infoGGDic);

    /**
     * 根据ID删除
     */
    boolean deleteById(long id);

    /**
     * 修改信息
     */
    boolean update(InfoGGDic infoGGDic);

    /**
     * 按条件查找实体
     */
    InfoGGDic getmodel(InfoGGDic infoGGDic);

    /**
     * 按条件查找所有
     */
    List<InfoGGDic> getList(InfoGGDic infoGGDic);

    /**
     * 按条件查询分页
     */
    PageUtil<InfoGGDic> getpagelist(InfoGGDic infoGGDic, int currentPage, int pagesize);

    /**
     * 获取DicPath的最大值
     */
    String getmax(InfoGGDic infoGGDic);

    /*
    *树状图列表查询
     */
    List<InfoGGDic> gettreelist(InfoGGDic infoGGDic);
}
