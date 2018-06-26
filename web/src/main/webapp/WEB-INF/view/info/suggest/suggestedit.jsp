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
                反馈内容
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>
                用户姓名：
            </th>
            <td>
                <input id="realName" name="realName" class="mini-textbox" style="width: 200px"
                       required="true" value=""/>
            </td>
            <th>
                手机号码：
            </th>
            <td>
                <input id="mobile" name="mobile" class="mini-textbox" style="width: 200px"
                       required="true" value=""/>
            </td>
        </tr>
        <tr>
            <th>
                状态：
            </th>
            <td>
                <input id="isRead" name="isRead" class="mini-combobox" style="width: 200px;" textfield="text"
                       valuefield="id" required="true" allowinput="false" value="1"
                       data="[{id:'1',text:'已读'},{id:'2',text:'未读'}]"/>
            </td>
            <th>
            </th>
            <td>
            </td>
        </tr>
        <tr>
            <th>
                反馈内容：
            </th>
            <td colspan="3">
                <input id="content" name="content" class="mini-textarea" style="width: 580px; height: 100px"/>
            </td>
        </tr>
        </tbody>
    </table>
    <div style="text-align: center; padding: 10px;">
         <a class="mini-button onCancel()" iconcls="icon-close"
                                           onclick="onCancel" style="width: 60px;">关闭</a>
    </div>
    <script src="/assets/pagejs/info/suggest/suggestedit.js" type="text/javascript"></script>
</form>
</body>
</html>

