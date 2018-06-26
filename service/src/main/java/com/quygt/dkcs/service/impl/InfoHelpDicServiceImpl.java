package com.quygt.dkcs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.InfoGGDicDao;
import com.quygt.dkcs.dao.InfoHelpDicDao;
import com.quygt.dkcs.model.InfoHelpDic;
import com.quygt.dkcs.service.InfoGGDicService;
import com.quygt.dkcs.service.InfoHelpDicService;
import com.quygt.dkcs.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoHelpDicService")
public class InfoHelpDicServiceImpl implements InfoHelpDicService {

    @Resource
    private InfoHelpDicDao infoHelpDicDao;

    public boolean add(InfoHelpDic infoHelpDic) {
        return infoHelpDicDao.insert(infoHelpDic);
    }

    public boolean deleteById(long id) {
        InfoHelpDic infoHelpDic = new InfoHelpDic();
        infoHelpDic.setId(id);
        return infoHelpDicDao.delete(infoHelpDic);
    }

    public boolean update(InfoHelpDic infoHelpDic) {
        return infoHelpDicDao.update(infoHelpDic);
    }

    public InfoHelpDic getmodel(InfoHelpDic infoHelpDic) {
        return infoHelpDicDao.getmodel(infoHelpDic);
    }

    public List<InfoHelpDic> getList(InfoHelpDic infoHelpDic) {
        return infoHelpDicDao.getlist(infoHelpDic);
    }

    public PageUtil<InfoHelpDic> getpagelist(InfoHelpDic infoHelpDic, int currentPage, int pagesize) {
        try {
            Page<InfoHelpDic> page = PageHelper.startPage(currentPage, pagesize);
            infoHelpDicDao.getlist(infoHelpDic);
            PageUtil data = new PageUtil(currentPage, pagesize, page.getPages(), page.getTotal(), page.getResult());
            return data;
        } catch (Exception e) {

        }
        return null;
    }

    public String getmax(InfoHelpDic infoHelpDic) {
        return infoHelpDicDao.getmax(infoHelpDic);
    }

    public List<InfoHelpDic> gettreelist(InfoHelpDic infoHelpDic) {
        return infoHelpDicDao.gettreelist(infoHelpDic);
    }
}
