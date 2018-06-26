<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/24
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>${data.type==1?"热点":data.type==2?"理财":data.title}</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0, maximum-scale=2.0"/>
</head>
<body>
<div id="all" class="aboutus">
    <div id="content" style="padding-top: 1rem;margin-left: 0.8rem;margin-right: 0.8rem">
        <div style="margin-bottom: 0.5rem">
            <span style="font-size: 1.3rem;text-align:left;color: #333333;">${data.title}</span>
        </div>
        <div style="font-size: 0.5rem;color: #a3a3a3;">
            <span style="float:left;">${data.source}</span>
            <span style="margin-left: 0.5rem;"><fmt:formatDate value="${data.createTime }" pattern="yyyy-MM-dd"/></span>
            <span style="float:right;">${data.readCount}阅读</span>
        </div>
        <div class="about" style="padding-top: 0.9rem">
            <div>${data.content}</div>
        </div>
    </div>
</div>
</body>
</html>

