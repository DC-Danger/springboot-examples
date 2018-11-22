<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${ctx}/static/css/default.css" rel="stylesheet"/>
    <link rel="icon" href="${ctx}/static/images/favicon.ico" type="image/x-icon"/>
    <meta charset="UTF-8"/>
    <title>书籍列表</title>
</head>

<body>

<div class="contentDiv">

    <h5>Spring Boot 2.x JSP集成</h5>

    <table class="table table-hover table-condensed">
        <legend>
            <strong>书籍列表</strong>
        </legend>
        <thead>
        <tr>
            <th>书籍编号</th>
            <th>书名</th>
            <th>作者</th>
            <th>简介</th>
            <th>管理</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bookList}" var="book">
            <tr>
                <th scope="row">${book.id}</th>
                <td><a href="${ctx}/book/update/${book.id}">${book.name}</a></td>
                <td>${book.author}</td>
                <td>${book.introduction}</td>
                <td><a class="btn btn-danger" href="${ctx}/book/delete/${book.id}">删除</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div><a class="btn btn-primary" href="${ctx}/book/create" role="button">新增书籍</a></div>
</div>

</body>
</html>