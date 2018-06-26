package com.quygt.dkcs.dao;

import com.quygt.dkcs.dao.base.BaseDao;
import com.quygt.dkcs.model.InfoGGDic;
import com.quygt.dkcs.model.InfoHelpDic;

import java.util.List;

public interface InfoHelpDicDao extends BaseDao<InfoHelpDic> {
    String getmax(InfoHelpDic infoHelpDic);

    List<InfoHelpDic> gettreelist(InfoHelpDic infoHelpDic);
}
