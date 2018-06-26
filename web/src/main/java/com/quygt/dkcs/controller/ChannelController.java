package com.quygt.dkcs.controller;

import com.quygt.dkcs.model.Channel;
import com.quygt.dkcs.service.ChannelService;
import com.quygt.dkcs.utils.PageUtil;
import com.quygt.dkcs.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Project:dkcs
 * @PackageName:com.quygt.dkcs.controller
 * @Author:fujian
 * @CreationDate:2018年01月15日13:42
 */
@Controller
@RequestMapping(value = "/channel", produces = "text/plain;charset=UTF-8")
public class ChannelController {

    @Resource
    private ChannelService channelService;

    @RequestMapping(value = "/channelList")
    public String channelList() {
        return "channel/channelList";
    }

    @RequestMapping(value = "/channelEdit")
    public String channelEdit() {
        return "channel/channelEdit";
    }

    /**
     * 添加
     *
     * @param channel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(Channel channel) throws Exception {
        Channel search=new Channel();
        search.setCode(channel.getCode());
        Integer count=channelService.selectCount(search);
        if(count>0){
            return ResponseMsg.New(0, "该渠道代码已被使用").asJson();
        }
        channel.setCreateTime(new Date());
        boolean flag = channelService.insert(channel);
        if (flag) {//成功
            return ResponseMsg.New(1, "保存成功", channel).asJson();
        }
        return ResponseMsg.New(0, "保存失败").asJson();
    }

    /**
     * 根据id修改
     *
     * @param channel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    @ResponseBody
    public String updateById(Channel channel) throws Exception {
        if (channel == null || channel.getId() == null || channel.getId().equals(0l)) {
            return ResponseMsg.New(0, "请选择需要更改的渠道").asJson();
        }
        Channel oldChannel=channelService.getById(channel.getId());
        if(oldChannel==null){
            return ResponseMsg.New(0, "未找到该渠道商").asJson();
        }

        if(!oldChannel.getCode().equals(channel.getCode())){//渠道代码被更改过的情况下
            Channel search=new Channel();
            search.setCode(channel.getCode());
            Integer count=channelService.selectCount(search);
            if(count>0){
                return ResponseMsg.New(0, "该渠道代码已被使用").asJson();
            }
        }
        boolean flag = channelService.updateById(channel);
        if (flag) {//成功
            return ResponseMsg.New(1, "修改成功", channel).asJson();
        }
        return ResponseMsg.New(0, "修改失败").asJson();
    }

    @RequestMapping(value = "/updateStateByIds")
    @ResponseBody
    public String updateStateByIds(Integer state,String ids) throws Exception {
        if(ids==null){
            return ResponseMsg.New(0, "请至少选择一项操作").asJson();
        }
        String [] idsArray=ids.split(",");
        if(idsArray.length==0){
            return ResponseMsg.New(0, "请至少选择一项操作").asJson();
        }

        Channel channel=new Channel();
        channel.setState(state);
        boolean flag= channelService.updateByIds(channel,idsArray);
        if (flag) {//成功
            return ResponseMsg.New(1, "修改成功").asJson();
        }
        return ResponseMsg.New(0, "修改失败").asJson();
    }

    /**
     * 根据id数组  批量删除
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteByIds")
    @ResponseBody
    public String deleteByIds(String ids) throws Exception {
        if(ids==null){
            return ResponseMsg.New(0, "请至少选择一项操作").asJson();
        }
        String [] idsArray=ids.split(",");
        if(idsArray.length==0){
            return ResponseMsg.New(0, "请至少选择一项操作").asJson();
        }
        List<Channel> channelList=channelService.selectAllByIds(idsArray);
        List<Long> idList=new ArrayList<Long>();
        for (int i = 0; i < idsArray.length; i++) {
            idList.add(Long.parseLong(idsArray[i]));
        }
        StringBuilder names=new StringBuilder("");//不能删除的渠道名称集合
        if(channelList!=null&&channelList.size()>0){
            for (int i = 0; i < channelList.size(); i++) {
                if(channelList.get(i).getUserCount()>0){
                    names.append("【"+channelList.get(i).getName()+"】");
                    idList.remove(channelList.get(i).getId());
                }
            }
        }
        if(idList.size()==0){
            return ResponseMsg.New(0, "选中的渠道均存在绑定的用户<br/>删除失败").asJson();
        }
        boolean flag= channelService.deleteByIds(idList);
        if (flag) {//成功
            if(names.toString().equals("")){
                return ResponseMsg.New(1, "删除成功").asJson();
            }else{
                return ResponseMsg.New(4, "部分删除成功<br/>"+names.toString()+"<br/>存在绑定的用户不能删除").asJson();
            }
        }
        return ResponseMsg.New(0, "删除失败").asJson();
    }

    /**
     * 根据id删除 不可恢复
     *
     * @param channel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteById")
    @ResponseBody
    public String deleteById(Channel channel) throws Exception {
        if (channel == null || channel.getId() == null || channel.getId().equals(0l)) {
            return ResponseMsg.New(0, "请选择需要删除的渠道").asJson();
        }
        boolean flag = channelService.delete(channel);
        if (flag) {//成功
            return ResponseMsg.New(1, "删除成功").asJson();
        }
        return ResponseMsg.New(0, "删除失败").asJson();
    }

    /**
     * 分页查询渠道商列表
     *
     * @param channel
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectList")
    @ResponseBody
    public String selectList(Channel channel, Integer pageIndex, Integer pageSize) throws Exception {
        PageUtil<Channel> pageList = channelService.getPageList(channel, pageIndex, pageSize);
        return ResponseMsg.dataPage(pageList);
    }

    @RequestMapping(value = "/selectById")
    @ResponseBody
    public String selectById(Long id) throws Exception {
        Channel channel = channelService.getById(id);
        return ResponseMsg.New(1, "查询成功",channel).asJson();
    }
}
