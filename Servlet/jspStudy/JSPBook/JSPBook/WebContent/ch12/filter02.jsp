<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Filter</title>
</head>
<body>
	<!-- <url-pattern>/ch12/filter02_process.jsp</url-pattern> 요청 -->
	<!-- <filter-class>ch12.InitParamFilter</filter-class> 실행 -->
	<!-- filter02_process.jsp?id=admin&passwd=1234 -->
	<form action="filter02_process.jsp" method="post">
		<p>아이디 : <input type="text" name="id" /></p>
		<p>비밀번호 : <input type="password" name="passwd" /></p>
		<p><button type="submit">전송</button></p>
	</form>
</body>
</html>