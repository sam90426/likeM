package com.wxx.like.api.controller;

import cn.jpush.api.push.model.PushPayload;
import com.github.pagehelper.Page;
import com.wxx.like.model.Friends;
import com.wxx.like.model.UserInfo;
import com.wxx.like.service.FriendsService;
import com.wxx.like.service.UserInfoService;
import com.wxx.like.api.common.ServletUtils;
import com.wxx.like.utils.PageUtil;
import com.wxx.like.utils.RdPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: wuxianxin
 * @Date: 2018/7/10 16:27
 * @Description:
 */
@Controller
@RequestMapping(value = "/user", produces = "text/plain;charset=UTF-8")
public class UserController extends BaseController {

    @Resource
    private UserInfoService userInfoService;
    @Resource
    private FriendsService friendsService;

    //region 用户信息修改

    //region 更新个性签名

    /**
     * @param userId
     * @param content
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/updateSign", method = RequestMethod.POST)
    @ResponseBody
    public void updateSign(@RequestParam(value = "userId", required = true) Long userId,
                           @RequestParam(value = "content", required = true) String content,
                           HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (content.isEmpty()) {
            result.put("code", 400);
            result.put("msg", "请输入个性签名内容");
        } else {
            UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
            if (userInfo == null) {
                result.put("code", 400);
                result.put("msg", "用户不存在");
            } else {
                userInfo.setSign(content);
                userInfoService.update(userInfo);
                result.put("code", 200);
                result.put("msg", "保存成功");
            }
        }
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 更新昵称

    /**
     * @param userId
     * @param nickName
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/updateNickName", method = RequestMethod.POST)
    @ResponseBody
    public void updateNickName(@RequestParam(value = "userId", required = true) Long userId,
                               @RequestParam(value = "nickName", required = true) String nickName,
                               HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (nickName.isEmpty()) {
            result.put("code", 400);
            result.put("msg", "请输入用户名称");
        } else {
            UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
            if (userInfo == null) {
                result.put("code", 400);
                result.put("msg", "用户不存在");
            } else {
                userInfo.setUserName(nickName);
                userInfoService.update(userInfo);
                result.put("code", 200);
                result.put("msg", "保存成功");
            }
        }

        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 更换头像

    //endregion

    //region 修改密码

    /**
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public void updatePwd(@RequestParam(value = "userId", required = true) Long userId,
                          @RequestParam(value = "oldPwd", required = true) String oldPwd,
                          @RequestParam(value = "newPwd", required = true) String newPwd,
                          HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
        if (userInfo == null) {
            result.put("code", 400);
            result.put("msg", "用户不存在");
        } else {
            if (userInfo.getPassword().equals(oldPwd)) {
                userInfo.setPassword(newPwd);
                userInfoService.update(userInfo);
                result.put("code", 200);
                result.put("msg", "保存成功");
            } else {
                result.put("code", 400);
                result.put("msg", "旧密码不正确");
            }
        }

        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //endregion

    //region 好友管理

    //region 好友列表

    /**
     * @param userId
     * @param pageIndex
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/friendsList", method = RequestMethod.POST)
    @ResponseBody
    public void friendsList(@RequestParam(value = "userId", required = true) Long userId,
                            @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
                            HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("state",2);
        Page<Friends> page = friendsService.getPageList(map, pageIndex, 10);
        data.put("friends", page);
        data.put("page",new RdPage(page));
        Friends friends = new Friends();
        friends.setFriendUserId(userId);
        friends.setState(1);
        int applyCount = friendsService.selectcount(friends);
        data.put("applyCount", applyCount);
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", data);
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 好友申请列表

    /**
     * @param userId
     * @param pageIndex
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/friendsApplyList", method = RequestMethod.POST)
    @ResponseBody
    public void friendsApplyList(@RequestParam(value = "userId", required = true) Long userId,
                                 @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
                                 HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("state",1);
        Page<Friends> page = friendsService.getPageList(map, pageIndex, 10);
        if (page.getResult().size() > 0) {
            for (Friends item : page.getResult()) {
                item.setFriendUserId(item.getUserId());
                item.setFriendUserName(item.getUserName());
                item.setFriendSex(0);
            }
        }
        data.put("applyList", page);
        data.put("page",new RdPage(page));
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", data);
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 好友操作

    /**
     * @param userId
     * @param friendsListId
     * @param type          操作类型 2=同意申请 3=拒绝申请 4=删除好友
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/friendsOperation", method = RequestMethod.POST)
    @ResponseBody
    public void friendsOperation(@RequestParam(value = "userId", required = true) Long userId,
                                 @RequestParam(value = "friendsListId", required = true) Long friendsListId,
                                 @RequestParam(value = "type", required = true) Integer type,
                                 HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Friends friends = friendsService.findByPrimary(friendsListId);
        if (friends == null) {
            result.put("code", 400);
            result.put("msg", "该好友记录不存在");
        } else {
            switch (type) {
                case 2:
                    //region 同意好友申请
                    friends.setState(2);
                    if (friendsService.update(friends)) {
                        UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
                        Friends friends1 = new Friends();
                        friends1.setUserId(friends.getFriendUserId());
                        friends1.setUserName(friends.getFriendUserName());
                        friends1.setFriendUserId(userInfo.getId());
                        friends1.setFriendUserName(userInfo.getUserName());
                        friends1.setFriendSex(userInfo.getSex());
                        friends1.setState(2);
                        friends1.setCreateTime(new Date());
                        friendsService.save(friends1);
                        result.put("code", 200);
                        result.put("msg", "同意成功");
                    }
                    //endregion
                    break;
                case 3:
                    //region 拒绝好友申请
                    friends.setState(1);
                    friendsService.update(friends);
                    result.put("code", 200);
                    result.put("msg", "拒绝成功");
                    //endregion
                    break;
                case 4:
                    //region 删除好友
                    Friends friends1 = new Friends();
                    friends1.setUserId(friends.getFriendUserId());
                    friends1.setFriendUserId(friends.getUserId());
                    friendsService.delete(friends1);
                    friendsService.delete(friends);
                    result.put("code", 200);
                    result.put("msg", "删除成功");
                    //endregion
                    break;
            }
        }

        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //endregion

    //region 用户信息

    /**
     * @param userId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public void getUserInfo(@RequestParam(value = "userId", required = true) Long userId,
                            HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);

        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", userInfo);
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

}
