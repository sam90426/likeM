package com.wxx.like.service;


import com.wxx.like.model.CircleInfo;
import com.wxx.like.service.base.BaseService;
import com.wxx.like.utils.PageUtil;

/**
 * 动态表Service
 */
public interface CircleInfoService extends BaseService<CircleInfo> {

    PageUtil<CircleInfo> getFriendsPageList(CircleInfo circleInfo, Integer currPage, Integer pageSize);
}
