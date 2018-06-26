<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>渠道量统计</title>
    <link rel="stylesheet" href="/assets/js/bootstrap3/css/bootstrap.css"/>
    <link rel="stylesheet" href="/assets/css/base.css"/>
    <link rel="stylesheet" href="/assets/css/layout.css?v=1.0.0"/>
    <link rel="stylesheet" href="/assets/css/module-models.css"/>
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
    <script src="/assets/js/jquery.cookie.js" type="text/javascript"></script>
</head>
<body>
<div style="width: 100%;">
    <div class="mini-toolbar" style="border-bottom: 0; padding: 0px;">
        <table style="width: 100%;">
            <tbody>
            <tr>
                <td>
                    <%--<a class="mini-button look()" iconcls="icon-search" onclick="look()">查看</a>--%>
                    <%--<a class="mini-button edit()" iconcls="icon-edit" onclick="remove()">删除</a>--%>
                </td>
                <td style="text-align: right;">
                    查询时间：
                    <input id="startTime" class="mini-datepicker"
                           showClearButton="false"/>
                    -
                    <input id="endTime" class="mini-datepicker"
                           showClearButton="false"/>
                    <a class="mini-button search()" onclick="search()">查询</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div id="main" style="margin-top:5%;width: 100%;height:400px;"></div>
<script src="https://gw.alipayobjects.com/os/antv/assets/g2/3.0.4-beta.2/g2.min.js"></script>
<script src="/assets/js/momentjs/moment.min.js" type="text/javascript"></script>
<script src="/assets/pagejs/statistics/sourceChart.js?v=1.0" type="text/javascript"></script>
</body>
</html>
