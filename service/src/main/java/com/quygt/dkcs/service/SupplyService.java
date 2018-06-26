package com.quygt.dkcs.service;

import com.quygt.dkcs.model.Supply;
import com.quygt.dkcs.utils.PageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface SupplyService {
    Supply getSupplyById(long id);
    boolean update(Supply supply);
    PageUtil<Supply> getListByPage(int supplyType, int currPage, int pageSize);
    Supply getmodel(Supply supply);
    boolean insert(Supply supply);
    PageUtil<Supply> getPageList(Supply supply, int currPage, int pageSize);
    boolean deleteById(long id);

    /**
     * 获取供应商列表
     * @param supplyType
     * @param recommend
     * @param currPage
     * @param request
     * @return
     */
    String getSupplyList(int supplyType, int recommend,int state,  int currPage,int pageSize, HttpServletRequest request);

    /**
     * 供应商点击数自增1
     * @param id
     * @return
     */
    boolean updateHits(Long id);

    /**
     * 供应商记录访问日志
     * @param supplyId
     * @param source
     * @param uid
     * @param specificSource 具体来源
     * @return
     */
    String insertUpdate(long supplyId,int source,String uid,Integer specificSource);

    /**
     * 查询供应商列表及其关联供应商标签
     * @param map
     * @return
     */
    PageUtil<Supply> selectListAll(Map<String,Object> map,Integer pageNum,Integer pageSize);

    /**
     * 查询供应商条数
     * @param map
     * @return
     */
    Integer countSupply(Map map);

    /**
     * 统计供应商点击量
     * @param supply
     * @return
     */
    Integer sumHits(Supply supply);

    /**
     * 根据id查询供应商全部信息
     * @param id
     * @return
     */
    Supply selectById(Long id);

    /**
     * 封装首页滚动广告假数据查询
     * @return
     */
    Map<String,Object> selectRollingMsg();
}
