package com.quygt.dkcs.service;

import com.quygt.dkcs.model.AdminUser;
import com.quygt.dkcs.utils.PageUtil;

import java.util.List;

public interface AdminUserService {
    /**
     * 添加
     */
    boolean add(AdminUser adminUser);

    /**
     * 根据ID删除
     */
    boolean deleteById(long id);

    /**
     * 修改信息
     */
    boolean update(AdminUser adminUser);

    /**
     * 按条件查找实体
     */
    AdminUser getmodel(AdminUser adminUser);

    /**
     * 按条件查找所有
     */
    List<AdminUser> getList(AdminUser adminUser);

    /**
     * 按条件查询分页
     */
    PageUtil<AdminUser> getpagelist(AdminUser adminUser, int currentPage, int pagesize);
}
