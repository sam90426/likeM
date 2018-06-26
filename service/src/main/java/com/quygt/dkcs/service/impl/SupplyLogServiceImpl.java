package com.quygt.dkcs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.SupplyLogDao;
import com.quygt.dkcs.model.SupplyLog;
import com.quygt.dkcs.model.vo.ChannelChartVO;
import com.quygt.dkcs.model.vo.SourceChartVO;
import com.quygt.dkcs.service.SupplyLogService;
import com.quygt.dkcs.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("SupplyLogService")
public class SupplyLogServiceImpl implements SupplyLogService {

    @Resource
    private SupplyLogDao supplyLogDao;

    public boolean insert(SupplyLog supplyLog) {
        return supplyLogDao.insert(supplyLog);
    }

    public PageUtil<SupplyLog> getpagelist(SupplyLog supplyLog, int currentPage, int pagesize) {
        try {
            Page<SupplyLog> page = PageHelper.startPage(currentPage, pagesize);
            supplyLogDao.getlist(supplyLog);
            PageUtil data = new PageUtil(currentPage, pagesize, page.getPages(), page.getTotal(), page.getResult());
            return data;
        } catch (Exception e) {

        }
        return null;
    }

    public SupplyLog getmodel(SupplyLog supplyLog) {
        return supplyLogDao.getmodel(supplyLog);
    }

    public List<SupplyLog> getAllList(SupplyLog supplyLog) {
        return supplyLogDao.getlist(supplyLog);
    }

    public List<ChannelChartVO> selectChannelChartData(String startTime, String endTime) {
        return supplyLogDao.selectChannelChartData(startTime, endTime);
    }

    public List<SourceChartVO> selectSourceChartData(String startTime, String endTime, String dicPath) {
        return supplyLogDao.selectSourceChartData(startTime, endTime, dicPath);
    }
}
