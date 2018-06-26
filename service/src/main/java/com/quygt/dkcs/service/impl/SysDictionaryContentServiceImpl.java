package com.quygt.dkcs.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.SysDictionaryContentDao;
import com.quygt.dkcs.model.SysDictionaryContent;
import com.quygt.dkcs.service.SysDictionaryContentService;
import com.quygt.dkcs.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("SysDictionaryContentService")
public class SysDictionaryContentServiceImpl implements SysDictionaryContentService {

    @Resource
    private SysDictionaryContentDao sysDictionaryContentDao;

    public boolean add(SysDictionaryContent sysDictionaryContent) {
        return sysDictionaryContentDao.insert(sysDictionaryContent);
    }

    public boolean deleteById(long id) {
        SysDictionaryContent sysDictionaryContent = new SysDictionaryContent();
        sysDictionaryContent.setId(id);
        return sysDictionaryContentDao.delete(sysDictionaryContent);
    }

    public boolean update(SysDictionaryContent sysDictionaryContent) {
        return sysDictionaryContentDao.update(sysDictionaryContent);
    }

    public SysDictionaryContent getmodel(SysDictionaryContent sysDictionaryContent) {
        return sysDictionaryContentDao.getmodel(sysDictionaryContent);
    }

    public List<SysDictionaryContent> getList(SysDictionaryContent sysDictionaryContent) {
        return sysDictionaryContentDao.getlist(sysDictionaryContent);
    }

    public PageUtil<SysDictionaryContent> getpagelist(SysDictionaryContent sysDictionaryContent, int currentPage, int pagesize) {
        try {
            Page<SysDictionaryContent> page = PageHelper.startPage(currentPage, pagesize);
            sysDictionaryContentDao.getlist(sysDictionaryContent);
            PageUtil data = new PageUtil(currentPage, pagesize, page.getPages(), page.getTotal(), page.getResult());
            return data;
        } catch (Exception e) {

        }
        return null;
    }
}
