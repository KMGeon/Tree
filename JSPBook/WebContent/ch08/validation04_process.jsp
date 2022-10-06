<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3>입력 성공!</h3>
	<%
	//스크립틀릿
	//request : jsp와 톰켓과 서블릿에서 제공해주는 내장 객체
	request.setCharacterEncoding("UTF-8");
	String id=request.getParameter("id");
	String passwd = request.getParameter("passwd");
	%>
	
	<p>아이디:<%=id %> </p>
	<p>비밀번호:<%=passwd %> </p>
</body>
</html>