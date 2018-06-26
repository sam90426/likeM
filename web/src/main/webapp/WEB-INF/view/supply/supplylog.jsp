<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/30
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>供应商访问日志列表</title>
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
</head>
<body onload='onLoadLog(${specificSource})'>
<div style="width: 100%;">
    <div class="mini-toolbar" style="border-bottom: 0; padding: 0px;">
        <table style="width: 100%;">
            <tbody>
            <tr>
                <td style="text-align: right;">
                    <span style="font-size: 9pt;">具体访问来源：</span><input id="specificSource" name="specificSource" class="mini-combobox" style="width: 150px;"
                         textfield="text" valuefield="id" allowinput="false" value="0" data='${specificSource}'/>
                    <span style="font-size: 9pt;">创建时间：</span><input id="startTime" class="mini-datepicker" style="width:150px;" format="yyyy-MM-dd" emptytext="请输入起始时间" ondrawdate="onDrawDate"  onvaluechanged="onValueChanged"/> -
                    <input id="endTime" class="mini-datepicker" style="width:150px;" format="yyyy-MM-dd" emptytext="请输入结束时间" ondrawdate="onDrawDate"/>
                    <input id="SearchType" name="SearchType" class="mini-combobox" style="width: 90px;" textfield="text" valuefield="id"
                           allowinput="false" value="1"
                           data="[{id:'1',text:'登录名'},{id:'2',text:'姓名'},{id:'3',text:'供应商名称'}]"/>
                    <input id="key" name="key" class="mini-textbox" emptytext="请输入登录名" style="width: 100px;"/>
                    <a class="mini-button search()" onclick="search()">查询</a>
                    <a class="mini-button search()" onclick="resetsearch()">重置</a>
                    <a class="mini-button userExport()" iconCls="icon-print" onclick="userExport()">导出</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div id="datagrid1" class="mini-datagrid" style="width: 100%; height: 420px;" allowresize="true"
     url="/supply/supplylogp" multiselect="true" pagesize="25">
    <div property="columns">
        <div width="20" type="checkcolumn">
        </div>
        <div field="id" width="30" headeralign="center" align="center" allowsort="true">
            编号
        </div>
        <div field="userId" width="40" headeralign="center" align="center">
            用户编号
        </div>
        <div field="userName" width="40" headeralign="center" align="center">
            登录账号
        </div>
        <div field="realName" width="40" headeralign="center" align="center">
            联系人
        </div>
        <div field="supplyId" width="40" headeralign="center" align="center">
            供应商ID
        </div>
        <div field="supplyName" width="40" headeralign="center" align="center">
            供应商名称
        </div>
        <div field="source" width="40" headeralign="center" align="center" renderer="onSource">
            来源
        </div>
        <div field="specificSource" width="60" headeralign="center" align="center" renderer="onSpecificSource">
            具体访问来源
        </div>
        <div field="createTime" width="70" headeralign="center" align="center" dateformat="yyyy-MM-dd HH:mm"
             allowsort="true">
            创建时间
        </div>
    </div>
</div>
<script src="/assets/pagejs/supply/supplyloglist.js?v=1.0.1" type="text/javascript"></script>
</body>
</html>

