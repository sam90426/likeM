package com.quygt.dkcs.dao;

import com.quygt.dkcs.dao.base.BaseDao;
import com.quygt.dkcs.model.Supply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SupplyDao extends BaseDao<Supply> {
    Supply getSupplyById(@Param("id") long id);
    List<Supply> getListByPage(@Param("supplyType") int supplyType);
    List<Supply> getPageList(Supply supply);

    /**
     * 供应商点击数自增1
     * @param id
     * @return
     */
    boolean updateHits(Long id);

    /**
     * 查询供应商列表及其关联供应商标签
     * @param map
     * @return
     */
    List<Supply> selectListAll(Map map);

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
}
