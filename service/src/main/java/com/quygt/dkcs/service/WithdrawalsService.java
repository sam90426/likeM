package com.quygt.dkcs.service;

import com.quygt.dkcs.model.Withdrawals;
import com.quygt.dkcs.service.base.BaseService;

import java.math.BigDecimal;
import java.util.List;

public interface WithdrawalsService extends BaseService<Withdrawals> {

    /**
     * 申请提现
     * @return
     */
    String apply(Long userId);

    /**
     * 查询列表
     * @param withdrawals 其他条件
     * @param start 起始位置 不是页码
     * @param pageSize 查询条数
     * @param minAmount 最低金额筛选
     * @return
     */
    List<Withdrawals> getNotice(Withdrawals withdrawals,Integer start,Integer pageSize,BigDecimal minAmount);
}
