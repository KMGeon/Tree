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
<title>Insert title here</title>
<%
session.setMaxInactiveInterval(60);

%>
</head>
<body>

<%
	Calendar claendar = Calendar.getInstance();
%>
<%
	Date time=new Date();
	DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
%>
<div>세션id : <%= session.getId() %></div>
<%
	time.setTime(session.getCreationTime());
%>
<p>세션 생성 시간 : <%= formatter.format(time) %> </p>
<%
	//session.getLasetccessedTime() : 세션의 마지막 접근 시간
	time.setTime(session.getLastAccessedTime());
%>
<p> 최근접근시간 : <%= formatter.format(time) %> </p>

<a href="closeSession.jsp">fds</a>
</body>
</html>