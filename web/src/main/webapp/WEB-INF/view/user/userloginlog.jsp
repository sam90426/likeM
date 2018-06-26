<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/30
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>app用户登录日志列表</title>
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
</head>
<body>
<div style="width: 100%;">
    <div class="mini-toolbar" style="border-bottom: 0; padding: 0px;">
        <table style="width: 100%;">
            <tbody>
            <tr>
                <td style="text-align: right;">
                    <input name="SearchType" class="mini-combobox" style="width: 90px;" textfield="text" valuefield="id"
                           allowinput="false" value="1"
                           data="[{id:'1',text:'登录名'},{id:'2',text:'姓名'}]"/>
                    <input id="key" name="key" class="mini-textbox" emptytext="请输入登录名" style="width: 100px;"/>
                    <a class="mini-button search()" onclick="search()">查询</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div id="datagrid1" class="mini-datagrid" style="width: 100%; height: 420px;" allowresize="true"
     url="/user/userloginlogp" multiselect="true" pagesize="25">
    <div property="columns">
        <div width="20" type="checkcolumn">
        </div>
        <div field="id" width="30" headeralign="center" align="center" allowsort="true">
            编号
        </div>
        <div field="userId" width="40" headeralign="center" align="center">
            用户编号
        </div>
        <div field="userName" width="50" headeralign="center" align="center">
            登录账号
        </div>
        <div field="realName" width="50" headeralign="center" align="center">
            联系人
        </div>
        <div field="state" width="40" headeralign="center" align="center" renderer="onState">
            状态
        </div>
        <div field="isApp" width="40" headeralign="center" align="center" renderer="onAPP">
            是否APP
        </div>
        <div field="version" width="40" headeralign="center" align="center">
            版本号
        </div>
        <div field="deviceInfo" width="95" headeralign="center">
            设备信息
        </div>
        <div field="createTime" width="75" headeralign="center" align="center" dateformat="yyyy-MM-dd HH:mm"
             allowsort="true">
            创建时间
        </div>
    </div>
</div>
<script src="/assets/pagejs/user/userloginloglist.js?v=1.0.0" type="text/javascript"></script>
</body>
</html>

