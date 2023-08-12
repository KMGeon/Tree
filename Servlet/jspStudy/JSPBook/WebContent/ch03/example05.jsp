<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="./Header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<!-- 옆 강의실에 수업이므로 박수는 select min(박수) from dual;로 하자 -->
	현재시간: <%=Calendar.getInstance().getTime() %>
	<jsp:include page="./Header.jsp">
		<jsp:param name="id" value="a001" />
	</jsp:include>
</body>
</html>