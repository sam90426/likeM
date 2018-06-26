package com.quygt.dkcs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.InfoSuggestDao;
import com.quygt.dkcs.model.InfoSuggest;
import com.quygt.dkcs.service.InfoSuggestService;
import com.quygt.dkcs.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoSuggestService")
public class InfoSuggestServiceImpl implements InfoSuggestService {

    @Resource
    private InfoSuggestDao infoSuggestDao;

    public boolean add(InfoSuggest infoSuggest) {
        return infoSuggestDao.insert(infoSuggest);
    }

    public boolean deleteById(long id) {
        InfoSuggest infoSuggest = new InfoSuggest();
        infoSuggest.setId(id);
        return infoSuggestDao.delete(infoSuggest);
    }

    public boolean update(InfoSuggest infoSuggest) {
        return infoSuggestDao.update(infoSuggest);
    }

    public InfoSuggest getmodel(InfoSuggest infoSuggest) {
        return infoSuggestDao.getmodel(infoSuggest);
    }

    public List<InfoSuggest> getList(InfoSuggest infoSuggest) {
        return infoSuggestDao.getlist(infoSuggest);
    }

    public PageUtil<InfoSuggest> getpagelist(InfoSuggest infoSuggest, int currentPage, int pagesize) {
        try {
            Page<InfoSuggest> page = PageHelper.startPage(currentPage, pagesize);
            infoSuggestDao.getlist(infoSuggest);
            PageUtil<InfoSuggest> data = new PageUtil<InfoSuggest>(currentPage, pagesize, page.getPages(), page.getTotal(), page.getResult());
            return data;
        } catch (Exception e) {

        }
        return null;
    }
}
