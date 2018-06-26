<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/18
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户编辑</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
    <link href="/assets/css/Common.css" rel="stylesheet" type="text/css" />
    <script src="/assets/js/common/Common.js" type="text/javascript"></script>
</head>
<body>
<form id="form1" method="post">
    <div style="padding-left: 11px; padding-bottom: 5px; width: 750px;">
        <input name="id" class="mini-hidden" />
        <table border="0" cellspacing="0" cellpadding="0" class="inputTable">
            <thead>
            <tr>
                <th colspan="4">
                    渠道信息
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th style="width: 14%">
                    渠道代码：
                </th>
                <td style="width: 36%">
                    <input id="code" name="code" class="mini-textbox" required="true" emptytext="请输入渠道代码"
                           style="width: 200px;"/>
                </td>
                <th style="width: 14%">
                    渠道商名称：
                </th>
                <td>
                    <input id="name" name="name" class="mini-textbox" style="width: 200px" required="true" />
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    联系人：
                </th>
                <td style="width: 36%">
                    <input id="linker" name="linker" class="mini-textbox" required="true" style="width: 200px;"/>
                </td>
                <th style="width: 14%">
                    联系人手机号：
                </th>
                <td style="width: 36%">
                    <input id="phone" name="phone" class="mini-textbox" required="true" onvalidation="onPhone"
                           style="width: 200px;" />
                </td>
            </tr>
            <tr>

                <th style="width: 14%">
                    状态：
                </th>
                <td>
                    <input id="state" name="state" class="mini-combobox" style="width: 200px;" value="10"
                           textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'10',text:'正常'},{id:'20',text:'停用'}]" />
                </td>
                <th style="width: 14%">
                </th>
                <td style="width: 36%">
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="text-align: center; padding: 10px; width: 750px;">
        <a class="mini-button onOk()" iconcls="icon-save" onclick="onOk" style="width: 60px;
            margin-right: 20px;">确定</a>
    </div>
</form>
<script src="/assets/pagejs/channel/channelEdit.js" type="text/javascript"></script>
</body>
</html>

