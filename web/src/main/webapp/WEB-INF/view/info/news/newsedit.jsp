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
    <link href="/assets/js/KindEditor/themes/default/default.css" rel="stylesheet" type="text/css"/>
    <script src="/assets/js/common/Common.js" type="text/javascript"></script>
    <script src="/assets/js/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="/assets/js/KindEditor/kindeditor-all.js"></script>
    <script src="/assets/js/KindEditor/lang/zh-CN.js"></script>
    <script src="/assets/js/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
</head>
<body>
<form id="form1" method="post">
    <input id="id" name="id" class="mini-hidden" value="0" />
    <table border="0" cellspacing="0" cellpadding="0" class="inputTable">
        <thead>
        <tr>
            <th colspan="4">
                资讯内容
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>
                标题：
            </th>
            <td>
                <input id="title" name="title" class="mini-textbox" style="width: 200px" emptytext="请输入标题"
                       required="true" value=""/>
            </td>
            <th>
                状态：
            </th>
            <td>
                <input id="state" name="state" class="mini-combobox" style="width: 200px;" textfield="text"
                       valuefield="id" required="true" allowinput="false" value="1"
                       data="[{id:'1',text:'正常'},{id:'2',text:'停用'}]"
                       requirederrortext="状态不能为空"/>
            </td>
        </tr>
        <tr>
            <th>
                类型：
            </th>
            <td>
                <input id="type" name="type" class="mini-combobox" style="width: 200px;" textfield="text"
                       valuefield="id" required="true" allowinput="false" value="1"
                       data="[{id:'1',text:'热点'},{id:'2',text:'理财'},{id:'3',text:'贷款'}]"
                       requirederrortext="类型不能为空"/>
            </td>

            <th>
                排序：
            </th>
            <td>
                <input name="orderIndex" class="mini-textbox" style="width: 200px" vtype="int" emptytext="请输入排序号"
                       required="true" value="0"/>
            </td>
        </tr>
        <tr>
            <th>
                阅读量：
            </th>
            <td>
                <input name="readCount" class="mini-textbox" style="width: 200px" vtype="int" emptytext="请输入阅读量"
                       required="true" value="0"/>
            </td>

            <th>
                来源：
            </th>
            <td>
                <input name="source" class="mini-textbox" style="width: 200px" vtype="text" emptytext="请输入来源"
                       required="true" value="蜂优贷原创"/>
            </td>
        </tr>

        <tr>
            <th>
                是否显示在首页：
            </th>
            <td>
                <input id="isHot" name="isHot" class="mini-combobox" style="width: 200px;" textfield="text"
                       valuefield="id" required="true" allowinput="false" value="2"
                       data="[{id:'1',text:'是'},{id:'2',text:'否'}]"/>
            </td>

            <th>
            </th>
            <td>
            </td>
        </tr>
        <tr>
            <th style="width: 14%;" align="right">
                图片：
                <br >
                尺寸:200x112
            </th>
            <td style="width: 36%" colspan="3">
                <img id="imgPicUrl" name="imgPicUrl" alt="广告位图片" src="/assets/img/NullPhoto.jpg" style="height: 100px"/><span
                    style="color: Gray"></span>
                <input id="picUrl" name="picUrl" class="mini-hidden" value=""/><br/>
                <a class="mini-button" id="btnSingleUpFile" tooltip="图片上传">图片上传</a>
            </td>
        </tr>
        <tr>
            <th>
                内容：
            </th>
            <td style="width: 86%" colspan="3">
                <textarea id="content" name="content" style="width: 85%; height: 300px; visibility: hidden;"></textarea>
            </td>
        </tr>
        </tbody>
    </table>
    <div style="text-align: center; padding: 10px;">
        <a class="mini-button onOk()" iconcls="icon-save" onclick="onOk" style="width: 60px;
            margin-right: 20px;">确定</a> <a class="mini-button onCancel()" iconcls="icon-close"
                                           onclick="onCancel" style="width: 60px;">取消</a>
    </div>
    <script src="/assets/pagejs/info/news/newsedit.js" type="text/javascript"></script>
</form>
</body>
</html>

