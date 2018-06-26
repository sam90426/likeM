package com.quygt.dkcs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.InfoGGDicDao;
import com.quygt.dkcs.model.InfoGGDic;
import com.quygt.dkcs.service.InfoGGDicService;
import com.quygt.dkcs.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoGGDicService")
public class InfoGGDicServiceImpl implements InfoGGDicService {

    @Resource
    private InfoGGDicDao infoGGDicDao;

    public boolean add(InfoGGDic infoGGDic) {
        return infoGGDicDao.insert(infoGGDic);
    }

    public boolean deleteById(long id) {
        InfoGGDic infoGGDic = new InfoGGDic();
        infoGGDic.setId(id);
        return infoGGDicDao.delete(infoGGDic);
    }

    public boolean update(InfoGGDic infoGGDic) {
        return infoGGDicDao.update(infoGGDic);
    }

    public InfoGGDic getmodel(InfoGGDic infoGGDic) {
        return infoGGDicDao.getmodel(infoGGDic);
    }

    public List<InfoGGDic> getList(InfoGGDic infoGGDic) {
        return infoGGDicDao.getlist(infoGGDic);
    }

    public PageUtil<InfoGGDic> getpagelist(InfoGGDic infoGGDic, int currentPage, int pagesize) {
        try {
            Page<InfoGGDic> page = PageHelper.startPage(currentPage, pagesize);
            infoGGDicDao.getlist(infoGGDic);
            PageUtil data = new PageUtil(currentPage, pagesize, page.getPages(), page.getTotal(), page.getResult());
            return data;
        } catch (Exception e) {

        }
        return null;
    }

    public String getmax(InfoGGDic infoGGDic) {
        return infoGGDicDao.getmax(infoGGDic);
    }

    public List<InfoGGDic> gettreelist(InfoGGDic infoGGDic) {
        return infoGGDicDao.gettreelist(infoGGDic);
    }
}
