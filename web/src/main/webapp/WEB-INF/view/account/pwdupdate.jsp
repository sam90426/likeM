<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/27
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
    <link href="/assets/css/Common.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form id="form1">
    <div style="width: 380px; text-align: center; margin: 0 auto;">
        <table border="0" cellspacing="0" cellpadding="0" class="inputTable">
            <thead>
            <tr>
                <th colspan="4">
                    密码修改<input name="ID" class="mini-hidden" />
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th style="width: 14%">
                    旧密码：
                </th>
                <td style="width: 36%;" colspan="3">
                    <input id="OldPwd" name="OldPwd" class="mini-textbox" style="width: 200px;" required="true"
                           vtype="required;rangeLength:5,64" />
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    新密码：
                </th>
                <td style="width: 36%" colspan="3">
                    <input id="NewPwd" name="NewPwd" class="mini-password" style="width: 200px;" required="true"
                           vtype="required;rangeLength:5,64" />
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    确认新密码：
                </th>
                <td style="width: 36%;" colspan="3">
                    <input id="ConfirmPwd" name="ConfirmPwd" class="mini-password" style="width: 200px;" required="true"
                           vtype="required;rangeLength:5,64" />
                </td>
            </tr>
            </tbody>
        </table>
        <div style="text-align: center; padding: 10px;">
            <a id="btnSubmit" class="mini-button onOk()" iconcls="icon-save" onclick="onOk" style="margin-right: 20px;">
                保存</a><a class="mini-button onCancel()" iconcls="icon-close" onclick="onCancel" style="width: 60px;">取消</a>
        </div>
    </div>
</form>
<script src="/assets/pagejs/account/pwdupdate.js" type="text/javascript"></script>
</body>
</html>
