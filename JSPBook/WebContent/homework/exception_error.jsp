<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<title>Exception</title>
</head>
<body>
	<p>오류 발생 : <%=exception.getMessage()%></p>
	<!-- include => tiles로 대체(header, aside, bottom, body) -->
	<!-- include 디렉티브 : 요청 파라미터를 전달할 수 없음(정적) -->
	<%@ include file="exception.jsp" %>
	<!-- include 액션태그 : 요청 파라미터를 전달할 수 있음(동적) -->
	<jsp:include page="exception.jsp">
		<jsp:param value="id" name="admin"/>
		<jsp:param value="pwd" name="1234"/>
	</jsp:include>
</body>
</html>




