<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/19
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <link href="/assets/css/Common.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/js/KindEditor/themes/default/default.css" rel="stylesheet" type="text/css"/>
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
    <script src="/assets/js/common/Common.js" type="text/javascript"></script>
    <script src="/assets/js/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="/assets/js/KindEditor/kindeditor-all.js"></script>
    <script src="/assets/js/KindEditor/lang/zh-CN.js"></script>
</head>
<body>
<form id="form1" method="post">
    <input id="id" name="id" class="mini-hidden" value="0"/>
    <input id="dicPath" name="dicPath" class="mini-hidden"/>
    <table border="0" cellspacing="0" cellpadding="0" class="inputTable">
        <thead>
        <tr>
            <th colspan="4">
                广告位字典
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th style="width: 14%">
                标题：
            </th>
            <td style="width: 86%;" colspan="3">
                <input id="title" name="title" class="mini-textbox" style="width: 550px" emptytext="请输入标题"
                       required="true" value=""/>
            </td>
        </tr>

        <tr>
            <th style="width: 14%">
                内容：
            </th>
            <td style="width: 86%" colspan="3">
                <textarea id="content" name="content" style="width: 85%; height: 300px; visibility: hidden;"></textarea>
            </td>
        </tr>
        <tr>
            <th>
                状态：
            </th>
            <td>
                <input id="state" name="state" class="mini-combobox" style="width: 200px;" textfield="text"
                       valuefield="id" required="true" allowinput="false" value="1"
                       data="[{id:'1',text:'使用'},{id:'2',text:'不使用'}]"
                       requirederrortext="状态不能为空"/>
            </td>
            <th>
                排序：
            </th>
            <td>
                <input name="orderIndex" class="mini-textbox" style="width: 200px" vtype="int" emptytext="请输入排序号"
                       required="true" value="0"/>
            </td>
        </tr>

        </tbody>
    </table>
    <div style="text-align: center; padding: 10px;">
        <a class="mini-button onOk()" iconcls="icon-save" onclick="onOk" style="width: 60px;
            margin-right: 20px;">确定</a> <a class="mini-button onCancel()" iconcls="icon-close"
                                           onclick="onCancel" style="width: 60px;">取消</a>
    </div>
    <script src="/assets/pagejs/info/helpdic/helpdiccontentedit.js" type="text/javascript"></script>
</form>
</body>
</html>

