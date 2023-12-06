<%--core 태그 임포트--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--formatting 태그 임포트--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--jsp 한글 깨짐 방지--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Spring MVC01</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>아이디</th>
        <th>제목</th>
        <th>내용</th>
        <th>작가</th>
        <th>조회수</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${list}" var="board">
        <tr>
            <td>${board.id}</td>
            <td>${board.title}</td>
            <td>${board.content}</td>
            <td>${board.writer}</td>
            <td>${board.count}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>