package com.wxx.like.service;


import com.github.pagehelper.Page;
import com.wxx.like.model.CircleInfo;
import com.wxx.like.service.base.BaseService;
import com.wxx.like.utils.PageUtil;

import java.util.Map;

/**
 * 动态表Service
 */
public interface CircleInfoService extends BaseService<CircleInfo> {

    Page<CircleInfo> getFriendsPageList(Long userId, Integer currPage, Integer pageSize);


}
