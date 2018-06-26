package com.quygt.dkcs.dao;

import com.quygt.dkcs.dao.base.BaseDao;
import com.quygt.dkcs.model.SupplyLog;
import com.quygt.dkcs.model.vo.ChannelChartVO;
import com.quygt.dkcs.model.vo.SourceChartVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyLogDao extends BaseDao<SupplyLog> {

    List<ChannelChartVO> selectChannelChartData(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 获取渠道来源统计数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 统计vo集合
     */
    List<SourceChartVO> selectSourceChartData(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("dicPath") String dicPath);
}
