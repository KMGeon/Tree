<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=session.getId()%>
	<%
	//세션의 속성 명
	String name;
	//세션의 속성에 setting된 값(value)
	String value;
	//
	Enumeration en = session.getAttributeNames();
	int i = 0;
	//다음 요소가 없을때까지 반복
	while (en.hasMoreElements()) {
		i++;
		name = en.nextElement().toString();
		value = session.getAttribute(name).toString();
		out.println("설정된 세션의 속성이름 : [" + i + "]: " + name + "</br>");
		out.println("설정된 세션의 속성 값 : [" + i + "]: " + value + "</br>");
	}
	%>
</body>
</html>