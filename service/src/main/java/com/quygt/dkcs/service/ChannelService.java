package com.quygt.dkcs.service;

import com.quygt.dkcs.model.Channel;
import com.quygt.dkcs.service.base.BaseService;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @Project:dkcs
 * @PackageName:com.quygt.dkcs.service
 * @Author:fujian
 * @CreationDate:2018年01月15日11:43
 */
public interface ChannelService extends BaseService<Channel>{

    /**
     * 根据渠道商id查询统计个渠道用户数量
     * @param ids
     * @return
     */
    List<Channel> selectAllByIds(Serializable[] ids);

    /**
     * 根据id集合删除渠道商
     * @param list
     * @return
     */
    boolean deleteByIds(List<Long> list);

    /**
     * 根据渠道编码查找
     * @param code
     * @return
     */
    Channel getByCode(String code);

    /**
     * 查询渠道列表id 和name 用作搜索
     * @param channel
     * @return
     */
    List<Channel> getSearchList(Channel channel);
}
