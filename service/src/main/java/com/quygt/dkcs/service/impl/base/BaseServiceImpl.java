package com.quygt.dkcs.service.impl.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.base.BaseDao;
import com.quygt.dkcs.service.base.BaseService;
import com.quygt.dkcs.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<M extends BaseDao<T>, T> implements BaseService<T> {

    @Autowired
    protected M baseMapper;

    /**
     * 条数
     * @param record
     * @return
     */
    public Integer selectCount(T record){
        return baseMapper.selectCount(record);
    }

    public boolean insert(T record) {
        return baseMapper.insert(record);
    }

    public T getById(Serializable id) {
        return baseMapper.getById(id);
    }

    public boolean updateById(T record) {
        return baseMapper.update(record);
    }

    public boolean updateByIds(T record,Serializable [] ids) {
        return baseMapper.updateByIds(record,ids);
    }

    /**
     * 根据实体删除

     *
     * @param e
     * @return
     */
    public boolean delete(T e) {
        return baseMapper.delete(e);
    }

    /**
     * 查询列表 分页
     *
     * @param e
     * @param currPage
     * @param pageSize
     * @return
     */
    public PageUtil<T> getPageList(T e, Integer currPage, Integer pageSize) {
        currPage++;
        Page<T> page = PageHelper.startPage(currPage, pageSize);
        baseMapper.getlist(e);
        return new PageUtil<T>(currPage, pageSize, page.getPages(), page.getTotal(), page.getResult());
    }

    /**
     * 查询列表 未分页
     *
     * @param e
     * @return
     */
    public List<T> getlist(T e) {
        return baseMapper.getlist(e);
    }

    public T getmodel(T e){
        return baseMapper.getmodel(e);
    }

    public boolean update(T e){
        return baseMapper.update(e);
    }
}
