package com.quygt.dkcs.controller;

import com.quygt.dkcs.model.UserInvite;
import com.quygt.dkcs.service.UserInviteService;
import com.quygt.dkcs.utils.PageUtil;
import com.quygt.dkcs.utils.ParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.quygt.dkcs.utils.JsonUtil.Encode;

@Controller
@RequestMapping(value = "/userinvite", produces = "text/plain;charset=UTF-8")
public class UserinviteController {

    @Autowired
    private UserInviteService userInviteService;

    private static final String INVITATION_LIST = "invitation/invitation/invitationList";

    @RequestMapping("/invitationList")
    public String invitationList() {
        return INVITATION_LIST;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer pageIndex, UserInvite userInvite) {
        int pageSize = ParseUtil.toInt(request.getParameter("pageSize"), 20);
        PageUtil<UserInvite> page = userInviteService.getPageList(userInvite, pageIndex, pageSize);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", page.getData());
        data.put("total", page.getTotalItems());
        return Encode(data);
    }
}
