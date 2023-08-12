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
	String user_id= request.getParameter("id");
	String user_pw = request.getParameter("passwd");
	if(user_id.equals("admin")&&user_pw.equals("1234")){
		session.setAttribute("userID", user_id);
		session.setAttribute("userPW", user_pw);
		out.println("세션 설정이 성공했다");
		out.println(user_id+"환영합니다.");
		out.println("<a href='session02.jsp'>세션 환인</a>");
	}else{
		out.println("세션 설정이 실패했습니다.");
	}
	

%>
	<c:set var="id" value="<%=user_id %>" scope="applcaiotn"%> </c:set>
</body>
</html>


<!-- 
	
	
	
 -->