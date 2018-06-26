<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>常见问题</title>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
    <script src="/assets/js/flexable.js"></script>
    <link href="/assets/css/style1.css" rel="stylesheet"/>
    <script src="/assets/js/jquery.min.js"></script>
    <script type="text/javascript" src="/assets/js/spin.js"></script>
    <script type="text/javascript" src="/assets/js/common.js"></script>
    <script>
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?985acfc678db5c774efb3ed1a2235b53";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
</head>
<body>
<div class="repayment-description">
    <ul>
        <c:forEach var="message" items="${data}">
            <li>${message.title}
                <p>${message.content}</p>
            </li>
        </c:forEach>

    </ul>
</div>
<script>
    $(function () {
        $("li").each(function (k, v) {
            $(v).attr('id', k);
        })
        $("li").click(function () {
            $(this).toggleClass("active");
            var id = $(this).attr('id');
            var lis = $('ul li').filter(function (i, e) {
                return $(e).attr('id') != id;
            })
            lis.removeClass();

        });
    })
</script>
</body>
</html>
