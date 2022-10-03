<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립트 태그</title>
</head>
<body>
	<%!//선언문 태그
	//전역 메소드
	public String makeItLower(String arg){
		//tolowercase 소문자
		return arg.toLowerCase();
	}
	%>
	
	<%
	//스크립트릿 태그
	//
	out.print(makeItLower("Hello World"));
	%>
	
</body>
</html>