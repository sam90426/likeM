package com.quygt.dkcs.service;

import com.quygt.dkcs.model.AdminUserLoginLog;
import com.quygt.dkcs.utils.PageUtil;

import java.util.List;

public interface AdminUserLoginLogService {
    /**
     * 添加
     */
    boolean add(AdminUserLoginLog adminUserLoginLog);

    /**
     * 根据ID删除
     */
    boolean deleteById(long id);

    /**
     * 修改信息
     */
    boolean update(AdminUserLoginLog adminUserLoginLog);

    /**
     *按条件查找实体
     */
    AdminUserLoginLog getmodel(AdminUserLoginLog adminUserLoginLog);

    /**
     * 按条件查找所有
     */
    List<AdminUserLoginLog> getList(AdminUserLoginLog adminUserLoginLog);

    /**
     * 按条件查询分页
     */
    PageUtil<AdminUserLoginLog> getpagelist(AdminUserLoginLog adminUserLoginLog, int currentPage, int pagesize);
}
