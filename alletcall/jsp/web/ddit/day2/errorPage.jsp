<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isErrorPage="true" %>
<!--이 jsp페이지는 error페이지라고 명시 isErrorPage = true  -->
<!--이렇게 설정하면 exception객체를 사용 가능하다.  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Directives Tag</title>
</head>
<body>
	<h4>errorPage디렉티브 태그</h4>
	에러가 발생했습니다.
	<%= exception.getMessage() %>
</body>
</html>