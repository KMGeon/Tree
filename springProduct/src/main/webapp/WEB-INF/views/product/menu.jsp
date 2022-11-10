<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<nav class="navbar navbar-expand navbar-dark bg-dark">
	<!-- 내용이 들어간다. -->
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="products">Home</a>
		</div>
		<div style="float:right;">
			<!-- 인증된 사용자인 경우 true. 로그인 했을 때에만 로그아웃 버튼이 보임 -->
			<sec:authorize access="isAuthenticated()">
				<form action="/logout" method="post">
					<button type="submit" class="btn btn-primary" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">
					  로그아웃
					</button>
					<sec:csrfInput/>
				</form>
			</sec:authorize>
		</div>
	</div>
</nav>








