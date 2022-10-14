<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page import="ch04.vo.ProductVO"%>
<%@ page import="java.util.List"%>
<%@ page import="ch04.dao.ProductRepository"%>
<!DOCTYPE html>
<%//스크립틀릿 태그
	//기본적으로 3개의 상품이 들어 있는 객체 생성
	ProductRepository productDAO = ProductRepository.getInstance();
	//SELECT * FROM PRODUCT
	List<ProductVO> listOfProducts = productDAO.getAllProducts();
%>
<!-- 아스가르드 -> Earth로 소환 -->
<!-- scope : 영역 
	page(동일 jsp)(기본), request(동일 요청), session(동일 웹브라우저), application(모든 웹브라우저)
 -->
<c:set var="listOfProducts" value="<%=listOfProducts%>" scope="page" />
<html>
<head>
<link rel="stylesheet" 
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>상품 목록</title>
</head>
<body>
	<!-- 머리글에 해당하는 menu.jsp 파일의 내용을 포함하도록
	include 액션 태그를 작성 -->
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<!-- 내용 드루와 -->
		<div class="container">
			<h1 class="display-3">상품목록</h1>
		</div>
	</div>
	<!-- ============= 상품 목록 시작 ============== -->
	<div class="container">
		<div class="row" style="justify-content:right;margin:0 0 30px 0;">
			<a href="addProduct.jsp" class="btn btn-primary"
				>상품 추가</a>
		</div>
		<div class="row" align="center">
			<!-- List<ProductVO> -> 한 행을 꺼내오면 -> ProductVO -->
			<c:forEach var="productVO" items="${listOfProducts}">
				<div class="col-md-4">
					<img alt="${productVO.pname}" title="${productVO.pname}"
					 src="/resources/images/${productVO.filename}"
					 style="width:100%;">
					<h3>${productVO.pname}</h3>
					<p>${productVO.description}</p>
					<p>${productVO.unitPrice}원</p>
					<!-- 상품 아이디에 대한 상세 정보 페이지가 연결되도록 상세 정보 버튼 작성 -->
					<p><a href="product.jsp?id=${productVO.productId}"
						class="btn btn-secondary" role="button">상세 정보&raquo;</a></p>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- ============= 상품 목록 끝 ============== -->
	<jsp:include page="footer.jsp" />
</body>
</html>






