<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<title>Exception</title>
</head>
<body>

<h3>오류 발생</h3>
<!-- 
  - javax.servlet.error.status_code : 오류 코드
  - javax.servlet.error.exception_type : 예외 타입 정보 (Class 형 객체)
  - javax.servlet.error.message : 예외 메시지
  - javax.servlet.error.exception : 발생된 예외 객체
  - javax.servlet.error.request_uri : 오류 발생 파일의 URI
-->
<%
ErrorData errorData = pageContext.getErrorData();
Throwable throwable = errorData.getThrowable();
StackTraceElement[] stackTrace = throwable.getStackTrace();
%>
<table border="1">
	<tr>
		<td width="100px">Error :</td>
		<!-- 예외 유형 -->
<%-- 		<td>${pageContext.exception}</td> --%>
		<td><%=exception%></td>
	</tr>
	<tr>
		<td>URI :</td>
		<!-- 오류가 발생한 URI 주소 -->
<%-- 		<td>${pageContext.errorData.requestURI}</td>  --%>
		<td><%=errorData.getRequestURI()%></td>
	</tr>
	<tr>
		<td>Status Code :</td>
		<!-- 오류 코드 -->
<%-- 		<td>${pageContext.errorData.statusCode}</td> --%>
		<td><%=errorData.getStatusCode() %></td>
	</tr>
	<tr>
		<td>Status Code :</td>
		<!-- 오류 메시지 -->
<%-- 		<td>${pageContext.throwable.message}</td> --%>
		<td><%=throwable.getMessage()%></td>
	</tr>
</table>
<%
    for ( int i = 0 ; i < stackTrace.length ; ++i ) {
       out.println(stackTrace[i]);
    }
%>
</body>
</html>