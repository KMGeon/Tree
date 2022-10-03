<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립트 태그</title>
</head>
<body>
	<%!
	//변수 data에 50을 할당 -> 선언문 태그
	int data =50;
	%>
	
	<%
	//스크립트릿 태그
	//out : jsp내장 객체(jsp에 내장된 객체)
	//변수 data는 선언문 태그에 선언된 전역변수 값 50에 할당됨
	out.println("Value of variable is"+data); 
	%>
	
</body>
</html>