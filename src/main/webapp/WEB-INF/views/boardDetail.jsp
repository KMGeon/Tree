<%--core 태그 임포트--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--formatting 태그 임포트--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--jsp 한글 깨짐 방지--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

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
    <table>
        <tr>
            <td>아이디</td>
            <td>${detail.id}</td>
        </tr>
        <tr>
            <td>제목</td>
            <td>${detail.title}</td>
        </tr>
        <tr>
            <td>내용</td>
            <td>${detail.content}</td>
        </tr>
        <tr>
            <td>작가</td>
            <td>${detail.content}</td>
        </tr>
        <tr>
            <td>조회수</td>
            <td>${detail.count}</td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <a href="update" class="btn btn-primary">수정화면</a>
                <a href="/delete/${detail.id}" class="btn btn-block">삭제</a>
                <a href="/" class="btn btn-danger">목록</a>
            </td>
        </tr>
    </table>
</body>
</html>