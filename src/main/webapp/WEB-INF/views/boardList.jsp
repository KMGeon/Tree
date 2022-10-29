<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="/resources/css/menu.css">
</head>
<body>
<table border="1">
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>이름</th>
        <th>등록일</th>
        <th>조회수</th>
    </tr>
    <c:forEach var="board" items="${list}">
    <tr>
        <th>${board.bno}</th>
        <th>${board.title}</th>
        <th>${board.writer}</th>
        <th>${board.reg_date}</th>
        <th>${board.view_cnt}</th>
    </tr>
    </c:forEach>
</table>
<br>
<div>
    <c:if test="${ph.showPrec}">
        <a href="<c:url value='/board/list?page=${ph.beginPage-1}&pageSize=${ph.pageSize}'/> ">&lt;</a>
    </c:if>

    <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
       <a href="<c:url value='/board/list?page=${i}&pageSize=${ph.pageSize}'/> ">${i}</a>
    </c:forEach>

    <c:if test="${ph.showNext}">
        <a href="<c:url value='/board/list?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/> ">&lt;</a>
    </c:if>

</div>

</body>

</html>