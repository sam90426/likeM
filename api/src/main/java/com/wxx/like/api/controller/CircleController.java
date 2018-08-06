package com.wxx.like.api.controller;

import com.github.pagehelper.Page;
import com.wxx.like.api.common.ServletUtils;
import com.wxx.like.model.*;
import com.wxx.like.service.*;
import com.wxx.like.utils.ConfigUtil;
import com.wxx.like.utils.RdPage;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tool.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Calendar;
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
    @RequestMapping(value = "/friendsCircle", method = RequestMethod.POST)
    public void friendsCircle(@RequestParam(value = "userId", required = true) Long userId,
                              @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
                              HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Page<CircleInfo> page = circleInfoService.getFriendsPageList(userId, pageIndex, 10);
        if (page.getResult().size() > 0) {
            for (CircleInfo item : page.getResult()) {
                if (item.getLogo().contains(":/")) {
                    item.setLogo("/readFile.htm?path=" + item.getLogo().replace("/", "\\"));
                }
                if (!item.getPicUrl().isEmpty()) {
                    String[] pics = item.getPicUrl().split(",");
                    String newPic = "";
                    if (pics.length > 0) {
                        for (int i = 0; i < pics.length; i++) {
                            if (pics[i].contains(":/")) {
                                newPic = newPic + "," + "/readFile.htm?path=" + pics[i].replace("/", "\\");
                            }
                        }
                        item.setPicUrl(newPic.substring(1));
                    }
                }
                Map<String, Object> map = new HashMap<>();
                map.put("circleId", item.getId());
                item.setZanList(circleZanService.listSelective(map));
                item.setCommentList(circleCommentService.listSelective(map));
                CircleZan circleZan = new CircleZan();
                circleZan.setCircleId(item.getId());
                circleZan.setUserId(userId);
                item.setZanCount(circleZanService.selectcount(circleZan) > 0 ? 1 : 2);
            }
        }
        Map<String, Object> data = new HashMap<>();
        data.put("friendsCircle", page);
        data.put("pageInfo", new RdPage(page));
        result.put("code", 200);
        result.put("msg", "操作成功");
        result.put("data", data);
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 上传动态图片
    public void pushCircleImg() throws Exception {

    }
    //endregion

    //region 发布动态

    /**
     * @param userId
     * @param content
     * @param label
     * @param country
     * @param isout
     * @param multiFile
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/sendCircle", method = RequestMethod.POST)
    public void sendCircle(@RequestParam(value = "userId", required = true) Long userId,
                           @RequestParam(value = "content", required = true) String content,
                           @RequestParam(value = "label", required = true) String label,
                           @RequestParam(value = "country", required = true) String country,
                           @RequestParam(value = "isout", required = true) Integer isout,
                           @RequestParam(value = "image") MultipartFile[] multiFile,
                           @RequestParam(value = "video") MultipartFile[] multiFileByVideo,
                           HttpServletResponse response) throws Exception {
        Map<String, Object> reslut = new HashMap<>();
        if (content.isEmpty()) {
            reslut.put("code", 400);
            reslut.put("msg", "请输入动态内容");
            ServletUtils.writeToResponse(response, reslut);
            return;
        }
        UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
        String imgPath = "";
        if (multiFile != null && multiFile.length > 0) {
            for (int i = 0; i < multiFile.length; i++) {
                MultipartFile fileitem = multiFile[i];
                //region 保存文件
                //获取服务器物理路径
                String basedir = ConfigUtil.getInstance().getString("PicPath");
                //路径
                Calendar cal = Calendar.getInstance();
                String path = "/circleImg";
                path = path + "/" + cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.HOUR_OF_DAY);
                String dir = basedir + path;

                File file = new File(dir);
                if (!file.exists()) {
                    file.mkdirs();
                }
                String filename = fileitem.getOriginalFilename();
                //防止文件被覆盖，以纳秒生成文件
                Long _l = System.nanoTime();
                //String _extfilename = filename.substring(filename.indexOf("."));
                filename = _l.toString() + ".jpg";
                try {
                    FileUtils.writeByteArrayToFile(new File(dir, filename), fileitem.getBytes());
                    Map data = new HashMap<String, Object>();
                    data.put("fileName", filename);
                    data.put("fileSize", fileitem.getSize() / 1024 / 1024);
                    String imgpath = basedir + path + "/" + filename;
                    imgPath = "," + imgpath;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //endregion

            }
        }

        if (multiFileByVideo != null && multiFileByVideo.length > 0) {
            for (int i = 0; i < multiFileByVideo.length; i++) {
                MultipartFile fileitem = multiFileByVideo[i];
                //region 保存文件
                //获取服务器物理路径
                String basedir = ConfigUtil.getInstance().getString("PicPath");
                //路径
                Calendar cal = Calendar.getInstance();
                String path = "/circleVideo";
                path = path + "/" + cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.HOUR_OF_DAY);
                String dir = basedir + path;

                File file = new File(dir);
                if (!file.exists()) {
                    file.mkdirs();
                }
                String filename = fileitem.getOriginalFilename();
                //防止文件被覆盖，以纳秒生成文件
                Long _l = System.nanoTime();
                String _extfilename = filename.substring(filename.indexOf("."));
                filename = _l.toString() + _extfilename;
                try {
                    FileUtils.writeByteArrayToFile(new File(dir, filename), fileitem.getBytes());
                    Map data = new HashMap<String, Object>();
                    data.put("fileName", filename);
                    data.put("fileSize", fileitem.getSize() / 1024 / 1024);
                    String imgpath = basedir + path + "/" + filename;
                    imgPath = imgPath+"," + imgpath;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //endregion

            }
        }
        if (StringUtil.isNotBlank(imgPath)) {
            imgPath = imgPath.substring(1);
        }

        CircleInfo circleInfo = new CircleInfo();
        circleInfo.setUserId(userInfo.getId());
        circleInfo.setUserName(userInfo.getUserName());
        circleInfo.setSex(userInfo.getSex());
        circleInfo.setLeave(userInfo.getLevel());
        circleInfo.setLogo(userInfo.getLogo());
        circleInfo.setContent(content);
        circleInfo.setCountry(country);
        circleInfo.setIsOut(isout);
        circleInfo.setLable(label);
        circleInfo.setPicUrl(imgPath);
        circleInfo.setZanCount(0);
        circleInfo.setCommentCount(0);
        circleInfo.setCreateTime(new Date());
        if (multiFileByVideo != null && multiFileByVideo.length > 0) {
            circleInfo.setType(3);
        } else if (multiFile != null && multiFile.length > 0) {
            circleInfo.setType(2);
        } else {
            circleInfo.setType(1);
        }
        if (circleInfoService.save(circleInfo)) {
            reslut.put("code", 200);
            reslut.put("msg", "发布成功");
        } else {
            reslut.put("code", 400);
            reslut.put("msg", "发布失败");
        }
        ServletUtils.writeToResponse(response, reslut);
    }
    //endregion

    //region 申请好友

    /**
     * @param userId
     * @param friendsId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/friendApply", method = RequestMethod.POST)
    @ResponseBody
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
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userInfo.getId());
        map.put("friendUserId", friendInfo.getId());
        Friends friends = friendsService.findSelective(map);
        if (friends == null) {
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
        } else if (friends.getState() == 1) {
            result.put("code", 400);
            result.put("msg", "已申请,等待通过");
            ServletUtils.writeToResponse(response, result);
            return;
        } else if (friends.getState() == 2) {
            result.put("code", 400);
            result.put("msg", "已是好友");
            ServletUtils.writeToResponse(response, result);
            return;
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
    @ResponseBody
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

    /**
     * @param userId
     * @param circleId
     * @param pageIndex
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/zanList", method = RequestMethod.POST)
    public void zanList(@RequestParam(value = "userId", required = true) Long userId,
                        @RequestParam(value = "circleId", required = true) Long circleId,
                        @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
                        HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("circleId", circleId);
        Page<CircleZan> page = circleZanService.getPageList(map, pageIndex, 10);
        map = new HashMap<>();
        map.put("zanList", page);
        map.put("pageInfo", new RdPage(page));
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", map);
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
    @ResponseBody
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
            Map<String, Object> map = new HashMap<>();
            map.put("circleId", circleId);
            map.put("userId", userId);
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
                    circleInfo.setZanCount(+1);
                    circleInfoService.update(circleInfo);
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
    @ResponseBody
    public void cancelCircleZan(@RequestParam(value = "userId", required = true) Long userId,
                                @RequestParam(value = "circleId", required = true) Long circleId,
                                HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("circleId", circleId);
        map.put("userId", userId);
        CircleZan circleZan = circleZanService.findSelective(map);
        if (circleZan == null) {
            result.put("code", 400);
            result.put("msg", "取消点赞失败");
        } else {
            if (circleZanService.delete(circleZan)) {
                CircleInfo circleInfo = circleInfoService.findByPrimary(circleId);
                circleInfo.setZanCount(circleInfo.getZanCount() > 0 ? -1 : 0);
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
    @RequestMapping(value = "/commentList", method = RequestMethod.POST)
    public void commentList(@RequestParam(value = "userId", required = true) Long userId,
                            @RequestParam(value = "circleId", required = true) Long circleId,
                            @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
                            HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("circleId", circleId);
        Page<CircleComment> page = circleCommentService.getPageList(map, pageIndex, 10);
        map = new HashMap<>();
        map.put("zanList", page);
        map.put("pageInfo", new RdPage(page));
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", map);
        ServletUtils.writeToResponse(response, result);
    }
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
    @ResponseBody
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
        Long id = circleCommentService.insertBackId(circleComment);
        if (id > 0) {
            circleComment.setId(id);
            circleInfo.setCommentCount(+1);
            circleInfoService.update(circleInfo);
            result.put("code", 200);
            result.put("msg", "评论成功");
            result.put("data", circleComment);
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
    @ResponseBody
    public void delCircleComment(@RequestParam(value = "userId", required = true) Long userId,
                                 @RequestParam(value = "commentId", required = true) Long commentId,
                                 HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("commentId", commentId);
        CircleComment circleComment = circleCommentService.findSelective(map);
        if (circleComment == null) {
            result.put("code", 400);
            result.put("msg", "评论不存在");
        } else {
            if (circleCommentService.delete(circleComment)) {
                CircleInfo circleInfo = circleInfoService.findByPrimary(circleComment.getCircleId());
                circleInfo.setCommentCount(circleInfo.getCommentCount() > 0 ? -1 : 0);
                circleInfoService.update(circleInfo);
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
