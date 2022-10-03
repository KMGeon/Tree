<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	//스크립틀릿
	//선언문 태그와 달리 지역변수 a,b
	int a = 2;
	int b = 3;
	int sum = a + b;
	out.print("2+3=" + sum);
	%>
	
	<%
	//지역변수 a,b를 1씩 증가시킴
	a++;
	b++;
	sum=a+b;
	out.print("3+4="+sum);
	%>
</body>
</html>