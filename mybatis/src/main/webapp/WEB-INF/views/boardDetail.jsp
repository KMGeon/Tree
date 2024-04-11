<%--core 태그 임포트--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--formatting 태그 임포트--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--jsp 한글 깨짐 방지--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<head>
    <jsp:include page="root.jsp"/>
</head>
<body>
    <table>
        <tr>
            <td>아이디</td>
        </tr>
        <tr>
            <td>제목</td>
        </tr>
        <tr>
            <td>내용</td>
        </tr>
        <tr>
            <td>작가</td>
        </tr>
        <tr>
            <td>조회수</td>
        </tr>
        <tbody></tbody>
    </table>
</body>
</html>