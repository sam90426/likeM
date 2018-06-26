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
</head>
<body>
<form id="form1" method="post">
    <input id="id" name="id" class="mini-hidden" value="0" />
    <input id="dicPath" name="dicPath" class="mini-hidden" />
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
            <th>
                string1：
            </th>
            <td>
                <input id="string1" name="string1" class="mini-textbox" style="width: 230px" emptytext="请输入"
                        value=""/>
            </td>

            <th>
                string2：
            </th>
            <td>
                <input id="string2" name="string2" class="mini-textbox" style="width: 230px" emptytext="请输入"
                        value=""/>
            </td>

        </tr>
        <tr>
            <th>
                string3：
            </th>
            <td>
                <input id="string3" name="string3" class="mini-textbox" style="width: 230px" emptytext="请输入"
                       value=""/>
            </td>

            <th>
                string4：
            </th>
            <td>
                <input id="string4" name="string4" class="mini-textbox" style="width: 230px" emptytext="请输入"
                       value=""/>
            </td>

        </tr>
        <tr>
            <th>
                zs1：
            </th>
            <td>
                <input id="zs1" name="zs1" class="mini-textbox" style="width: 150px" emptytext="请输入"
                        vtype="int" value="0"/>
            </td>

            <th>
                zs2：
            </th>
            <td>
                <input id="zs2" name="zs2" class="mini-textbox" style="width: 150px" vtype="int" emptytext="请输入"
                        value="0"/>
            </td>
        </tr>
        <tr>
            <th>
                zs3：
            </th>
            <td>
                <input id="zs3" name="zs3" class="mini-textbox" style="width: 150px" emptytext="请输入"
                        vtype="int" value="0"/>
            </td>

            <th>
                zs4：
            </th>
            <td>
                <input id="zs4" name="zs4" class="mini-textbox" style="width: 150px" vtype="int" emptytext="请输入"
                        value="0"/>
            </td>
        </tr>
        <tr>
            <th>
                intro1：
            </th>
            <td>
                <input id="intro1" name="intro1" class="mini-textbox" style="width: 150px" emptytext="请输入"
                        vtype="text" value=""/>
            </td>

            <th>
                intro2：
            </th>
            <td>
                <input id="intro2" name="intro2" class="mini-textbox" style="width: 150px" vtype="text" emptytext="请输入"
                        value=""/>
            </td>
        </tr>
        <tr>
            <th>
                排序：
            </th>
            <td>
                <input id="orderIndex" name="orderIndex" class="mini-textbox" style="width: 150px" emptytext="请输入"
                        vtype="int" value="0"/>
            </td>

            <th>
            </th>
            <td>
            </td>
        </tr>
        <tr>
            <th>
                简介：
            </th>
            <td colspan="3">
                <input id="content" name="content" class="mini-textarea" style="width: 580px; height: 100px"/>
            </td>
        </tr>
        </tbody>
    </table>
    <div style="text-align: center; padding: 10px;">
        <a class="mini-button onOk()" iconcls="icon-save" onclick="onOk" style="width: 60px;
            margin-right: 20px;">确定</a> <a class="mini-button onCancel()" iconcls="icon-close"
                                           onclick="onCancel" style="width: 60px;">取消</a>
    </div>
    <script src="/assets/pagejs/sys/sysdiccontentedit.js" type="text/javascript"></script>
</form>
</body>
</html>

