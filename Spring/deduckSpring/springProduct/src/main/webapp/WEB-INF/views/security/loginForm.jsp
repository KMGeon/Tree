<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css" />
<title>상품 상세 정보</title>

</head>
<body>
	<jsp:include page="../product/menu.jsp" />
	
	<!-- --------------------상품상세 시작-------------------- -->
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">로그인</h1>
		</div>
	</div>
	<div class="container">
	<!-- form 시작 
		action속성의 값은 /login으로 정해져 있음
		name 속성의 값은 username, password로 정해져 있음
	-->
	<form method="post" action="/login">
		<div class="row">
			<div class="bd-example">
				<c:if test="${error!=null}">		
					<div class="mb-3">
					  <input type="text" class="form-control is-invalid" 
					  	placeholder="${error}" value="${error}" readonly="true" />
					</div>
				</c:if>
				<c:if test="${logout!=null}">
					<div class="mb-3">
					  <input type="text" class="form-control is-invalid" 
					  	placeholder="${logout}" value="${logout}" readonly="true" />
					</div>
				</c:if>
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">아이디</label>
				  <input type="text" name="username" class="form-control" id="username" placeholder="아이디" />
				</div>
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">비밀번호</label>
				  <input type="password" name="password" class="form-control" id="password" placeholder="비밀번호" />
				</div>
				<div class="mb-3">
				  <div class="form-check">
				  <!-- 로그인 상태 유지 체크박스 -->
					  <input class="form-check-input" type="checkbox" name="remember-me" 
					  	id="remember-me" />
					  <label class="form-check-label" for="remember-me">
					    Remember Me
					  </label>
					</div>
				</div>
				<div class="mb-3">
				  <input type="submit" value="로그인" />
				</div>
			</div>
		</div>
		<sec:csrfInput/>
	</form>
	<!-- form 끝 -->
	</div>
	<!-- --------------------상품상세 끝-------------------- -->
	
	<jsp:include page="../product/footer.jsp" />
</body>
</html>






