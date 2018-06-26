package com.quygt.dkcs.service;

import com.quygt.dkcs.model.AppErrorLog;

public interface AppErrorLogService {
    /**
     * 添加
     * @param appErrorLog
     * @return
     */
    boolean insert(AppErrorLog appErrorLog);
}
