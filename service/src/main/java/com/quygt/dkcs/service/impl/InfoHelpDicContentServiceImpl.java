package com.quygt.dkcs.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.InfoHelpDicContentDao;
import com.quygt.dkcs.model.InfoHelpDicContent;
import com.quygt.dkcs.service.InfoHelpDicContentService;
import com.quygt.dkcs.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoHelpDicContentService")
public class InfoHelpDicContentServiceImpl implements InfoHelpDicContentService {

    @Resource
    private InfoHelpDicContentDao infoHelpDicContentDao;

    public boolean add(InfoHelpDicContent infoHelpDicContent) {
        return infoHelpDicContentDao.insert(infoHelpDicContent);
    }

    public boolean deleteById(long id) {
        InfoHelpDicContent infoHelpDicContent = new InfoHelpDicContent();
        infoHelpDicContent.setId(id);
        return infoHelpDicContentDao.delete(infoHelpDicContent);
    }

    public boolean update(InfoHelpDicContent infoHelpDicContent) {
        return infoHelpDicContentDao.update(infoHelpDicContent);
    }

    public InfoHelpDicContent getmodel(InfoHelpDicContent infoHelpDicContent) {
        return infoHelpDicContentDao.getmodel(infoHelpDicContent);
    }

    public List<InfoHelpDicContent> getList(InfoHelpDicContent infoHelpDicContent) {
        return infoHelpDicContentDao.getlist(infoHelpDicContent);
    }

    public PageUtil<InfoHelpDicContent> getpagelist(InfoHelpDicContent infoHelpDicContent, int currentPage, int pagesize) {
        try {
            Page<InfoHelpDicContent> page = PageHelper.startPage(currentPage, pagesize);
            infoHelpDicContentDao.getlist(infoHelpDicContent);
            PageUtil data = new PageUtil(currentPage, pagesize, page.getPages(), page.getTotal(), page.getResult());
            return data;
        } catch (Exception e) {

        }
        return null;
    }
}
