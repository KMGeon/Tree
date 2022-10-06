<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Scripting Tag</title>
</head>
<body>
	<%//스크립틀릿 태그
		int a = 10;
		int b = 20;
		int c = 30;
	%>
	<!-- 스크립틀릿 태그에 선언한 변수 a(10), b(20), c(30)
	의 값을 출력하도록 표현문 태그를 작성함 -->
	<%=a + b + c%>
</body>
</html>




