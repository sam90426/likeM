package com.quygt.dkcs.dao;

import com.quygt.dkcs.dao.base.BaseDao;
import com.quygt.dkcs.model.InfoHelpDic;
import com.quygt.dkcs.model.SysDictionary;

import java.util.List;

public interface SysDictionaryDao extends BaseDao<SysDictionary> {
    String getmax(SysDictionary sysDictionary);

    List<SysDictionary> gettreelist(SysDictionary sysDictionary);
}
