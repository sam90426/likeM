package com.wxx.like.api.controller;

import com.github.pagehelper.Page;
import com.wxx.like.api.common.ServletUtils;
import com.wxx.like.model.*;
import com.wxx.like.service.*;
import com.wxx.like.utils.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wuxianxin
 * @Date: 2018/7/3 15:07
 * @Description:
 */
@Controller
@RequestMapping(value = "/index", produces = "text/plain;charset=UTF-8")
public class IndexController extends BaseController {
    @Resource
    private LikeArticleService likeArticleService;
    @Resource
    private CircleInfoService circleInfoService;
    @Resource
    private FriendsService friendsService;
    @Resource
    private CircleZanService circleZanService;
    @Resource
    private CircleCommentService circleCommentService;


    //region 首页

    /**
     * 首页
     *
     * @param userId
     * @param label
     * @param lastcircleId
     * @param pageindex
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/findIndex",method = RequestMethod.POST)
    @ResponseBody
    public void findIndex(@RequestParam(value = "userId", required = true) Long userId,
                          @RequestParam(value = "label", required = true) String label,
                          @RequestParam(value = "pageindex", required = true) Integer pageindex,
                          HttpServletResponse response)  {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        //首页轮播
        List<LikeArticle> indexBanner = likeArticleService.listSelective(new HashMap<String,Object>());
        //首页动态
        Map<String,Object> map=new HashMap<>();
        map.put("isOut",1);
        if (!label.isEmpty()) {
            map.put("label",label);
        }
        Page<CircleInfo> page = circleInfoService.getPageList(map, pageindex, 5);
        if (page.getResult().size() > 0) {
            for (CircleInfo item : page.getResult()) {
                Friends friends = new Friends();
                friends.setUserId(userId);
                friends.setFriendUserId(item.getUserId());
                friends.setState(2);
                int friend = friendsService.selectcount(friends);
                CircleZan circleZan = new CircleZan();
                circleZan.setUserId(userId);
                circleZan.setCircleId(item.getId());
                int zancount = circleZanService.selectcount(circleZan);
                //Sex=2时显示添加好友按钮
                if (friend > 0) {
                    item.setSex(1);
                } else if (item.getUserId().equals(userId)) {
                    item.setSex(1);
                } else {
                    item.setSex(2);
                }
                if (zancount > 0) {
                    item.setZanCount(1);
                } else {
                    item.setZanCount(0);
                }
                map=new HashMap<>();
                map.put("circleId",item.getId());
                item.setCommentList(circleCommentService.listSelective(map));
                circleZan = new CircleZan();
                item.setZanList(circleZanService.listSelective(map));
            }
        }
        data.put("indexBanner", indexBanner);
        data.put("circlelist", page.getResult());
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", data);
        ServletUtils.writeToResponse(response, result);
    }
    //endregion
}
