<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>供应商列表</title>
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
</head>
<body>
<div style="width: 100%;">
    <div class="mini-toolbar" style="border-bottom: 0; padding: 0px;">
        <table style="width: 100%;">
            <tbody>
            <tr>
                <td>
                    <a class="mini-button add()" iconcls="icon-add" onclick="add()">增加</a>
                    <a class="mini-button edit()" iconcls="icon-edit" onclick="edit()">编辑</a>
                    <a class="mini-button edit()" iconcls="icon-edit" onclick="remove()">删除</a>
                </td>
                <td style="text-align: right;">
                    <input id="supplyType" name="supplyType" class="mini-combobox" style="width: 150px;" textfield="text" valuefield="id" allowinput="false" data="[{id:'1',text:'极速贷'},{id:'2',text:'工薪贷'},{id:'3',text:'学生贷'}]"  emptytext="请选择供应商类型"/>
                    <input id="supplyName" name="supplyName" class="mini-textbox" emptytext="请输入供应商名称" style="width: 150px;"/>
                    <a class="mini-button search()" onclick="search()">查询</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div id="datagrid1" class="mini-datagrid" style="width: 100%; height: 420px;" allowresize="true"
     url="/supply/getSupplyList" multiselect="true" pagesize="25" onrowdblclick="edit()">
    <div property="columns">
        <div width="20" type="checkcolumn">
        </div>
        <div field="id" width="30" headeralign="center" align="center" allowsort="true">
            编号
        </div>
        <div field="supplyName" width="60" headeralign="center">
            供应商名称
        </div>
        <div field="supplyType" width="60" headeralign="center" align="center" renderer="onSupplyTypeRender">
            供应商类型
        </div>
        <div field="linkUrl" width="65" headeralign="center" renderer="onLinkUrlRender">
            链接地址
        </div>
        <div field="intro" width="60" headeralign="center">
            简介
        </div>
        <div field="minMoney" width="40" headeralign="center" align="center" allowsort="true">
            最小金额
        </div>
        <div field="maxMoney" width="40" headeralign="center" align="center" allowsort="true">
            最大金额
        </div>
        <div field="rate" width="40" headeralign="center" align="center" allowsort="true">
            利率
        </div>
        <div field="rateUnit" width="40" headeralign="center" align="center" allowsort="true" renderer="onRateUnitRender">
            利率单位
        </div>
        <div field="state" width="40" headeralign="center" align="center" allowsort="true" renderer="onStateRender">
            状态
        </div>
        <div field="orderIndex" width="40" headeralign="center" align="center" allowsort="true">
            排序
        </div>
        <div field="hits" width="40" headeralign="center" align="center" allowsort="true">
            点击率
        </div>
        <div field="recommend" width="40" headeralign="center" align="center" allowsort="true" renderer="onRecommend">
            推荐
        </div>
        <div field="createTime" width="75" headeralign="center" align="center" dateformat="yyyy-MM-dd HH:mm"
             allowsort="true">
            创建时间
        </div>
    </div>
</div>
<script src="/assets/pagejs/supply/supplyList.js?v=1.4.1" type="text/javascript"></script>
</body>
</html>
