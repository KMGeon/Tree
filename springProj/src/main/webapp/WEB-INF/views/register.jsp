<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>요청 처리</title>
</head>
<body>
	<!-- 
	URL : /register01
	요청 파라미터 : {"userId":"hongkd","password":"1234","coin":"100"}
	 -->
	<form action="/register01" method="post">
		<p>coin : <input type="text" name="coin" value="100" /></p>
		<p>userId : <input type="text" name="userId" value="hongkd" /></p>
		<p>passwd : <input type="text" name="password" value="1234" /></p>
		<p><input type="submit" value="register01" /></p>
	</form>
</body>
</html>