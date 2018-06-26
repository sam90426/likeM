<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>蜂贷超市-管理后台</title>
    <link rel="stylesheet" href="/assets/js/bootstrap3/css/bootstrap.css"/>
    <link rel="stylesheet" href="/assets/css/base.css"/>
    <link rel="stylesheet" href="/assets/css/layout.css?v=1.0.0"/>
    <link rel="stylesheet" href="/assets/css/module-models.css"/>
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
    <script src="/assets/js/jquery.cookie.js" type="text/javascript"></script>
    <script type="text/javascript">
        function updatepwd() {
            ShowCommonDialog("/account/pwdupdate", "账号管理 - 密码修改", 420, 240);
        }
    </script>
</head>
<body>
<div id="wrap">
    <div id="common-header">
        <div class="common-w1400 clearfix">
            <div id="web-logo">
                <img width="196" height="79" src="/assets/img/logo.png" alt="logo"/>
            </div>
            <div id="user-info">
                您好！<a href="###" class="mr-10">${username}
            </a>欢迎来到蜂贷超市<span>|</span><a href="/account/loginout">退出</a><span>|</span>
                <a href="###" onclick="updatepwd()">密码修改</a>
            </div>
            <div id="nav-top">
                <ul id="topMenu" class="clearfix"></ul>
            </div>
        </div>
    </div>
    <div id="common-body">
        <div class="common-w1400 clearfix">
            <div id="nav-left">
                <ul attr="nav">
                    <li class="item"><p class="item-name">供应商管理<b class="arrow right"></b><b class="arrow-inner right"></b></p>
                        <ul style="overflow: hidden;">
                            <li><a attr="LeftMenu" name="aLeftMenu6" href="/supply/supplylist" target="iframeRight" onclick="onLeftMenuStyle(6);"><b></b>供应商列表</a></li>
                            <li><a attr="LeftMenu" name="aLeftMenu9" href="/supply/supplylog" target="iframeRight" onclick="onLeftMenuStyle(9);"><b></b>供应商访问日志</a></li>
                        </ul>
                    </li>
                </ul>
                <ul attr="nav">
                    <li class="item"><p class="item-name">用户管理<b class="arrow right"></b><b class="arrow-inner right"></b></p>
                        <ul style="overflow: hidden;">
                            <li><a attr="LeftMenu" name="aLeftMenu1" href="/user/adminuserlist" target="iframeRight" onclick="onLeftMenuStyle(1);"><b></b>后台用户</a></li>
                            <li><a attr="LeftMenu" name="aLeftMenu2" href="/user/userlist" target="iframeRight" onclick="onLeftMenuStyle(2);"><b></b>APP用户</a></li>
                            <li><a attr="LeftMenu" name="aLeftMenu3" href="/channel/channelList" target="iframeRight" onclick="onLeftMenuStyle(3);"><b></b>渠道商</a></li>
                        </ul>
                    </li>
                </ul>
                <ul attr="nav">
                    <li class="item"><p class="item-name">广告管理<b class="arrow right"></b><b class="arrow-inner right"></b></p>
                        <ul style="overflow: hidden;">
                            <li><a attr="LeftMenu" name="aLeftMenu3" href="/info/ggdiclist" target="iframeRight" onclick="onLeftMenuStyle(3);"><b></b>Banner管理</a></li>
                            <li><a attr="LeftMenu" name="aLeftMenu4" href="/info/helpdiclist" target="iframeRight" onclick="onLeftMenuStyle(4);"><b></b>帮助中心</a></li>
                            <li><a attr="LeftMenu" name="aLeftMenu5" href="/sys/sysdiclist" target="iframeRight" onclick="onLeftMenuStyle(5);"><b></b>数据字典</a></li>
                        </ul>
                    </li>
                </ul>
                <ul attr="nav">
                    <li class="item"><p class="item-name">营销管理<b class="arrow right"></b><b class="arrow-inner right"></b></p>
                        <ul style="overflow: hidden;">
                            <li><a attr="LeftMenu" name="aLeftMenu6" href="/info/sendpush" target="iframeRight" onclick="onLeftMenuStyle(6);"><b></b>Push推送</a></li>
                        </ul>
                    </li>
                </ul>
                <ul attr="nav">
                    <li class="item"><p class="item-name">日志管理<b class="arrow right"></b><b class="arrow-inner right"></b></p>
                        <ul style="overflow: hidden;">
                            <li><a attr="LeftMenu" name="aLeftMenu7" href="/user/adminuserloginlog" target="iframeRight" onclick="onLeftMenuStyle(7);"><b></b>后台用户登录日志</a></li>
                            <li><a attr="LeftMenu" name="aLeftMenu8" href="/user/userloginlog" target="iframeRight" onclick="onLeftMenuStyle(8);"><b></b>APP用户登录日志</a></li>
                        </ul>
                    </li>
                </ul>
                <ul attr="nav">
                    <li class="item"><p class="item-name">资讯管理<b class="arrow right"></b><b class="arrow-inner right"></b></p>
                        <ul style="overflow: hidden;">
                            <li><a attr="LeftMenu" name="aLeftMenu9" href="/info/newslist" target="iframeRight" onclick="onLeftMenuStyle(9);"><b></b>资讯列表</a></li>
                        </ul>
                    </li>
                </ul>
                <ul attr="nav">
                    <li class="item"><p class="item-name">反馈管理<b class="arrow right"></b><b class="arrow-inner right"></b></p>
                        <ul style="overflow: hidden;">
                            <li><a attr="LeftMenu" name="aLeftMenu10" href="/info/suggestlist" target="iframeRight" onclick="onLeftMenuStyle(10);"><b></b>反馈列表</a></li>
                        </ul>
                    </li>
                </ul>
                <ul attr="nav">
                    <li class="item"><p class="item-name">用户邀请管理<b class="arrow right"></b><b class="arrow-inner right"></b></p>
                        <ul style="overflow: hidden;">
                            <li><a attr="LeftMenu" name="aLeftMenu11" href="/userinvite/invitationList" target="iframeRight" onclick="onLeftMenuStyle(11);"><b></b>邀请记录列表</a></li>
                            <li><a attr="LeftMenu" name="aLeftMenu12" href="/withdrawals/withdrawalList" target="iframeRight" onclick="onLeftMenuStyle(12);"><b></b>提现记录列表</a></li>
                        </ul>
                    </li>
                </ul>
                <ul attr="nav">
                    <li class="item"><p class="item-name">统计图表<b class="arrow right"></b><b class="arrow-inner right"></b></p>
                        <ul style="overflow: hidden;">
                            <li><a attr="LeftMenu" name="aLeftMenu13" href="/statistics/channelChart" target="iframeRight" onclick="onLeftMenuStyle(13);"><b></b>供应商访问统计</a></li>
                            <li><a attr="LeftMenu" name="aLeftMenu14" href="/statistics/sourceChart" target="iframeRight" onclick="onLeftMenuStyle(14);"><b></b>访问来源统计</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div id="common-ct">
                <iframe style="overflow: auto;" id="iframeRight" name="iframeRight" frameborder="0" width="100%" scrolling="yes" height="100%" src="/user/adminuserlist"></iframe>
            </div>
        </div>
    </div>
    <div id="common-footer">
        <div class="common-w1190">
            <div id="footer-top" class="clearfix"></div>
            <div id="footer-bottom"></div>
        </div>
    </div>
</div>
<script src="/assets/pagejs/mall/home.js?v=1.0.0" type="text/javascript"></script>
</body>
</html>
