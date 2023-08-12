<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	//session 기본 객체에 저장된 id라는 속성 이름에 대응한 값을 가져옴
	//object 타입으로 리턴함
	String user_id = (String)session.getAttribute("userID");//admin
	String user_pw = (String)session.getAttribute("userPW");//1234
	out.println("userId: "+user_id+"<br/>;");
	out.println("userPw"+user_pw);
	%>
	<p>
	id : <%= application.getAttribute("id") %>
	</p>

</body>
</html>