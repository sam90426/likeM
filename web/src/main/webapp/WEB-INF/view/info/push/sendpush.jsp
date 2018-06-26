<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/27
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>推送服务</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
    <link href="/assets/css/Common.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form id="form1" method="post">
    <table border="0" cellspacing="0" cellpadding="0" class="inputTable">
        <thead>
        <tr>
            <th colspan="4">
                极光推送
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th style="width: 14%">
                推送内容：
            </th>
            <td style="width: 86%;" colspan="3">
                    <textarea id="content" name="content" class="mini-textarea" emptytext="最多72个字" style="width: 100%;
                        height: 100px;" required="true" vtype="maxLength:72"></textarea>
            </td>
        </tr>
        <tr>
            <th style="width: 14%">
                推送对象：
            </th>
            <td style="width: 36%">
                <input id="pushObj" name="pushObj" value="1" data="[{id:'1',text:'all'},{id:'2',text:'Android'},{id:'3',text:'iOS'}]"
                       class="mini-combobox" multiselect="false" emptytext="推送对象" />
            </td>
            <th style="width: 14%">

            </th>
            <td style="width: 36%">

            </td>
        </tr>
        <tr>
            <th style="width: 14%">
                具体参数：
            </th>
            <td style="width: 36%">
                <input id="pushObjPar" name="pushObjPar" value="1" data="[{id:'1',text:'广播(所有人)'},{id:'2',text:'设备别名(Alias)'},{id:'3',text:'设备标签(Tag)'}]"
                       class="mini-combobox" multiselect="false" emptytext="推送对象参数" onvaluechanged="onPushObjParChange" />
            </td>
            <th style="width: 14%">
                用户标识：
            </th>
            <td style="width: 36%">
                <input id="pushObjParValue" name="pushObjParValue" value="" class="mini-textbox"
                       valuefield="id" textfield="text" style="width: 100%" />
            </td>
        </tr>
        </tbody>
    </table>
    <table border="0" cellspacing="0" cellpadding="0" class="inputTable">
        <thead>
        <tr>
            <th colspan="4">
                可选参数
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>
                附加键：
            </th>
            <td>
                <input class="mini-textbox" id="addKey1" name="addKey1" readonly="readonly" value="url" />
            </td>
            <th>
                附加值：
            </th>
            <td>
                <input class="mini-textbox" id="addValue1" name="addValue1" width="400px" emptytext="跳转url(没有则填#)" />
            </td>
        </tr>
        </tbody>
    </table>
    <div style="text-align: center; padding: 10px;">
        <a class="mini-button onOk()" iconcls="icon-save" onclick="onOk" style="width: 60px;
            margin-right: 20px;">确定</a> <a class="mini-button onCancel()" iconcls="icon-close"
                                           onclick="onCancel" style="width: 60px;">取消</a>
    </div>
</form>
<script type="text/javascript" src="/assets/pagejs/info/push/sendpush.js"></script>
</body>
</html>

