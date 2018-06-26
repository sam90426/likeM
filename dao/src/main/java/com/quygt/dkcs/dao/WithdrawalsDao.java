package com.quygt.dkcs.dao;

import com.quygt.dkcs.dao.base.BaseDao;
import com.quygt.dkcs.model.Withdrawals;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface WithdrawalsDao extends BaseDao<Withdrawals> {

    /**
     * 查询列表
     * @param withdrawals 其他条件
     * @param start 起始位置 不是页码
     * @param pageSize 查询条数
     * @param minAmount 最低金额筛选
     * @return
     */
    List<Withdrawals> getNotice(@Param("w") Withdrawals withdrawals,@Param("start") Integer start,@Param("size") Integer pageSize,@Param("minAmount") BigDecimal minAmount);

}
