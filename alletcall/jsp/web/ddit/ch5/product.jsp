
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="ch04.vo.ProductVO"%>
<%@page import="java.util.List"%>
<%@page import="ch04.dao.ProductRepository"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<%
String id = request.getParameter("id");
ProductRepository productRepository = ProductRepository.getInstance();
//상세보기 =>1행을 리턴
ProductVO productVO = productRepository.getProductById(id);
%>
<c:set var="productVO" value="<%=productVO%>" scope="page" />
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>상품 상제 정보</title>
</head>
<body>
	<!-- 머리글에 해당하는 menu.jsp 파일의 내용을 포함하도록
   include 액션 태그를 작성 -->
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<!-- 내용이 들어감 -->
		<div class="container">
			<h1 class="display-3">상품 정보</h1>
		</div>
	</div>
	<!-- ==============상품 상세 시작 ============== -->
	<div class="container">
		<div class="row" align="center"></div>
		<div class="col-md-6">
			<h3>${productVO.pname}</h3>
			<p>${productVO.description}</p>
			<p>
				<b>상품 코드: </b><span class="badge badge-danger">${productVO.productId }</span>
			</p>
			<p>
				<b>제조사</b>: ${productVO.manufacturer}
			</p>
			<p>
				<b>분류</b>: ${productVO.category }
			</p>
			<p>
				<b>재고수</b>:${productVO.unitsInStock }
			</p>
			<h4>${productVO.unitPrice}</h4>
			<p>
				<a href="#" class="btn btn-info">상품주문&raquo;</a> <a
					href="../ch04/products.jsp" class="btn btn-secondary">상품목록&raquo;</a>

			</p>

		</div>
	</div>
	<!-- ==============상품 상세 끝 ============== -->
	<jsp:include page="footer.jsp" />
</body>
</html>