package com.quygt.dkcs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.InfoGGDicContentDao;
import com.quygt.dkcs.model.InfoGGDicContent;
import com.quygt.dkcs.service.InfoGGDicContentService;
import com.quygt.dkcs.utils.ConfigUtil;
import com.quygt.dkcs.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoGGDicContentService")
public class InfoGGDicContentServiceImpl implements InfoGGDicContentService {

    @Resource
    private InfoGGDicContentDao infoGGDicContentDao;

    public boolean add(InfoGGDicContent infoGGDicContent) {
        return infoGGDicContentDao.insert(infoGGDicContent);
    }

    public boolean deleteById(long id) {
        InfoGGDicContent infoGGDicContent = new InfoGGDicContent();
        infoGGDicContent.setId(id);
        return infoGGDicContentDao.delete(infoGGDicContent);
    }

    public boolean update(InfoGGDicContent infoGGDicContent) {
        return infoGGDicContentDao.update(infoGGDicContent);
    }

    public InfoGGDicContent getmodel(InfoGGDicContent infoGGDicContent) {
        return infoGGDicContentDao.getmodel(infoGGDicContent);
    }

    public List<InfoGGDicContent> getList(InfoGGDicContent infoGGDicContent) {
        return infoGGDicContentDao.getlist(infoGGDicContent);
    }

    public PageUtil<InfoGGDicContent> getpagelist(InfoGGDicContent infoGGDicContent, int currentPage, int pagesize) {
        try {
            Page<InfoGGDicContent> page = PageHelper.startPage(currentPage, pagesize);
            infoGGDicContentDao.getlist(infoGGDicContent);
            PageUtil data = new PageUtil(currentPage, pagesize, page.getPages(), page.getTotal(), page.getResult());
            return data;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 开关
     * @param type
     * @return
     */
    public Integer getState(Integer type){
        String path = ConfigUtil.getInstance().getString("onlineswitch");
        InfoGGDicContent infoGGDicContent = new InfoGGDicContent();
        infoGGDicContent.setDicPath(path);
        List<InfoGGDicContent> list = this.getList(infoGGDicContent);

        infoGGDicContent = new InfoGGDicContent();
        if(type==null){
            type=1;
        }
        for (InfoGGDicContent item : list) {
            if (type == 1) {
                if (item.getLinkUrl().equals("Android"))
                    infoGGDicContent = item;
            } else if (type == 2) {
                if (item.getLinkUrl().equals("IOS"))
                    infoGGDicContent = item;
            }
        }
        return infoGGDicContent.getState();
    }
}
