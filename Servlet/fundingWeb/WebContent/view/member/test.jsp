<%@page import="kr.or.funding.member.VO.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%  MemberVO memVo = (MemberVO) request.getAttribute("memList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<script>
		console.log("1234");
		console.log(<%= memVo %>);
	</script>
</body>
</html>