<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/19
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>广告位列表</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            border: 0;
            width: 100%;
            height: 100%;
            overflow: hidden;
        }
    </style>
</head>
<body>
<div class="mini-toolbar" style="padding: 2px; border-top: 0; border-left: 0; border-right: 0;">
    <a class="mini-button add()" iconcls="icon-add" onclick="add()">新增</a>
    <a class="mini-button edit()" iconcls="icon-edit" onclick="edit()">编辑</a>
    <a class="mini-button remove()" iconcls="icon-remove" onclick="remove()">删除</a>
    <span class="separator"></span>
    <input name="key" class="mini-textbox" emptytext="请输入名称"/>
    <a class="mini-button search()" iconcls="icon-search" onclick="search()">查找</a>
</div>
<div class="mini-splitter" style="width: 100%; height: 100%;">
    <div size="280" showcollapsebutton="true">
        <div class="mini-fit">
            <ul id="tree1" class="mini-tree" url="/info/ggdiclistp" style="width: 100%;"
                showtreeicon="true" textfield="dicName" idfield="dicPath" parentfield="path" expandOnLoad="0"
                resultastree="false" expandonload="true">
            </ul>
        </div>
    </div>
    <div showcollapsebutton="true">
        <iframe style="width: 100%; height: 100%; border: 0px;" id="mainForm"></iframe>
    </div>
</div>
<script src="/assets/pagejs/info/ggdic/ggdiclist.js" type="text/javascript"></script>
</body>
</html>
