package com.quygt.dkcsapi.controller;

import com.quygt.dkcs.model.LikeArticle;
import com.quygt.dkcs.model.UserInfo;
import com.quygt.dkcs.service.LikeArticleService;
import com.quygt.dkcs.service.UserInfoService;
import com.quygt.dkcs.utils.PageUtil;
import com.quygt.dkcsapi.common.ServletUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: wuxianxin
 * @Date: 2018/7/11 15:58
 * @Description:
 */
@RequestMapping(value = "/article", produces = "text/plain;charset=UTF-8")
public class ArticleController extends BaseController {

    @Resource
    private UserInfoService userInfoService;
    @Resource
    private LikeArticleService likeArticleService;

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
        if (likeArticleService.insert(likeArticle)) {
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
    public void articleList(@RequestParam(value = "userId", required = true) Long userId,
                            @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
                            HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        LikeArticle likeArticle = new LikeArticle();
        likeArticle.setState(1);
        PageUtil<LikeArticle> page = likeArticleService.getPageList(likeArticle, pageIndex, 10);
        if (page.getData().size() > 0) {
            likeArticle = new LikeArticle();
            likeArticle.setUserId(userId);
            for (LikeArticle item : page.getData()) {
                likeArticle.setId(item.getId());
                int count = likeArticleService.selectCount(likeArticle);
                if (count > 0) {
                    item.setZanCount(1);
                } else {
                    item.setZanCount(0);
                }
            }
        }
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", page);
        ServletUtils.writeToResponse(response, result);
    }
    //endregion
}
