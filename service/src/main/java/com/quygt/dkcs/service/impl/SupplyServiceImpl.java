package com.quygt.dkcs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.quygt.dkcs.dao.SupplyDao;
import com.quygt.dkcs.dao.SupplyLogDao;
import com.quygt.dkcs.dao.UserDao;
import com.quygt.dkcs.model.InfoGGDicContent;
import com.quygt.dkcs.model.Supply;
import com.quygt.dkcs.model.SupplyLog;
import com.quygt.dkcs.model.User;
import com.quygt.dkcs.service.InfoGGDicContentService;
import com.quygt.dkcs.service.SupplyService;
import com.quygt.dkcs.service.UserService;
import com.quygt.dkcs.utils.ConfigUtil;
import com.quygt.dkcs.utils.NumChangeStart;
import com.quygt.dkcs.utils.PageUtil;
import com.quygt.dkcs.utils.ResponseMsg;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Service("SupplyService")
public class SupplyServiceImpl implements SupplyService {

    @Resource
    private SupplyDao supplyDao;

    @Resource
    private InfoGGDicContentService infoGGDicContentService;

    @Resource
    private SupplyLogDao supplyLogDao;

    @Resource
    private UserService userService;

    public Supply getSupplyById(long id) {
        return supplyDao.getSupplyById(id);
    }

    public boolean update(Supply supply) {
        return supplyDao.update(supply);
    }

    public PageUtil<Supply> getListByPage(int supplyType, int currPage, int pageSize) {
        Page<Supply> page = PageHelper.startPage(currPage, pageSize);
        supplyDao.getListByPage(supplyType);
        PageUtil<Supply> data = new PageUtil<Supply>(currPage, pageSize, page.getPages(), page.getTotal(), page.getResult());
        return data;
    }

    public Supply getmodel(Supply supply) {
        return supplyDao.getmodel(supply);
    }

    public boolean insert(Supply supply) {
        return supplyDao.insert(supply);
    }

    public PageUtil<Supply> getPageList(Supply supply, int currPage, int pageSize) {
        Page<Supply> page = PageHelper.startPage(currPage, pageSize);
        supplyDao.getPageList(supply);
        PageUtil<Supply> data = new PageUtil<Supply>(currPage, pageSize, page.getPages(), page.getTotal(), page.getResult());
        return data;
    }

    public boolean deleteById(long id) {
        Supply supply = new Supply();
        supply.setId(id);
        return supplyDao.delete(supply);
    }

    /**
     * 获取供应商列表
     *
     * @param supplyType
     * @param recommend
     * @param currPage
     * @param request
     * @return
     */
    public String getSupplyList(int supplyType, int recommend, int state, int currPage, int pageSize, HttpServletRequest request) {
        if (supplyType < 0 || supplyType > 3)
            return ResponseMsg.New(-1, "供应商类型不正确").asJson();
        if (currPage < 0)
            return ResponseMsg.New(-1, "当前页码错误").asJson();
        String typestr = request.getParameter("type");
        int type = 1;
        if (typestr != null) {
            type = Integer.parseInt(typestr);
        }
        String path = ConfigUtil.getInstance().getString("onlineswitch");
        InfoGGDicContent infoGGDicContent = new InfoGGDicContent();
        infoGGDicContent.setDicPath(path);
        List<InfoGGDicContent> list = infoGGDicContentService.getList(infoGGDicContent);

        infoGGDicContent = new InfoGGDicContent();
        for (InfoGGDicContent item : list) {
            if (type == 1) {
                if (item.getLinkUrl().equals("Android"))
                    infoGGDicContent = item;
            } else if (type == 2) {
                if (item.getLinkUrl().equals("IOS"))
                    infoGGDicContent = item;
            }
        }

        Supply supply = new Supply();
        if (supplyType > 0) {
            supply.setSupplyType(supplyType);
        }
        supply.setRecommend(recommend);
        if (infoGGDicContent.getState() == 1) {
            supply.setState(3);
        } else {
            supply.setState(1);
        }
        if (state > 0) {
            supply.setState(state);
        }
        PageUtil<Supply> page = this.getPageList(supply, currPage, pageSize);
        //PageUtil<Supply> pageData = supplyService.getListByPage(supplyType, currPage, pageSize);
        Map data = new HashMap<String, Object>();
        data.put("data", page);
        return ResponseMsg.New(1, "查询成功", data).asJson();
    }

    /**
     * 供应商点击数自增1
     *
     * @param id
     * @return
     */
    public boolean updateHits(Long id) {
        return supplyDao.updateHits(id);
    }

    /**
     * 供应商记录访问日志
     * @param supplyId
     * @param source
     * @param uid
     * @param specificSource 具体来源
     * @return
     */
    public String insertUpdate(long supplyId, int source, String uid,Integer specificSource) {
        if (supplyId <= 0)
            return ResponseMsg.New(-1, "供应商编号不正确").asJson();
        Supply supply = supplyDao.getSupplyById(supplyId);
        if (supply == null)
            return ResponseMsg.New(-1, "供应商编号不正确").asJson();
        if (supply.getState() == 1) {
            SupplyLog supplyLog = new SupplyLog();
            User user = (User) (RequestContextHolder.getRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_REQUEST));
            if (source == 1) {
                supplyLog.setUserName(user.getUserName());
            } else {
                supplyLog.setUserName(uid);
            }
            supplyLog.setSupplyId(supplyId);
            supplyLog = supplyLogDao.getmodel(supplyLog);
            //判断是否存在日志记录
            if (supplyLog == null) {
                supplyLog = new SupplyLog();
//                supplyLog.setUserId(uid);

                supplyLog.setUserName(source == 1 ? user.getUserName() : uid);
//                supplyLog.setRealName(user.getRealName());
                supplyLog.setSupplyId(supply.getId());
                supplyLog.setSupplyName(supply.getSupplyName());
                supplyLog.setSource(source);
                supplyLog.setCreateTime(new Date());
                supplyLog.setSpecificSource(specificSource);
                boolean result = supplyLogDao.insert(supplyLog);
                if (!result)
                    return ResponseMsg.New(0, "添加供应商访问记录失败").asJson();
                //修改供应商点击量
                supply.setHits(supply.getHits() + 1);
                supplyDao.update(supply);
            }
        }
        return ResponseMsg.New(1, "添加供应商访问记录成功").asJson();
    }

    /**
     * 查询供应商列表及其关联供应商标签
     *
     * @param map
     * @return
     */
    public PageUtil<Supply> selectListAll(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        Integer count = supplyDao.countSupply(map);
        if (pageSize == null) {
            pageSize = 10;
        }
        Integer pageStart = NumChangeStart.getStart(pageNum, pageSize, count);//查询起始位置
        map.put("pageSize", pageSize);
        map.put("pageStart", pageStart);
        List<Supply> supplies = supplyDao.selectListAll(map);
        pageNum = NumChangeStart.getNum(pageNum, pageSize, count);//重新获取准确的pageNum
        if (supplies != null && supplies.size() > 0) {
            for (int i = 0; i < supplies.size(); i++) {
                String raiders = supplies.get(i).getRaiders();
                if (!StringUtil.isEmpty(raiders)) {//攻略文本中 图片地址域名加上
                    supplies.get(i).setRaiders(raiders.replaceAll("/UploadFile", "http://fdcsapi1.fengyjf.com/UploadFile"));
                }
            }
        }
        return new PageUtil<Supply>(pageNum, pageSize, count, supplies);
    }

    /**
     * 查询供应商条数
     *
     * @param map
     * @return
     */
    public Integer countSupply(Map map) {
        return supplyDao.countSupply(map);
    }

    /**
     * 统计供应商点击量
     *
     * @param supply
     * @return
     */
    public Integer sumHits(Supply supply) {
        return supplyDao.sumHits(supply);
    }

    /**
     * 根据id查询供应商全部信息
     *
     * @param id
     * @return
     */
    public Supply selectById(Long id) {
        Supply supply = supplyDao.selectById(id);
        if (supply != null) {
            String raiders = supply.getRaiders();
            if (!StringUtil.isEmpty(raiders)) {//攻略文本中 图片地址域名加上
                supply.setRaiders(raiders.replaceAll("/UploadFile", "http://fdcsapi1.fengyjf.com/UploadFile"));
            }
        }
        return supply;
    }

    /**
     * 封装首页滚动广告假数据查询
     *
     * @return
     */
    public Map<String, Object> selectRollingMsg() {
        Supply supply = new Supply();
        supply.setState(1);//启用的供应商筛选
        Integer sumHits = supplyDao.sumHits(supply);//申请人数
        PageUtil<User> userPageUtil = userService.getPageList(new User(), 1, 10);
        List<User> users = userPageUtil.getData();//用新注册的前十位用户信息生成假数据
        List<Map> list = new ArrayList<Map>();
        for (int i = 0; i < users.size(); i++) {
            String str = new BigDecimal(users.get(i).getUserName()).add(new BigDecimal("123456789")).toString();
            String cardNo = str.substring(str.length() - 5, str.length() - 1);//尾号
            Integer time = Integer.parseInt(str.substring(str.length() - 8, str.length() - 6)) / 3;//用时time分钟
            if (time == 0) {//如果碰巧截取到的数字为0则默认为10
                time = 10;
            }
            Integer money = Integer.parseInt(str.substring(str.length() - 9, str.length() - 8)) * 1000;//借款额度
            if (money == 0 || money >= 9000) {
                money = 1000;//最小额度控制在1000
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cardNo", cardNo);
            map.put("time", time);
            map.put("money", money);
            list.add(map);
        }
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("list", list);
        json.put("sumHits", sumHits);
        return json;
    }

}
