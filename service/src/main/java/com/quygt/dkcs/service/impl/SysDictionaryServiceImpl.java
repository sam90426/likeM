package com.quygt.dkcs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.InfoHelpDicDao;
import com.quygt.dkcs.dao.SysDictionaryDao;
import com.quygt.dkcs.model.SysDictionary;
import com.quygt.dkcs.service.InfoHelpDicService;
import com.quygt.dkcs.service.SysDictionaryService;
import com.quygt.dkcs.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("SysDictionaryService")
public class SysDictionaryServiceImpl implements SysDictionaryService {

    @Resource
    private SysDictionaryDao sysDictionaryDao;

    public boolean add(SysDictionary sysDictionary) {
        return sysDictionaryDao.insert(sysDictionary);
    }

    public boolean deleteById(long id) {
        SysDictionary sysDictionary = new SysDictionary();
        sysDictionary.setId(id);
        return sysDictionaryDao.delete(sysDictionary);
    }

    public boolean update(SysDictionary sysDictionary) {
        return sysDictionaryDao.update(sysDictionary);
    }

    public SysDictionary getmodel(SysDictionary sysDictionary) {
        return sysDictionaryDao.getmodel(sysDictionary);
    }

    public List<SysDictionary> getList(SysDictionary sysDictionary) {
        return sysDictionaryDao.getlist(sysDictionary);
    }

    public PageUtil<SysDictionary> getpagelist(SysDictionary sysDictionary, int currentPage, int pagesize) {
        try {
            Page<SysDictionary> page = PageHelper.startPage(currentPage, pagesize);
            sysDictionaryDao.getlist(sysDictionary);
            PageUtil data = new PageUtil(currentPage, pagesize, page.getPages(), page.getTotal(), page.getResult());
            return data;
        } catch (Exception e) {

        }
        return null;
    }

    public String getmax(SysDictionary sysDictionary) {
        return sysDictionaryDao.getmax(sysDictionary);
    }

    public List<SysDictionary> gettreelist(SysDictionary sysDictionary) {
        return sysDictionaryDao.gettreelist(sysDictionary);
    }
}
