<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>邀请列表</title>
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
</head>
<body>
<div style="width: 100%;">
    <div class="mini-toolbar" style="border-bottom: 0; padding: 0px;">
        <table style="width: 100%;">
            <tbody>
            <tr>
                <td>
                    <%--<a class="mini-button look()" iconcls="icon-search" onclick="look()">查看</a>--%>
                    <%--<a class="mini-button edit()" iconcls="icon-edit" onclick="remove()">删除</a>--%>
                </td>
                <td style="text-align: right;">
                    <span style="font-size: 9pt;">状态:
                        <input id="withdrawalsState" name="Type" class="mini-combobox" style="width: 100px;"
                               textfield="text" valuefield="id" allowinput="false" value=""
                               data="[{id:'',text:'全部'},{id:'0',text:'未申请'},{id:'1',text:'提现中'},{id:'2',text:'提现成功'},{id:'3',text:'提现失败'}]"
                               emptytext="选择状态"/>
                    </span>
                    <span style="font-size: 9pt;">邀请人名称:
                        <input id="userName" name="userName" class="mini-textbox" emptytext="请输入邀请人名称" style="width: 150px;"/>
                    </span>
                    <a class="mini-button search()" onclick="search()">查询</a>
                    <a class="mini-button resetsearch()" onclick="resetsearch()">重置</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div id="datagrid1" class="mini-datagrid" style="width: 100%; height: 420px;" allowresize="true"
     url="/userinvite/list" pagesize="25">
    <div property="columns">
        <div field="id" width="30" headeralign="center" align="center" allowsort="true">
            编号
        </div>
        <div field="userName" width="60" headeralign="center" align="center">
            邀请人名称
        </div>
        <div field="userId" width="50" headeralign="center" align="center">
            邀请人编号
        </div>
        <div field="inviteTime" width="100" headeralign="center" align="center" dateformat="yyyy-MM-dd HH:mm:ss">
            邀请时间
        </div>
        <div field="inviteName" width="60" headeralign="center" align="center">
            被邀请人名称
        </div>
        <div field="inviteId" width="60" headeralign="center" align="center">
            被邀请人编号
        </div>
        <div field="bounty" width="40" headeralign="center" align="center">
            奖励金
        </div>
        <div field="withdrawalsState" width="60" headeralign="center" align="center" renderer="onStateRender">
            提现状态
        </div>
    </div>
</div>
<script src="/assets/pagejs/invitation/invitation/invitationList.js?v=1.0.2" type="text/javascript"></script>
</body>
</html>
