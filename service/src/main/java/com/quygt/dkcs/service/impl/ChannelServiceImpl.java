package com.quygt.dkcs.service.impl;

import com.quygt.dkcs.dao.ChannelDao;
import com.quygt.dkcs.model.Channel;
import com.quygt.dkcs.service.ChannelService;
import com.quygt.dkcs.service.impl.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Project:dkcs
 * @PackageName:com.quygt.dkcs.service.impl
 * @Author:fujian
 * @CreationDate:2018年01月15日11:44
 */
@Service
public class ChannelServiceImpl extends BaseServiceImpl<ChannelDao, Channel> implements ChannelService {

    @Resource
    private ChannelDao channelDao;

    /**
     * 根据渠道商id查询统计个渠道用户数量
     * @param ids
     * @return
     */
    public List<Channel> selectAllByIds(Serializable[] ids){
        return channelDao.selectAllByIds(ids);
    }

    /**
     * 根据id集合删除渠道商
     * @param list
     * @return
     */
    public boolean deleteByIds(List<Long> list){
        return channelDao.deleteByIds(list);
    }

    /**
     * 根据渠道编码查找
     * @param code
     * @return
     */
    public Channel getByCode(String code){
        return channelDao.getByCode(code);
    }

    /**
     * 查询渠道列表id 和name 用作搜索
     * @param channel
     * @return
     */
    public List<Channel> getSearchList(Channel channel){
        return channelDao.getSearchList(channel);
    }
}
