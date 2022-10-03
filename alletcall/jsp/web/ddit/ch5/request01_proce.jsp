<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	//process.jsp?name = 개똥이
	//name = 개똥이 =>req객체에 들어있다.
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
	String pw = request.getParameter("pw");
	%>

	<div>
		이름 :
		<%=name%>
		비밀번호
		<%= pw %>
	</div>
	<br>
	<div>
		요청 정보 길이 :
		<%=request.getContentLength()%>

		<br> 클라이언트 전송방식 :
		<%=request.getMethod()%>
	</div>
	<p>
		서버 이름 :
		<%=request.getServerName()%>
	</p>
	<p>
		서버 포트 :
		<%=request.getServerPort()%>
	</p>
	<%=request.getServletPath()%>
</body>
</html>