<%--
  Created by IntelliJ IDEA.
  User: 22033807
  Date: 2022/10/8
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>小说详情</title>
</head>
<body>
<c:forEach items="${bookList}" var="book">
    <h1>小说详情</h1>
    <div>小说名称:${book.id}</div>
    <div>小说简介:${book.desc}</div>
    <div>作者:${book.author}</div>
</c:forEach>

</body>
</html>
