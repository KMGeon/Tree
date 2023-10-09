<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- ***중요함 : 웹 브라우저의 ip 주소를 가져옴 --%>
	<p>
		클라이언트ip
		<%=request.getRemoteAddr()%></p>
	<p>
		요청 정보 길이<%=request.getContentLength()%></p>
	<p>
		요청 정보 인코딩<%=request.getCharacterEncoding()%></p>
	<p>
		요청 정보 콘텐츠 유형:<%=request.getContentType()%></p>
	<p>
		요청 정보 프로토콜<%=request.getProtocol()%></p>
	<p>
		요청 정보 전송방식<%=request.getMethod()%></p>
	<%-- 웹 브라우저에 요청한 url을 가져옴 --%>
	<p>
		요청 uri<%=request.getRequestURL()%></p>
	<%-- 컨텍스트 경로 중요 : 현재 jsp 웹 콘텍스트 경로를 가져옴 --%>
	<p>
		컨텍스트 경로<%=request.getContextPath()%></p>
	<p>
		서버 이름<%=request.getServerName()%></p>
	<p>
		서버 포트:<%=request.getServerPort()%></p>
	<%-- 웹 브라우저의 전체 요청 파라미터 문자열(쿼리문)을 가져옴 --%>
	<p>
		쿼리문:<%=request.getQueryString()%></p>

</body>
</html>