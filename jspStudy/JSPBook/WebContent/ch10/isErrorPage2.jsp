<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exception</title>
</head>
<body>
<!-- 에러가났을때 이 페이지가 정상적으로 동작하도록 인위적으로 상태코드를 조작한다 -->
<% response.setStatus(200); %>

<h3>오류 발생</h3>
<!-- 
web.xml 의 <error-code>, <exception-type> 
엘리먼트에 설정된 오류의 <location> 지정 서블릿/JSP 가 수행될 때 발생된 
오류에 대한 정보가 HttpServletRequest 객체 통해 전달됨.
  - javax.servlet.error.status_code : 오류 코드
  - javax.servlet.error.exception_type : 예외 타입 정보 (Class 형 객체)
  - javax.servlet.error.message : 예외 메시지
  - javax.servlet.error.exception : 발생된 예외 객체
  - javax.servlet.error.request_uri : 오류 발생 파일의 URI
-->
<%
HttpServletRequest request1;
HttpSession session1 = request.getSession();
%>
<table border="1">
	<tr>
		<td width="100px">Error :</td>
		<!-- 예외 유형 -->
		<td> <%=request.getAttribute("javax.servlet.error.exception") %> : 오류발생!! </td>
		<!-- <td width="400px"> <%=exception.getClass().getName() %> : 오류발생!! </td> -->
	</tr>
	<tr>
		<td>URI :</td>
		<!-- 현재 페이지의 URI 주소 -->
		<td> <%= request.getAttribute("javax.servlet.error.request_uri") %> </td>
		<!-- <td><%=request.getRequestURI() %> </td> --> 
	</tr>
	<tr>
		<td>Status Code :</td>
		<!-- 상태코드 500 => 개발자가 오류를 냈을 때 나는 상태 코드 -->
		<td> <%=request.getAttribute("javax.servlet.error.status_code") %></td>
	</tr>
</table>

</body>
</html>