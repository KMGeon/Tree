<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<%
	
		Date today = new java.util.Date();
		
		out.println("<p>Today : " + today + "</p>");
	
	%>


	
</body>
</html>