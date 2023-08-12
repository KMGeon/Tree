<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//web.xml에서 분 단위로 유효 시간을 설정(기본 30분)
	//반면, 초 단위로 유효 시간을 설정함(60 => 1분)
// 	session.setMaxInactiveInterval(60);
%>
<!DOCTYPE html>
<html>
<head>
<title>Session</title>
</head>
<body>
	<%
	//long 타입의 시간 값을 저장
	Date time = new Date();
	//Date 객체의 시간 값을 지정한 양식으로 출력하기 위함
	DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	%>
	<p>세션id : <%=session.getId()%></p>
	<%
		//session.getCreationTime() : 세션의 생성 시간
		//Date 객체인 time에 저장
		time.setTime(session.getCreationTime());
	%>
	<p>세션생성시간 :<%=formatter.format(time)%></p>
	<%
		//session.getLastAccessedTime() : 세션의 마지막 접근 시간
		time.setTime(session.getLastAccessedTime());
	%>
	<p>최근접근시간 : <%=formatter.format(time)%></p>
	<a href="closeSession.jsp">기존 세션 제거</a>
</body>
</html>








