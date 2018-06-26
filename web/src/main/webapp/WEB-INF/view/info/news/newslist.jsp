<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>资讯列表</title>
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
                    状态：
                    <input id="State" name="Type" class="mini-combobox" style="width: 100px;" textfield="text" valuefield="id" allowinput="false" value="0" data="[{id:'0',text:'全部'},{id:'1',text:'正常'},{id:'2',text:'停用'}]"  emptytext="请选择状态"/>
                    类型：
                    <input id="Type" name="Type" class="mini-combobox" style="width: 100px;" textfield="text" valuefield="id" allowinput="false" value="0" data="[{id:'0',text:'全部'},{id:'1',text:'热点'},{id:'2',text:'理财'},{id:'3',text:'贷款'}]"  emptytext="请选择类型"/>
                    <a class="mini-button search()" onclick="search()">查询</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div id="datagrid1" class="mini-datagrid" style="width: 100%; height: 420px;" allowresize="true"
     url="/info/newslistp" multiselect="true" pagesize="25" onrowdblclick="edit()">
    <div property="columns">
        <div width="20" type="checkcolumn">
        </div>
        <div field="id" width="30" headeralign="center" align="center" allowsort="true">
            编号
        </div>
        <div field="title" width="80" headeralign="center">
            标题
        </div>
        <div field="adminRealName" width="40" headeralign="center" align="center">
            操作员姓名
        </div>
        <div field="type" width="40" headeralign="center" align="center" allowsort="true" renderer="onType">
            类型
        </div>
        <div field="state" width="40" headeralign="center" align="center" allowsort="true" renderer="onStateRender">
            状态
        </div>
        <div field="orderIndex" width="40" headeralign="center" align="center" allowsort="true">
            排序
        </div>
        <div field="readCount" width="40" headeralign="center" align="center" allowsort="true">
            阅读量
        </div>
        <div field="createTime" width="75" headeralign="center" align="center" dateformat="yyyy-MM-dd HH:mm"
             allowsort="true">
            创建时间
        </div>
    </div>
</div>
<script src="/assets/pagejs/info/news/newslist.js?v=1.0.1" type="text/javascript"></script>
</body>
</html>
