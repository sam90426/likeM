package com.quygt.dkcs.service;

import com.quygt.dkcs.model.SupplyLog;
import com.quygt.dkcs.model.vo.ChannelChartVO;
import com.quygt.dkcs.model.vo.SourceChartVO;
import com.quygt.dkcs.utils.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplyLogService {
    boolean insert(SupplyLog supplyLog);

    /**
     * 按条件查询分页
     */
    PageUtil<SupplyLog> getpagelist(SupplyLog supplyLog, int currentPage, int pagesize);

    /**
     * 获取实体数据
     */
    SupplyLog getmodel(SupplyLog supplyLog);

    /**
     * 根据条件获取全部数据
     *
     * @param supplyLog
     * @return
     */
    List<SupplyLog> getAllList(SupplyLog supplyLog);

    /**
     * 获取渠道量统计数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 统计vo集合
     */
    List<ChannelChartVO> selectChannelChartData(String startTime, String endTime);

    /**
     * 获取渠道来源统计数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 统计vo集合
     */
    List<SourceChartVO> selectSourceChartData(String startTime, String endTime, String dicPath);
}
