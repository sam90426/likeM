package com.quygt.dkcs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quygt.dkcs.dao.AdminUserLoginLogDao;
import com.quygt.dkcs.model.AdminUserLoginLog;
import com.quygt.dkcs.service.AdminUserLoginLogService;
import com.quygt.dkcs.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("AdminUserLoginLogService")
public class AdminUserLoginLogServiceImpl implements AdminUserLoginLogService {

    @Resource
    private AdminUserLoginLogDao adminUserloginlogDao;

    public boolean add(AdminUserLoginLog adminUserLoginLog) {
        return adminUserloginlogDao.insert(adminUserLoginLog);
    }

    public boolean deleteById(long id) {
        AdminUserLoginLog adminUserLoginLog=new AdminUserLoginLog();
        adminUserLoginLog.setId(id);
        return adminUserloginlogDao.delete(adminUserLoginLog);
    }

    public boolean update(AdminUserLoginLog adminUserLoginLog) {
        return adminUserloginlogDao.update(adminUserLoginLog);
    }

    public AdminUserLoginLog getmodel(AdminUserLoginLog adminUserLoginLog) {
        return adminUserloginlogDao.getmodel(adminUserLoginLog);
    }

    public List<AdminUserLoginLog> getList(AdminUserLoginLog adminUserLoginLog) {
        return adminUserloginlogDao.getlist(adminUserLoginLog);
    }

    public PageUtil<AdminUserLoginLog> getpagelist(AdminUserLoginLog adminUserLoginLog, int currentPage, int pagesize) {
        try {
            Page<AdminUserLoginLog> page = PageHelper.startPage(currentPage, pagesize);
            adminUserloginlogDao.getlist(adminUserLoginLog);
            PageUtil data = new PageUtil(currentPage, pagesize, page.getPages(), page.getTotal(), page.getResult());
            return data;
        } catch (Exception e) {

        }
        return null;
    }
}
