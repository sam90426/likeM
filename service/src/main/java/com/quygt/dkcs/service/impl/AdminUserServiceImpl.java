package com.quygt.dkcs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.AdminUserDao;
import com.quygt.dkcs.model.AdminUser;
import com.quygt.dkcs.service.AdminUserService;
import com.quygt.dkcs.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("AdminUserService")
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserDao adminUserDao;

    public boolean add(AdminUser adminUser) {
        return adminUserDao.insert(adminUser);
    }

    public boolean deleteById(long id) {
        AdminUser adminUser = new AdminUser();
        adminUser.setId(id);
        return adminUserDao.delete(adminUser);
    }

    public boolean update(AdminUser adminUser) {
        return adminUserDao.update(adminUser);
    }

    public AdminUser getmodel(AdminUser adminUser) {
        return adminUserDao.getmodel(adminUser);
    }

    public List<AdminUser> getList(AdminUser adminUser) {
        return adminUserDao.getlist(adminUser);
    }

    public PageUtil<AdminUser> getpagelist(AdminUser adminUser, int currentPage, int pagesize) {
        try {
            Page<AdminUser> page = PageHelper.startPage(currentPage, pagesize);
            adminUserDao.getlist(adminUser);
            PageUtil data = new PageUtil(currentPage, pagesize, page.getPages(), page.getTotal(), page.getResult());
            return data;
        } catch (Exception e) {

        }
        return null;
    }
}
