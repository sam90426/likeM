<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/27
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/assets/js/jquery-1.7.2.js"></script>
</head>
<body>
手机号：<input id="mobile">
内容：<input id="content">
<button onclick="send()">发送</button>
</body>

<script type="text/javascript">
    function send() {
        $.ajax({
            url: "/sys/sendmsgp",
            dataType: "json",
            type: "post",
            data: {
                mobile: $("#mobile").val(),
                content: $("#content").val()
            },
            success: function (data) {
                alert(data.message);
            },
            failure: function (resp, opts) {

            }
        });
    }
</script>
</html>
