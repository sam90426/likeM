package com.quygt.dkcs.service.impl;

import com.quygt.dkcs.dao.AppErrorLogDao;
import com.quygt.dkcs.model.AppErrorLog;
import com.quygt.dkcs.service.AppErrorLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("AppErrorLogService")
public class AppErrorLogImpl implements AppErrorLogService {
    @Resource
    private  AppErrorLogDao appErrorLogDao;
    /**
     * 添加
     * @param appErrorLog
     * @return
     */
    public boolean insert(AppErrorLog appErrorLog) {
        return appErrorLogDao.insert(appErrorLog);
    }
}
