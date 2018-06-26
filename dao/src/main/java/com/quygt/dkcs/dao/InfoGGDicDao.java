package com.quygt.dkcs.dao;

import com.quygt.dkcs.dao.base.BaseDao;
import com.quygt.dkcs.model.InfoGGDic;

import java.util.List;

public interface InfoGGDicDao extends BaseDao<InfoGGDic> {
    String getmax(InfoGGDic infoGGDic);

    List<InfoGGDic> gettreelist(InfoGGDic infoGGDic);
}
