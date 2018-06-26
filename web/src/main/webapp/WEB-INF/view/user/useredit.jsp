<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>app用户新增/编辑</title>
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
                    用户信息
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th style="width: 14%">
                    登录账号：
                </th>
                <td style="width: 36%">
                    <input id="userName" name="userName" class="mini-textbox" required="true" emptytext="请输入登录账号" style="width: 200px;"/>
                </td>
                <th style="width: 14%">
                    手机号：
                </th>
                <td>
                    <input id="mobile" name="mobile" class="mini-textbox" style="width: 200px" required="true" emptytext="请输入手机号" />
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    登录密码：
                </th>
                <td style="width: 36%">
                    <input id="passWord" name="passWord" class="mini-textbox" required="true" style="width: 200px;" emptytext="请输入登录密码" onvalidation="onPwValidation" />
                </td>
                <th style="width: 14%">
                    二次密码：
                </th>
                <td style="width: 36%">
                    <input id="PassWord2" name="PassWord2" class="mini-textbox" required="true" emptytext="请再次输入登录密码" onvalidation="onPwsValidation" style="width: 200px;" />
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    真实姓名：
                </th>
                <td style="width: 36%">
                    <input id="realName" name="realName" class="mini-textbox" required="true" emptytext="请输入真实姓名" style="width: 200px;"/>
                </td>
                <th style="width: 14%">
                    性别：
                </th>
                <td>
                    <input id="sex" name="sex" class="mini-combobox" style="width: 200px;" textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'1',text:'男'},{id:'2',text:'女'}]" emptytext="请选择性别" />
                </td>
            </tr>
            <tr>
                <th style="width: 14%">
                    状态：
                </th>
                <td>
                    <input id="state" name="state" class="mini-combobox" style="width: 200px;" textfield="text" valuefield="id" allowinput="false" required="true" data="[{id:'1',text:'正常'},{id:'2',text:'停用'}]" emptytext="请选择状态" />
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
<script src="/assets/pagejs/user/useredit.js" type="text/javascript"></script>
</body>
</html>

