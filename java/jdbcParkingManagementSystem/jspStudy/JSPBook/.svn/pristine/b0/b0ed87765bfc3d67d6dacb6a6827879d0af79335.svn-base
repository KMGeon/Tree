<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>상품 아이디 오류</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css" />
</head>
<body>
<!-- 상품이 없을 때 보여줄 오류 페이지 -->
	<!-- 머리글에 해당하는 menu.jsp 파일의 내용을 포함하도록
	include 액션 태그를 작성 -->
	<jsp:include page="menu.jsp" />
	
	<div class="jumbotron">
		<!-- 내용 드루와 -->
		<div class="container">
			<h2 class="alert alert-danger">해당 상품이 존재하지 않습니다.</h1>
		</div>
	</div>
	<div class="container">
		<!-- http://localhost/ch06/product.jsp?id=P1236 -->
		<p><%=request.getRequestURL()%>?<%=request.getQueryString()%></p>
		<p><a href="products.jsp" class="btn btn-secondary">상품 목록&raquo;</a></p>
	</div>
	
	<jsp:include page="footer.jsp" />
</body>
</html>



