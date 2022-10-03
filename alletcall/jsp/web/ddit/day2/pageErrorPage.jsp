<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page errorPage="errorPage.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<title>Directive Tag</title>
</head>
<body>
	<%
	String str = null;
	//오류 발생 null을 tostring() 불가능
	out.print(str.toString());
	%>

	<%= str %>
</body>
</html>