package com.wxx.like.dao;

import com.wxx.like.dao.base.BaseMapper;
import com.wxx.like.model.CircleInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 动态表Dao
 */
@Repository
public interface CircleInfoMapper extends BaseMapper<CircleInfo> {

    List<CircleInfo> getFriendsPageList(Long userId);
}
