package com.wxx.like.api.controller;

import com.wxx.like.api.common.ServletUtils;
import com.wxx.like.model.*;
import com.wxx.like.service.*;
import org.springframework.stereotype.Controller;
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
@Controller
@RequestMapping(value = "/circle", produces = "text/plain;charset=UTF-8")
public class CircleController extends BaseController {

    @Resource
    private CircleInfoService circleInfoService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private FriendsService friendsService;
    @Resource
    private CircleZanService circleZanService;
    @Resource
    private CircleCommentService circleCommentService;

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
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userInfo.getId());
        map.put("friendUserId",friendInfo.getId());
        Friends friends = friendsService.findSelective(map);
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
            if (friendsService.save(friends)) {
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
        CircleInfo circleInfo = circleInfoService.findByPrimary(circleId);
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
        CircleInfo circleInfo = circleInfoService.findByPrimary(circleId);
        if (circleInfo == null) {
            result.put("code", 400);
            result.put("msg", "该动态不存在");
        } else {
            Map<String,Object> map=new HashMap<>();
            map.put("circleId",circleId);
            map.put("userId",userId);
            CircleZan circleZan = circleZanService.findSelective(map);
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
                if (circleZanService.save(circleZan)) {
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

    /**
     * @param userId
     * @param circleId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/cancelCircleZan", method = RequestMethod.POST)
    public void cancelCircleZan(@RequestParam(value = "userId", required = true) Long userId,
                                @RequestParam(value = "circleId", required = true) Long circleId,
                                HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Map<String,Object> map=new HashMap<>();
        map.put("circleId",circleId);
        map.put("userId",userId);
        CircleZan circleZan = circleZanService.findSelective(map);
        if (circleZan == null) {
            result.put("code", 400);
            result.put("msg", "取消点赞失败");
        } else {
            if (circleZanService.delete(circleZan)) {
                result.put("code", 200);
                result.put("msg", "取消点赞成功");
            } else {
                result.put("code", 400);
                result.put("msg", "取消点赞失败");
            }
        }
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 评论列表

    //endregion

    //region 评论

    /**
     * @param userId
     * @param circleId
     * @param replyUserId
     * @param comment
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/circleComment", method = RequestMethod.POST)
    public void circleComment(@RequestParam(value = "userId", required = true) Long userId,
                              @RequestParam(value = "circleId", required = true) Long circleId,
                              @RequestParam(value = "replyUserId", required = true) Long replyUserId,
                              @RequestParam(value = "comment", required = true) String comment,
                              HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (comment.isEmpty()) {
            result.put("code", 400);
            result.put("msg", "请输入评论内容");
            ServletUtils.writeToResponse(response, result);
            return;
        }
        CircleInfo circleInfo = circleInfoService.findByPrimary(circleId);
        if (circleInfo == null) {
            result.put("code", 400);
            result.put("msg", "该动态不存在");
            ServletUtils.writeToResponse(response, result);
            return;
        }
        UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
        CircleComment circleComment = new CircleComment();
        circleComment.setUserId(userInfo.getId());
        circleComment.setUserName(userInfo.getUserName());
        circleComment.setSex(userInfo.getSex());
        circleComment.setLogo(userInfo.getLogo());
        circleComment.setCircleId(circleInfo.getId());
        if (replyUserId > 0) {
            UserInfo commentUser = userInfoService.findUserInfoByUserId(replyUserId);
            circleComment.setReplyUserId(commentUser.getId());
            circleComment.setReplyUserName(commentUser.getUserName());
        }
        circleComment.setComment(comment);
        circleComment.setCreateTime(new Date());
        if (circleCommentService.save(circleComment)) {
            result.put("code", 200);
            result.put("msg", "评论成功");
        } else {
            result.put("code", 400);
            result.put("msg", "评论失败，请重试");
        }

        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 评论删除

    /**
     * @param userId
     * @param commentId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/delCircleComment", method = RequestMethod.POST)
    public void delCircleComment(@RequestParam(value = "userId", required = true) Long userId,
                                 @RequestParam(value = "commentId", required = true) Long commentId,
                                 HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("commentId",commentId);
        CircleComment circleComment = circleCommentService.findSelective(map);
        if (circleComment == null) {
            result.put("code", 400);
            result.put("msg", "评论不存在");
        } else {
            if (circleCommentService.delete(circleComment)) {
                result.put("code", 200);
                result.put("msg", "删除成功");
            } else {
                result.put("code", 400);
                result.put("msg", "删除失败");
            }
        }
        ServletUtils.writeToResponse(response, result);
    }
    //endregion
}
