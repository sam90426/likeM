package com.quygt.dkcs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.InfoNewsDao;
import com.quygt.dkcs.model.InfoNews;
import com.quygt.dkcs.service.InfoNewsService;
import com.quygt.dkcs.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoNewsService")
public class InfoNewsServiceImpl implements InfoNewsService {

    @Resource
    private InfoNewsDao infoNewsDao;

    public boolean add(InfoNews infoNews) {
        return infoNewsDao.insert(infoNews);
    }

    public boolean deleteById(long id) {
        InfoNews infoNews = new InfoNews();
        infoNews.setId(id);
        return infoNewsDao.delete(infoNews);
    }

    public boolean update(InfoNews infoNews) {
        return infoNewsDao.update(infoNews);
    }

    public InfoNews getmodel(InfoNews infoNews) {
        return infoNewsDao.getmodel(infoNews);
    }

    public List<InfoNews> getList(InfoNews infoNews) {
        return infoNewsDao.getlist(infoNews);
    }

    public PageUtil<InfoNews> getpagelist(InfoNews infoNews, int currentPage, int pagesize) {
        Page<InfoNews> page = PageHelper.startPage(currentPage, pagesize);
        infoNewsDao.getlist(infoNews);
        PageUtil<InfoNews> data = new PageUtil<InfoNews>(currentPage, pagesize, page.getPages(), page.getTotal(), page.getResult());
        return data;
    }
}
