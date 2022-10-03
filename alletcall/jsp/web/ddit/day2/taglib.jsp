<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>태그 라이브러리</title>
</head>
<body>

	<c:set var="str" value="런닝맨"></c:set>
	<c:out value="${str}">
	</c:out>
	<%
	List list = new ArrayList();
	list.add("유재석");
	list.add("김종국");
	list.add("하하");
	list.add("전소민");
	%>


	<%-- items에는 Collection만 들어갈 수 있다. --%>
	<c:forEach var="item" items="<%=list%>">
		<p>${item}</p>
	</c:forEach>

	<hr>
	<%-- 변수 k가 1~10까지 1씩 증가하도록 jstl 코어 태그 반복문 작성 --%>
	<c:forEach var="k" begin="1" end="10" step="1">
		<c:out value="${k}"></c:out>
	</c:forEach>

	<%-- 짝수만 출력 --%>
	<c:forEach var="i" begin="1" end="10" step="1">
		<c:if test="${i%2==0}">
			<div>${i}</div>
		</c:if>
	</c:forEach>

	<!--choose-->
	<%-- scope(영역) : page : jsp페이지 내에서만 공유
	  				  request : 같은 요청 내에서만 공유
	  				  session : 같은 웹브라우저 내에서만 공유
	  				  application : 다른 웹브라우저에서도 서로 공유
	  				  거의 기본은 page
	  				   --%>
	<c:set var="money" value="5000" scope="page">
		<p>내가 가진 돈은 ${money}입니다.</p>
		<c:choose>
			<c:when test="${ money<=0}">무일푼</c:when>
			<c:when test="${ money<5000}">커피 한 잔의 여유 가능</c:when>
			<c:otherwise>밥먹자</c:otherwise>
		</c:choose>
	</c:set>

</body>
</html>