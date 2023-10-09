<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>Cart</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
</head>
<body>

	<!--
       머리글에 해당하는 menu.jsp 파일의 내용을 포함하도록
       include 액션 태그를 작성
    -->
	<jsp:include page="./menu.jsp" />
	<div class="jumbotron">
		<!-- container : 이 안에 내용이 있다 -->
		<div class="container">
			<h1 class="display-3">주문 취소</h1>
		</div>
	</div>
	
	<div class="container">
	<h2 class="alert alert-danger">주문이 취소되었습니다.</h2>
	</div>
	
	<%
		session.invalidate();
	
	%>
	
	<div class="container">
	<p> <a href="products.jsp" class="btn btn-secondary">&laquo;상품 목록</a> </p>
	
	</div>
	<!-- --------------------- 주문 정보 끝 ------------------------- -->
	<!-- 끝맺음 -->
	<jsp:include page="./footer.jsp" />
</body>
</html>