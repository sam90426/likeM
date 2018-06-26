<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/19
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
    <script src="/assets/js/common/Common.js" type="text/javascript"></script>
</head>
<body>
<div class="mini-toolbar" style="padding: 2px; border-top: 0; border-left: 0; border-right: 0;">
    <a class="mini-button add()" iconcls="icon-add" onclick="add()">新增</a>
    <a class="mini-button edit()" iconcls="icon-edit" onclick="edit()">编辑</a>
    <a class="mini-button remove()" iconcls="icon-remove" onclick="remove()">删除</a>
</div>
<div id="datagrid1" class="mini-datagrid" style="width: 100%;" allowresize="true"
     url="/info/helpdiccontentlistp" multiselect="true" pagesize="25"
     onrowdblclick="edit()">
    <div property="columns">
        <div type="checkcolumn">
        </div>
        <div field="id" width="30" headeralign="center" allowsort="true">
            编号
        </div>
        <div field="title" width="80" headeralign="center" allowsort="true">
            标题
        </div>
        <div field="dicPath" width="100" headeralign="center" allowsort="true">
            路径
        </div>
        <div field="orderIndex" width="40" headeralign="center" align="center" allowsort="true">
            排序编号
        </div>
        <div field="state" width="40" headeralign="center" align="center" allowsort="true" renderer="onState">
            是否使用
        </div>
        <div field="createTime" width="50" headeralign="center" align="center" dateformat="yyyy-MM-dd HH:mm">
            创建时间
        </div>
    </div>
</div>
<script src="/assets/pagejs/info/helpdic/helpdiccontentlist.js?v=1.0.0" type="text/javascript"></script>
</body>
</html>

