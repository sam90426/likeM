package com.wxx.like.api.controller;

import com.github.pagehelper.Page;
import com.wxx.like.api.common.ServletUtils;
import com.wxx.like.model.LikeArticle;
import com.wxx.like.model.LikeComment;
import com.wxx.like.model.LikeZan;
import com.wxx.like.model.UserInfo;
import com.wxx.like.service.LikeArticleService;
import com.wxx.like.service.LikeCommentService;
import com.wxx.like.service.LikeZanService;
import com.wxx.like.service.UserInfoService;
import com.wxx.like.utils.PageUtil;
import com.wxx.like.utils.RdPage;
import org.aspectj.bridge.MessageWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.keyinfo.PGPData;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Administrator
 * @Auther: wuxianxin
 * @Date: 2018/7/11 15:58
 * @Description:
 */
@Controller
@RequestMapping(value = "/article", produces = "text/plain;charset=UTF-8")
public class ArticleController extends BaseController {

    @Resource
    private UserInfoService userInfoService;
    @Resource
    private LikeArticleService likeArticleService;
    @Resource
    private LikeZanService likeZanService;
    @Resource
    private LikeCommentService likeCommentService;

    //region 官方文章发布

    /**
     * @param userId
     * @param title
     * @param content
     * @param picUrl
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/sendArticle", method = RequestMethod.POST)
    @ResponseBody
    public void sendArticle(@RequestParam(value = "userId", required = true) Long userId,
                            @RequestParam(value = "title", required = true) String title,
                            @RequestParam(value = "content", required = true) String content,
                            @RequestParam(value = "picUrl", required = true) String picUrl,
                            HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
        LikeArticle likeArticle = new LikeArticle();
        likeArticle.setUserId(userInfo.getId());
        likeArticle.setUserName(userInfo.getUserName());
        likeArticle.setSex(userInfo.getSex());
        likeArticle.setLogo(userInfo.getLogo());
        likeArticle.setLeave(userInfo.getLevel());
        likeArticle.setTitle(title);
        likeArticle.setContent(content);
        likeArticle.setPicUrl(picUrl);
        likeArticle.setState(2);
        likeArticle.setZanCount(0);
        likeArticle.setCommentCount(0);
        likeArticle.setHits(0);
        likeArticle.setCreateTime(new Date());
        if (likeArticleService.save(likeArticle)) {
            result.put("code", 200);
            result.put("msg", "发布成功");
        } else {
            result.put("code", 400);
            result.put("msg", "发布失败");
        }

        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 官方文章列表

    /**
     * @param userId
     * @param pageIndex
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/articleList", method = RequestMethod.POST)
    @ResponseBody
    public void articleList(@RequestParam(value = "userId", required = true) Long userId,
                            @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
                            HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Map<String,Object> map=new HashMap<>();
        map.put("state",1);
        Page<LikeArticle> page = likeArticleService.getPageList(map, pageIndex, 10);
        if (page.getResult().size() > 0) {
            LikeZan likeZan=new LikeZan();
            likeZan.setUserId(userId);
            for (LikeArticle item : page.getResult()) {
                likeZan.setLikeId(item.getId());
                int count = likeZanService.selectcount(likeZan);
                if (count > 0) {
                    item.setZanCount(1);
                } else {
                    item.setZanCount(0);
                }
            }
        }
        map=new HashMap<>();
        map.put("articleList",page);
        map.put("pageInfo",new RdPage(page));
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", map);
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 官方文章点赞

    /**
     * @param userId
     * @param articleId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/articleZan", method = RequestMethod.POST)
    @ResponseBody
    public void articleZan(@RequestParam(value = "userId", required = true) Long userId,
                           @RequestParam(value = "articleId", required = true) Long articleId,
                           HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
        LikeArticle likeArticle = likeArticleService.findByPrimary(articleId);
        if (likeArticle == null) {
            result.put("code", 400);
            result.put("msg", "文章不存在");
            ServletUtils.writeToResponse(response, result);
            return;
        }
        LikeZan likeZan = new LikeZan();
        likeZan.setUserId(userInfo.getId());
        likeZan.setLikeId(likeArticle.getId());
        int count = likeZanService.selectcount(likeZan);
        if (count > 0) {
            result.put("code", 400);
            result.put("msg", "已经赞过了");
            ServletUtils.writeToResponse(response, result);
            return;
        }
        likeZan.setLogo(userInfo.getLogo());
        likeZan.setSex(userInfo.getSex());
        likeZan.setUserName(userInfo.getUserName());
        likeZan.setCreateTime(new Date());
        if (likeZanService.save(likeZan)) {
            result.put("code", 200);
            result.put("msg", "点赞成功");
        } else {
            result.put("code", 400);
            result.put("msg", "点赞失败,请重试");
        }

        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 官方文章评论

    /**
     * @param userId
     * @param articleId
     * @param replyUserId
     * @param content
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/articleComment", method = RequestMethod.POST)
    @ResponseBody
    public void articleComment(@RequestParam(value = "userId", required = true) Long userId,
                               @RequestParam(value = "articleId", required = true) Long articleId,
                               @RequestParam(value = "replyUserId", required = true) Long replyUserId,
                               @RequestParam(value = "content", required = true) String content,
                               HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
        LikeArticle likeArticle = likeArticleService.findByPrimary(articleId);
        if (likeArticle == null) {
            result.put("code", 400);
            result.put("msg", "文章不存在");
            ServletUtils.writeToResponse(response, result);
            return;
        }
        LikeComment likeComment = new LikeComment();
        likeComment.setUserId(userInfo.getId());
        likeComment.setUserName(userInfo.getUserName());
        likeComment.setSex(userInfo.getSex());
        likeComment.setLogo(userInfo.getLogo());
        likeComment.setLikeId(likeArticle.getId());
        likeComment.setLikeTitle(likeArticle.getTitle());
        if (replyUserId != null && replyUserId > 0) {
            UserInfo userComment = userInfoService.findUserInfoByUserId(replyUserId);
            likeComment.setReplyUserId(userComment.getId());
            likeComment.setReplyUserName(userComment.getUserName());
        }
        likeComment.setComment(content);
        likeComment.setCreateTime(new Date());
        if (likeCommentService.save(likeComment)) {
            result.put("code", 200);
            result.put("msg", "评论成功");
        } else {
            result.put("code", 400);
            result.put("msg", "评论失败");
        }
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 官方文章详情

    /**
     * @param articleId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/articleDetail",method = RequestMethod.POST)
    @ResponseBody
    public void articleDetail(@RequestParam(value = "articleId", required = true) Long articleId,
                              HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        LikeArticle likeArticle = likeArticleService.findByPrimary(articleId);
        if (likeArticle == null) {
            result.put("code", 400);
            result.put("msg", "该文章不存在");
        } else {
            result.put("code", 200);
            result.put("msg", "查询成功");
            result.put("data", likeArticle);
        }
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 官方文章点赞列表

    /**
     * @param articleId
     * @param pageIndex
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/articleZanList", method = RequestMethod.POST)
    @ResponseBody
    public void articleZanList(@RequestParam(value = "articleId", required = true) Long articleId,
                               @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
                               HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        LikeZan likeZan = new LikeZan();
        likeZan.setLikeId(articleId);
        Map<String,Object> map=new HashMap<>();
        map.put("articleId",articleId);
        Page<LikeZan> page = likeZanService.getPageList(map, pageIndex, 10);
        map= new HashMap<>();
        map.put("articleZanList", page);
        map.put("pageInfo",new RdPage(page));
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", map);
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 官方文章评论列表

    /**
     * @param articleId
     * @param pageIndex
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/articleCommentList", method = RequestMethod.POST)
    @ResponseBody
    public void articleCommentList(@RequestParam(value = "articleId", required = true) Long articleId,
                                   @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
                                   HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        LikeComment likeComment = new LikeComment();
        likeComment.setLikeId(articleId);
        Map<String,Object> map=new HashMap<>();
        map.put("articleId",articleId);
        Page<LikeComment> page = likeCommentService.getPageList(map, pageIndex, 10);
        map=new HashMap<>();
        map.put("articleCommentList",page);
        map.put("pageInfo",new RdPage(page));
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", map);
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 删除评论

    /**
     * @param userId
     * @param commentId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/delSrticleCom", method = RequestMethod.POST)
    @ResponseBody
    public void delSrticleCom(@RequestParam(value = "userId", required = true) Long userId,
                              @RequestParam(value = "commentId", required = true) Long commentId,
                              HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
        LikeComment likeComment = likeCommentService.findByPrimary(commentId);
        if (likeComment == null) {
            result.put("code", 400);
            result.put("msg", "该评论不存在");
            ServletUtils.writeToResponse(response, result);
            return;
        }
        if (!Objects.equals(likeComment.getUserId(), userInfo.getId())) {
            result.put("code", 400);
            result.put("msg", "没有删除权限");
            ServletUtils.writeToResponse(response, result);
            return;
        }
        if (likeCommentService.delete(likeComment)) {
            result.put("code", 200);
            result.put("msg", "删除成功");
        } else {
            result.put("code", 400);
            result.put("msg", "删除失败");
        }
        ServletUtils.writeToResponse(response, result);
    }
    //endregion

    //region 官方文章取消点赞

    /**
     * @param userId
     * @param articleId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/delArticleZan", method = RequestMethod.POST)
    @ResponseBody
    public void delArticleZan(@RequestParam(value = "userId", required = true) Long userId,
                              @RequestParam(value = "articleId", required = true) Long articleId,
                              HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        UserInfo userInfo = userInfoService.findUserInfoByUserId(userId);
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("articleId",articleId);
        LikeZan likeZan=likeZanService.findSelective(map);
        if (likeZan == null) {
            result.put("code", 400);
            result.put("msg", "操作失败");
            ServletUtils.writeToResponse(response, result);
            return;
        }
        if (!Objects.equals(likeZan.getUserId(), userInfo.getId())) {
            result.put("code", 400);
            result.put("msg", "没有删除权限");
            ServletUtils.writeToResponse(response, result);
            return;
        }
        if (likeZanService.delete(likeZan)) {
            result.put("code", 200);
            result.put("msg", "取消成功");
        } else {
            result.put("code", 400);
            result.put("msg", "取消失败");
        }
        ServletUtils.writeToResponse(response, result);
    }
    //endregion
}
