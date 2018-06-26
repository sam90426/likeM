<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户列表</title>
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
                    <a class="mini-button unlock()" iconcls="icon-unlock" onclick="unlock()">启用</a>
                    <a class="mini-button lock()" iconcls="icon-lock" onclick="lock()">停用</a>
                    <a class="mini-button lock()" iconcls="icon-remove" onclick="islock(30)">删除</a>
                </td>
                <td style="text-align: right;">
                    <input name="SearchType" class="mini-combobox" style="width: 100px;" textfield="text" valuefield="id"
                           allowinput="false" value="1"  onvaluechanged="onSearchType"
                           data="[{id:'1',text:'渠道商名称'},{id:'2',text:'渠道商代码'}]"/>
                    <input id="key" name="key" class="mini-textbox" emptytext="请输入渠道商名称" style="width: 150px;"/>
                    <a class="mini-button search()" onclick="search()">查询</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div id="datagrid1" class="mini-datagrid" style="width: 100%; height: 420px;" allowresize="true"
     url="/channel/selectList" multiselect="true" pagesize="25" onrowdblclick="edit()">
    <div property="columns">
        <div width="20" type="checkcolumn">
        </div>
        <div field="id" width="30" headeralign="center" align="center" allowsort="true">
            编号
        </div>
        <div field="code" width="60" headeralign="center">
            渠道代码
        </div>
        <div field="name" width="40" headeralign="center">
            渠道商名称
        </div>
        <div field="linker" width="40" headeralign="center">
             联系人
        </div>
        <div field="phone" width="40" headeralign="center">
            联系人手机号
        </div>
        <div field="state" width="65" headeralign="center" align="center" renderer="onState">
            状态
        </div>

        <div field="createTime" width="75" headeralign="center" align="center" dateformat="yyyy-MM-dd HH:mm"
             allowsort="true">
            创建时间
        </div>
    </div>
</div>
<script src="/assets/pagejs/channel/channelList.js?v=1.0.0" type="text/javascript"></script>
</body>
</html>
