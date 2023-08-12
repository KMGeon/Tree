<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="exception_error.jsp" %>
<!-- 오류 발생 시  exception_error.jsp를 요청 -->
<!DOCTYPE html>
<html>
<head>
<title>Exception</title>
</head>
<body>
	<% //스크립틀릿
		//?num1=10&num2=2
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		
		int a = Integer.parseInt(num1);
		int b = Integer.parseInt(num2);
		int c = a / b;
		out.print(num1 + " / " + num2 + " = " + c);
	%>
</body>
</html>









