<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- j_security_check : 우리는 목적지가 있음. 폼 기반 인증 처리 action 속성 값
	폼 기반 인증(로그인) 처리 action 속성값
	로그인 인증
	권한이 있는가 -->
	<form action="j_security_check" name="loginForm" method="post">
		<p>
			ID: <input type="text" name="j_username">
		</p>

		<p>
			비밀번호: <input type="password" name="j_password">
		</p>

		<p>
			<input type="submit" value="로그인">
		</p>
	</form>
</body>
</html>