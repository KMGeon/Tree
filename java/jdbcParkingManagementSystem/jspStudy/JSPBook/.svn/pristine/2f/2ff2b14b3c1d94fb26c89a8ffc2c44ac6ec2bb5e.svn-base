<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<title>상품 등록</title>
<script type="text/javascript" src="/resources/js/validation.js"></script>
</head>
<body>
	<!-- ?language=ko / ?language=en-->
	<!-- http://localhost/ch06/addProduct.jsp?language=en -->
	<fmt:setLocale value="${param.language}" />
	<!-- 
	현재 로케일에 따라 리소스번들이 위치한 /src/bundle/ 패키지(디렉터리)에
	message.properties를 가져오도록 basename 속성을 작성함
	-->
	<fmt:bundle basename="bundle.message" >
	<!-- 머리글에 해당하는 menu.jsp 파일의 내용을 포함하도록
	include 액션 태그를 작성 -->
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<!-- 내용 드루와 -->
		<div class="container">
			<h1 class="display-3"><fmt:message key="title" /></h1>
		</div>
	</div>
	<!-- ============= 상품 등록 시작 ============== -->
	<div class="container">
		<div class="text-right">
			<a href="?language=ko">Korea</a>|<a href="?language=en">English</a>
		</div>
		<form name="newProduct" action="processAddProduct.jsp" 
			class="form-horizontal" method="post" enctype="multipart/form-data">
			<div class="form-group row">
				<label class="col-sm-2"><fmt:message key="productId" /></label>
				<div class="col-sm-3">
					<input type="text" id="productId" name="productId" class="form-control" />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2"><fmt:message key="pname" /></label>
				<div class="col-sm-3">
					<input type="text" id="pname" name="pname" class="form-control" />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2"><fmt:message key="unitPrice" /></label>
				<div class="col-sm-3">
					<input type="text" id="unitPrice" name="unitPrice" class="form-control" />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2"><fmt:message key="description" /></label>
				<div class="col-sm-3">
					<textarea id="description" name="description" rows="2" cols="50"
					 class="form-control"></textarea>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2"><fmt:message key="manufacturer" /></label>
				<div class="col-sm-3">
					<input type="text" id="manufacturer" name="manufacturer" class="form-control" />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2"><fmt:message key="category" /></label>
				<div class="col-sm-3">
					<input type="text" id="category" name="category" class="form-control" />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2"><fmt:message key="unitsInStock" /></label>
				<div class="col-sm-3">
					<input type="text" id="unitsInStock" name="unitsInStock" class="form-control" />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2"><fmt:message key="condition" /></label>
				<div class="col-sm-5">
					<input type="radio" id="condition1" name="condition" value="New" /><fmt:message key="condition_New" />
					<input type="radio" id="condition2" name="condition" value="Old" /><fmt:message key="condition_Old" />
					<input type="radio" id="condition3" name="condition" value="Refurbished" /><fmt:message key="condition_Refurbished" />
				</div>
			</div>
			<!-- ch07에서 추가됨(파일업로드) -->
			<div class="form-group row">
				<label class="col-sm-2"><fmt:message key="productImage" /></label>
				<div class="col-sm-5">
					<!-- 파일을 입력받을 수 있음 -->
					<input type="file" id="productImage" name="productImage" class="form-control" />
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="button" class="btn btn-primary" value="<fmt:message key='button' />"
						onclick="checkAddProduct()" />
					<a href="products.jsp" class="btn btn-warn"><fmt:message key="productList" /></a>
				</div>
			</div>
		</form>
	</div>	
	<!-- ============= 상품 등록 끝 ============== -->
	<jsp:include page="footer.jsp" />
	</fmt:bundle>
</body>
</html>






