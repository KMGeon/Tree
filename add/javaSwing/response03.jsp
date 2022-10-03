<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체</title>
</head>
<body>

	<%
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=utf-8");
	
	//404코드 응답
	//response.sendError(404,"오류오류");
	%>
	<p>
		문자 인코딩:
		<%=response.getCharacterEncoding()%>
	</p>
	<p>
		콘텐츠 유형 :
		<%=response.getContentType()%>
	</p>


</body>
</html>