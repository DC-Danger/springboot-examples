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
    <title>书籍管理</title>
</head>

<body>
<div class="contentDiv">

    <h5>Spring Boot 2.x JSP集成</h5>

    <legend>
        <strong>书籍管理</strong>
    </legend>

    <form action="${ctx}/book/${action}" method="post" class="form-horizontal">

        <input type="hidden" name="id" value="${book.id}"/>

        <div class="form-group">
            <label for="book_name" class="col-sm-2 control-label">书名:</label>
            <div class="col-xs-4">
                <input type="text" class="form-control" id="book_name" name="name" value="${book.name}"/>
            </div>
        </div>

        <div class="form-group">
            <label for="book_author" class="col-sm-2 control-label">作者:</label>
            <div class="col-xs-4">
                <input type="text" class="form-control" id="book_author" name="author" value="${book.author}"/>
            </div>
        </div>

        <div class="form-group">
            <label for="book_introduction" class="col-sm-2 control-label">简介:</label>
            <div class="col-xs-4">
                <textarea class="form-control" id="book_introduction" rows="3" name="introduction" value="${book.introduction}"></textarea>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input class="btn btn-primary" type="submit" value="提交"/>&nbsp;&nbsp;
                <input class="btn" type="button" value="返回" onclick="history.back()"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>