<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>

</head>
<body>

<%
//Cookie user = new Cookie("id", "");
	session.invalidate();//세션의 모든 속성 제거
	session.setMaxInactiveInterval(0);
	response.sendRedirect("/Funding/ptimfor/ptimforlist.do");
%>
</body>
</html>