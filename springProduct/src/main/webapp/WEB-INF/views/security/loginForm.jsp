<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
		<!-- 	속성의 값은 항상 /login으로 정해져 있음 filter여서 
		name속성의 값은 username , password로 정해져 있음-->
		<form action="/login" method="post">
			<div class="row">
				<div class="bd-example">
					<c:if test="${error!=null}">
						<div class="mb-3">
							<input type="text" class="form-control is-invalid"
								id="floatingInputInvalid" placeholder="${error}"
								value="${error}" readonly="readonly">
						</div>
					</c:if>
					<div class="mb-3">
						<label for="exampleFormControlInput1" class="form-label">아이디
						</label> <input type="text" class="form-control" id="username"
							name="username" placeholder="input Id">
					</div>

					<div class="mb-3">
						<label for="exampleFormControlInput1" class="form-label">비밀번호
						</label> <input type="text" class="form-control" id="password"
							name="password" placeholder="input Password">
					</div>
					<div class="mb-3">
						<input type="submit" value="로그인">
					</div>
				</div>

			</div>
			<sec:csrfInput />
		</form>
	</div>
	<!-- --------------------상품상세 끝-------------------- -->

	<jsp:include page="../product/footer.jsp" />
</body>
</html>






