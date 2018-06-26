<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>提现记录列表</title>
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
</head>
<body>
<div style="width: 100%;">
    <div class="mini-toolbar" style="border-bottom: 0; padding: 0px;">
        <table style="width: 100%;">
            <tbody>
            <tr>
                <td>
                    <a class="mini-button look()" iconcls="icon-search" onclick="look()">查看</a>
                    <%--<a class="mini-button edit()" iconcls="icon-edit" onclick="remove()">删除</a>--%>
                </td>
                <td style="text-align: right;">
                    <span style="font-size: 9pt;">状态:
                        <input id="state" name="Type" class="mini-combobox" style="width: 100px;"
                               textfield="text" valuefield="id" allowinput="false" value=""
                               data="[{id:'',text:'全部'},{id:'1',text:'提现中'},{id:'2',text:'提现成功'},{id:'3',text:'提现失败'}]"
                               emptytext="选择状态"/>
                    </span>
                    <span style="font-size: 9pt;">用户姓名:
                        <input id="userName" name="userName" class="mini-textbox" emptytext="请输入用户姓名" style="width: 150px;"/>
                    </span>
                    <a class="mini-button search()" onclick="search()">查询</a>
                    <a class="mini-button resetsearch()" onclick="resetsearch()">重置</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div id="datagrid1" class="mini-datagrid" style="width: 100%; height: 420px;" allowresize="true"
     url="/withdrawals/list" pagesize="25" onrowdblclick="look()">
    <div property="columns">
        <div width="20" type="checkcolumn">
        </div>
        <div field="id" width="30" headeralign="center" align="center" allowsort="true">
            编号
        </div>
        <%--<div field="userId" width="50" headeralign="center" align="center">
            用户编号
        </div>--%>
        <div field="userName" width="60" headeralign="center" align="center">
            用户姓名
        </div>
        <div field="amount" width="40" headeralign="center" align="center">
            提现金额
        </div>
        <div field="alipayAccount" width="60" headeralign="center" align="center">
            提现账号
        </div>
        <div field="createTime" width="50" headeralign="center" align="center" dateformat="yyyy-MM-dd HH:mm:ss">
            申请时间
        </div>
        <div field="payTime" width="50" headeralign="center" align="center" dateformat="yyyy-MM-dd HH:mm:ss">
            提现时间
        </div>
        <div field="failReason" width="60" headeralign="center" align="center">
            失败原因
        </div>
        <div field="state" width="50" headeralign="center" align="center" renderer="onStateRender">
            提现状态
        </div>
       <%-- <div field="sysAdminId" width="50" headeralign="center" align="center">
            操作人编号
        </div>--%>
        <div field="sysAdminName" width="50" headeralign="center" align="center">
            操作人名称
        </div>
    </div>
</div>
<script src="/assets/pagejs/invitation/withdrawals/withdrawalsList.js?v=1.0.2" type="text/javascript"></script>
</body>
</html>
