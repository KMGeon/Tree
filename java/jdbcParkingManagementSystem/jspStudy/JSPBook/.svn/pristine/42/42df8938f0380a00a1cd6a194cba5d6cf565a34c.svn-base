<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Filter</title>
</head>
<body>
	<!-- 필터를 통과함 -->
	<%
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	String message = "";
	
	if(id.equals("admin")&&passwd.equals("1234")){
		//세션객체의 userID라는 속성 명에 id 값을 매핑해줌
		session.setAttribute("userID", id);
		message = "로그인 성공";
	}else{
		message = "로그인 실패";
	}
	
	out.println(message);
	%>	
</body>
</html>



