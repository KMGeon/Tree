<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Filter</title>
</head>
<body>
	<!-- <url-pattern>/ch12/filter01_process.jsp</url-pattern> -->
	<!-- filter01_process.jsp를 요청하면 AuthenFilter가 작동 -->
	<form action="filter01_process.jsp">
		<!-- String name = request.getParameter("name"); -->
		<p>이름 : <input type="text" name="name" /></p>
		<p><input type="submit" value="전송" /></p>
	</form>
</body>
</html>


