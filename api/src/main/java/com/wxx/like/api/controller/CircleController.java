package com.wxx.like.api.controller;

import com.wxx.like.api.common.ServletUtils;
import com.wxx.like.model.CircleInfo;
import com.wxx.like.model.CircleZan;
import com.wxx.like.model.Friends;
import com.wxx.like.model.UserInfo;
import com.wxx.like.service.CircleInfoService;
import com.wxx.like.service.CircleZanService;
import com.wxx.like.service.FriendsService;
import com.wxx.like.service.UserInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @Auther: wuxianxin
 * @Date: 2018/7/17 18:02
 * @Description:
 */
public class CircleController extends BaseController {

    @Resource
    private CircleInfoService circleInfoService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private FriendsService friendsService;
    @Resource
    private CircleZanService circleZanService;

    //region 好友动态列表

    /**
     * @param userId
     * @param pageIndex
     * @param response
     * @throws Exception
     */
    public void friendsCircle(@RequestParam(value = "userId", required = true) Long userId,
                              @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
                              HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();

        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 上传动态图片

    //endregion

    //region 发布动态

    //endregion

    //region 申请好友

    /**
     * @param userId
     * @param friendsId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/friendApply", method = RequestMethod.POST)
    public void friendApply(@RequestParam(value = "userId", required = true) Long userId,
                            @RequestParam(value = "friendsId", required = true) Long friendsId,
                            HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
        UserInfo friendInfo = userInfoService.findUserInfoByUserId(friendsId);
        if (friendInfo == null) {
            result.put("code", 400);
            result.put("msg", "该用户不存在");
            ServletUtils.writeToResponse(response, result);
            return;
        }
        Friends friends = new Friends();
        friends.setUserId(userInfo.getId());
        friends.setFriendUserId(friendInfo.getId());
        friends = friendsService.getmodel(friends);
        if (friends.getState() == 1) {
            result.put("code", 400);
            result.put("msg", "已申请,等待通过");
            ServletUtils.writeToResponse(response, result);
            return;
        } else if (friends.getState() == 2) {
            result.put("code", 400);
            result.put("msg", "已是好友");
            ServletUtils.writeToResponse(response, result);
            return;
        } else {
            friends = new Friends();
            friends.setUserId(userInfo.getId());
            friends.setUserName(userInfo.getUserName());
            friends.setFriendUserId(friendInfo.getId());
            friends.setFriendUserName(friendInfo.getUserName());
            friends.setFriendSex(friendInfo.getSex());
            friends.setState(1);
            friends.setCreateTime(new Date());
            if (friendsService.insert(friends)) {
                result.put("code", 200);
                result.put("msg", "申请成功,等待审核");
            } else {
                result.put("code", 400);
                result.put("msg", "申请失败,请重试");
            }
        }
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 动态详情

    /**
     * @param circleId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/circleDetail", method = RequestMethod.POST)
    public void circleDetail(@RequestParam(value = "circleId", required = true) Long circleId,
                             HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        CircleInfo circleInfo = circleInfoService.getById(circleId);
        if (circleInfo == null) {
            result.put("code", 400);
            result.put("msg", "该动态不存在");
        } else {
            result.put("code", 200);
            result.put("msg", "查询成功");
            result.put("data", circleInfo);
        }
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 点赞列表
    public void zanList(@RequestParam(value = "userId", required = true) Long userId,
                        @RequestParam(value = "circleId", required = true) Long circleId,
                        @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
                        HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();

        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 点赞

    /**
     * @param userId
     * @param circleId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/circleZan", method = RequestMethod.POST)
    public void circleZan(@RequestParam(value = "userId", required = true) Long userId,
                          @RequestParam(value = "circleId", required = true) Long circleId,
                          HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
        CircleInfo circleInfo = circleInfoService.getById(circleId);
        if (circleInfo == null) {
            result.put("code", 400);
            result.put("msg", "该动态不存在");
        } else {
            CircleZan circleZan = new CircleZan();
            circleZan.setCircleId(circleId);
            circleZan.setUserId(userId);
            circleZan = circleZanService.getmodel(circleZan);
            if (circleZan != null) {
                result.put("code", 400);
                result.put("msg", "已经赞过了");
            } else {
                circleZan = new CircleZan();
                circleZan.setUserId(userInfo.getId());
                circleZan.setUserName(userInfo.getUserName());
                circleZan.setSex(userInfo.getSex());
                circleZan.setLogo(userInfo.getLogo());
                circleZan.setCircleId(circleId);
                circleZan.setCreateTime(new Date());
                if (circleZanService.insert(circleZan)) {
                    result.put("code", 200);
                    result.put("msg", "点赞成功");
                } else {
                    result.put("code", 400);
                    result.put("msg", "点赞失败，请重试");
                }
            }
        }
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 取消点赞

    //endregion
}
