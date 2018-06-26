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
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
    <link href="/assets/css/Common.css" rel="stylesheet" type="text/css"/>
    <script src="/assets/js/common/Common.js" type="text/javascript"></script>
    <script src="/assets/js/jquery-1.7.2.js" type="text/javascript"></script>
</head>
<body>
<form id="form1" method="post">
    <input id="id" name="id" class="mini-hidden" value="0" />
    <table border="0" cellspacing="0" cellpadding="0" class="inputTable">
        <thead>
        <tr>
            <th colspan="4">
                提现结果
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>支付宝账号：</th>
            <td>
                <input id="alipayAccount" name="alipayAccount" class="mini-textbox" style="width: 200px;" textfield="text"
                       valuefield="alipayAccount" allowinput="false"/>
            </td>
            <th>支付宝名称：</th>
            <td>
                <input id="alipayName" name="alipayName" class="mini-textbox" style="width: 200px;" textfield="text"
                       valuefield="alipayName" allowinput="false"/>
            </td>
        </tr>
        <tr>
            <th>
                状态：
            </th>
            <td>
                <input id="state" name="state" class="mini-combobox" style="width: 200px;" textfield="text"
                       valuefield="id" required="true" data="[{id:'1',text:'提现中'},{id:'2',text:'提现成功'},{id:'3',text:'提现失败'}]"/>
            </td>
            <th>
            </th>
            <td>
            </td>
        </tr>
        <tr>
            <th>
                失败原因：
            </th>
            <td colspan="3">
                <input id="failReason" name="failReason" class="mini-textarea" style="width: 580px; height: 100px"/>
            </td>
        </tr>
        </tbody>
    </table>
    <div style="text-align: center; padding: 10px;">
        <a id="submitBtn" class="mini-button onSubmit()" iconcls="icon-ok"
           onclick="onSubmit" style="width: 60px;">保存</a>
         <a class="mini-button onCancel()" iconcls="icon-close"
                                           onclick="onCancel" style="width: 60px;">关闭</a>
    </div>
    <script src="/assets/pagejs/invitation/withdrawals/withdrawalsModal.js?v=1.0.0" type="text/javascript"></script>
</form>
</body>
</html>

