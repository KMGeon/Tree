<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>YoilTellerMVC</title>
</head>
<body>
year = <%=request.getParameter("year")%>
<h1>${year}년 ${month}월 ${day}일은 ${yoil}요일입니다.</h1>
</body>
</html>
