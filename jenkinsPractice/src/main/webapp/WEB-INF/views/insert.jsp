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
<form action="boardInsert" method="post">
    <table class="table">
        <tr>
            <td>제목</td>
            <td>
                <label>
                    <input type="text" name="title" class="form-control"/>
                </label>
            </td>
        </tr>

        <tr>
            <td>내용</td>
            <td>
                <label>
                    <textarea rows="7" class="form-control" name="content"></textarea>
                </label>
            </td>
        </tr>

        <tr>
            <td>작성자</td>
            <td>
                <label>
                    <input type="text" name="writer" class="form-control"/>
                </label>
            </td>
        </tr>

        <tr>
            <td colspan="2" align="center">
                <button type="submit" class="btn btn-success btn-sm">등록</button>
                <button type="reset" class="btn btn-danger btn-sm">취소</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
