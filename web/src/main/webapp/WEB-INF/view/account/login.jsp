<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/12
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <!--jQuery js-->
    <script src="/assets/js/ui/jquery.min.js" type="text/javascript"></script>
    <!--MiniUI-->
    <link href="/assets/js/ui/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css"/>
    <script src="/assets/js/ui/miniui/miniui.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/assets/js/bootstrap3/css/bootstrap.css"/>
    <link rel="stylesheet" href="/assets/css/base.css"/>
    <link rel="stylesheet" href="/assets/css/layout.css?v=1.0.0"/>
    <link rel="stylesheet" href="/assets/js/validator/jquery.validator.css"/>
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
    <script type="text/javascript" src="/assets/js/extend/jquery.placeholder.js"></script>
    <script type="text/javascript" src="/assets/js/validator/jquery.validator.js"></script>
</head>
<body>
<div id="wrap">
    <img id="allImage" src="/assets/img/logbk.jpg"/>
    <div id="allImage-mask">
    </div>
    <form id="login">
        <img src="/assets/img/logo2.png" width="427" height="100"/>
        <div class="phone input">
            <i class="user-icon"></i>
            <input type="text" id="UserName" name="UserName" placeholder="用户名" autocomplete="off"/>
        </div>
        <div class="password input">
            <i class="key-icon"></i>
            <input type="password" id="PassWord" name="PassWord" placeholder="密码/动态密码" autocomplete="off"/>
        </div>
        <div class="submit">
            <button type="submit" id="btnSubmit" class="btn btn-login">
                登&nbsp;&nbsp;录
            </button>
        </div>
        <p class="copyright">
            © 2014-2016 杭州途购网络科技有限公司 版权所有 浙ICP备14008082号</p>
    </form>
</div>
<script src="/assets/pagejs/account/login.js" type="text/javascript"></script>
</body>
</html>
