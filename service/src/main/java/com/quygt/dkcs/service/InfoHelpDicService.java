package com.quygt.dkcs.service;

import com.quygt.dkcs.model.InfoHelpDic;
import com.quygt.dkcs.utils.PageUtil;

import java.util.List;

public interface InfoHelpDicService {
    /**
     * 添加
     */
    boolean add(InfoHelpDic infoHelpDic);

    /**
     * 根据ID删除
     */
    boolean deleteById(long id);

    /**
     * 修改信息
     */
    boolean update(InfoHelpDic infoHelpDic);

    /**
     * 按条件查找实体
     */
    InfoHelpDic getmodel(InfoHelpDic infoHelpDic);

    /**
     * 按条件查找所有
     */
    List<InfoHelpDic> getList(InfoHelpDic infoHelpDic);

    /**
     * 按条件查询分页
     */
    PageUtil<InfoHelpDic> getpagelist(InfoHelpDic infoHelpDic, int currentPage, int pagesize);

    /**
     * 获取DicPath的最大值
     */
    String getmax(InfoHelpDic infoHelpDic);

    /*
    *树状图列表查询
     */
    List<InfoHelpDic> gettreelist(InfoHelpDic infoHelpDic);
}
