<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="ch04.vo.ProductVO"%>
<%@ page import="java.util.List"%>
<%@ page import="ch04.dao.ProductRepository"%>
<%@ page errorPage="exceptionNoProductId.jsp"%>
<!-- 상품 아이디가 없을 때 exceptionNoProductId.jsp를 요청 -->
<!DOCTYPE html>
<%
//스크립틀릿 태그
// http://localhost/ch05/product.jsp?id=P1234
String id = request.getParameter("id").toString();

ProductRepository productRepository = ProductRepository.getInstance();
//상세보기  => 1행(ProductVO)을 리턴
//SELECT * FROM PRODUCT WHERE PRODUCT_ID = 'P1234'
ProductVO productVO = productRepository.getProductById(id);
%>
<c:set var="productVO" value="<%=productVO%>" scope="page" />
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>상품 상세 정보</title>
<script type="text/javascript">
//핸들러 함수(상품 주문 버튼 클릭 시 실행)
function addToCart() {
	console.log("${productVo.productId}");
	let result =  confirm("상품을 장바구니에 추가하시겠습니까?")
	
	if(result){
		console.log("추가")
		document.addForm.submit(); //add.Cart.jsp?id=123
	}else{
		console.log("실패")
		document.addForm.reset();
	}
}
</script>

</head>
<body>
	<!-- ?id=P1234&language=ko -->
	<fmt:setLocale value="${param.language}" />
	<fmt:bundle basename="bundle.message">
		<!-- 머리글에 해당하는 menu.jsp 파일의 내용을 포함하도록
	include 액션 태그를 작성 -->
		<jsp:include page="menu.jsp" />
		<div class="jumbotron">
			<!-- 내용 드루와 -->
			<div class="container">
				<h1 class="display-3">
					<fmt:message key="title" />
				</h1>
			</div>
		</div>
		<!-- ============= 상품 상세 시작 ============== -->
		<div class="container">
			<div class="text-right">
				<!-- param : ?id=P1234&language=en -->
				<a href="?id=${param.id}&language=ko">Korea</a>|<a
					href="?id=${param.id}&language=en">English</a>
			</div>
			<div class="row" align="center">
				<div class="col-md-6">
					<h3>${productVO.pname}</h3>
					<p>${productVO.description}</p>
					<p>
						<b><fmt:message key="productId" /> : </b> <span
							class="badge badge-danger">${productVO.productId}</span>
					</p>
					<p>
						<b><fmt:message key="manufacturer" /></b> :
						${productVO.manufacturer}
					</p>
					<p>
						<b><fmt:message key="category" /></b> : ${productVO.category}
					</p>
					<p>
						<b><fmt:message key="unitsInStock" /></b> :
						${productVO.unitsInStock}
					</p>
					<h4>${productVO.unitPrice}원</h4>
					<p>
					<form name="addForm" action="addCart.jsp?id=${productVO.productId}"
						method="post">
						<a href="#" class="btn btn-info"  onclick="addToCart()"><fmt:message
								key="productOrder" />&raquo;</a> <a
							href="cart.jsp" class="btn btn-warning">장바구니&raquo;</a> <a
							href="products.jsp" class="btn btn-secondary"><fmt:message
								key="productList" />&raquo;</a>
					</form>
					</p>
				</div>
			</div>
		</div>



		<!-- ============= 상품 상세 끝 ============== -->
		<jsp:include page="footer.jsp" />
	</fmt:bundle>
</body>
</html>






