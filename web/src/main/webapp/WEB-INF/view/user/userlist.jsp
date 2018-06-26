<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>app用户列表</title>
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
</head>
<body>
<div style="width: 100%;">
    <div class="mini-toolbar" style="border-bottom: 0; padding: 0px;">
        <table style="width: 100%;">
            <tbody>
            <tr>
                <td>
                    <a class="mini-button add()" iconcls="icon-add" onclick="add()">增加</a>
                    <a class="mini-button edit()" iconcls="icon-edit" onclick="edit()">编辑</a>
                    <a class="mini-button unlock()" iconcls="icon-unlock" onclick="unlock()">启用</a>
                    <a class="mini-button lock()" iconcls="icon-lock" onclick="lock()">停用</a>
                </td>
                <td style="text-align: right;">
                    <span style="font-size: 9pt;">注册渠道：<input id="channelId" name="channelId" class="mini-combobox" style="width: 150px;" textfield="text" valuefield="id" allowinput="false" data='${jsonObject}' emptytext="请选择渠道商"/></span>
                    <span style="font-size: 9pt;">创建时间：</span><input id="startTime" class="mini-datepicker" style="width:150px;" format="yyyy-MM-dd" emptytext="请输入起始时间" ondrawdate="onDrawDate"  onvaluechanged="onValueChanged"/> -
                    <input id="endTime" class="mini-datepicker" style="width:150px;" format="yyyy-MM-dd" emptytext="请输入结束时间" ondrawdate="onDrawDate"/>
                    <input name="SearchType" class="mini-combobox" style="width: 150px;" textfield="text" onvaluechanged="onSearchType" valuefield="id" allowinput="false" value="1" data="[{id:'1',text:'登录名'},{id:'2',text:'真实姓名'}]" emptytext="请选择类型"/>
                    <input id="key" name="key" class="mini-textbox" emptytext="请输入登录名" style="width: 150px;"/>
                    <a class="mini-button search()" iconCls="icon-search" onclick="search()">查询</a>
                    <a class="mini-button userExport()" iconCls="icon-print" onclick="userExport()">导出</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div id="datagrid1" class="mini-datagrid" style="width: 100%; height: 420px;" allowresize="true"
     url="/user/getUserList" multiselect="true" pagesize="25" onrowdblclick="edit()">
    <div property="columns">
        <div width="20" type="checkcolumn">
        </div>
        <div field="id" width="30" headeralign="center" align="center" allowsort="true">
            编号
        </div>
        <div field="userName" width="60" headeralign="center" align="center">
            用户名/帐号
        </div>
        <div field="realName" width="60" headeralign="center" align="center">
            真实姓名
        </div>
        <div field="mobile" width="60" headeralign="center" align="center">
            手机号
        </div>
        <div field="channelName" width="40" headeralign="center" align="center" allowsort="true">
            注册渠道
        </div>
        <div field="state" width="30" headeralign="center" align="center" renderer="onState">
            状态
        </div>
        <div field="loginCount" width="40" headeralign="center" align="center" allowsort="true">
            登陆次数
        </div>
        <div field="lastLoginTime" width="75" headeralign="center" align="center" dateformat="yyyy-MM-dd HH:mm"
             allowsort="true" >
            最后登录时间
        </div>
        <div field="createTime" width="75" headeralign="center" align="center" dateformat="yyyy-MM-dd HH:mm"
             allowsort="true">
            创建时间
        </div>
    </div>
</div>
<script src="/assets/pagejs/user/userlist.js?v=1.0.1" type="text/javascript"></script>
</body>
</html>
