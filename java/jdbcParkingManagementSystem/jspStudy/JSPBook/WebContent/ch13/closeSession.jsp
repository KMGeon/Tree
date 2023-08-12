<%@page import="java.util.Date"%>
<%@page import="java.sql.Time"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ses</title>
</head>
<body>
	<%
	// 세션을 제거하고 다음 요청에서 새로운 session객체를 만듬
	%>
	세션을 종료합니다.
	<a href="sessionInfo.jsp"></a>
	<%=session.invalidate()%>
</body>
</html>