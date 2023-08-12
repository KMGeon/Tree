<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Session</title>
</head>
<body>
	<%//스크립틀릿
		//session기본 객체에 저장된 userID라는 속성 이름에 대응한 값을 가져옴
		//Object 타입으로 리턴됨
		String user_id = (String)session.getAttribute("userID");//admin
		String user_pw = (String)session.getAttribute("userPW");//1234
		
		out.println("userID : " + user_id + "<br />");
		out.println("userPW : " + user_pw);
	%>
<p>id : <%=application.getAttribute("id")%></p>
</body>
</html>