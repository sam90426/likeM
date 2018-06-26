<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/19
  Time: 9:56
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
    <input name="id" class="mini-hidden" value="0"/>
    <table border="0" cellspacing="0" cellpadding="0" class="inputTable">
        <thead>
        <tr>
            <th colspan="4">
                帮助中心字典
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th style="width: 14%">
                字典名称：
            </th>
            <td style="width: 86%;" colspan="3">
                <input name="dicName" class="mini-textbox" required="true" style="width: 350px" emptytext="请输入字典名称"
                       required="true" value=""/>
            </td>
        </tr>
        <tr>
            <th style="width: 14%">
                所属字典：
            </th>
            <td style="width: 86%;" colspan="3">
                <input name="dicPath" textname="path" textfield="dicName" valuefield="dicPath"
                       emptytext="不选择字典视为顶级字典" class="mini-treeselect" url="/info/helpdiclistp"
                       multiselect="false" style="width: 350px" parentfield="path" showclose="true"
                       oncloseclick="closeseldic"/>
            </td>
        </tr>
        <tr>
            <th>
                排序：
            </th>
            <td colspan="3">
                <input name="orderIndex" class="mini-textbox" vtype="int" emptytext="请输入数字类型" style="width: 350px"
                       value="0"/>
            </td>
        </tr>
        <tr>
            <th>
                状态：
            </th>
            <td colspan="3">
                <input id="state" name="state" class="mini-combobox" style="width: 350px;"
                       textfield="text" valuefield="id" required="true" allowinput="false" value="1"
                       data="[{id:'1',text:'使用'},{id:'2',text:'不使用'}]" requirederrortext="状态不能为空"/>
            </td>
        </tr>
        </tbody>
    </table>
    <div style="text-align: center; padding: 10px;">
        <a class="mini-button onOk()" iconcls="icon-save" onclick="onOk" style="width: 60px;
            margin-right: 20px;">确定</a> <a class="mini-button onCancel()" iconcls="icon-close"
                                           onclick="onCancel" style="width: 60px;">取消</a>
    </div>
    <script src="/assets/pagejs/info/helpdic/helpdicedit.js" type="text/javascript"></script>
</form>
</body>
</html>
