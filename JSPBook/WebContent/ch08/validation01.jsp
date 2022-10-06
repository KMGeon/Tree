<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		function CheckForm() {
			let id = document.loginForm.id.value;
			let passwd = document.loginForm.passwd.value;

			console.log("id:"+id);
			console.log("pw:"+passwd)
		}
	</script>
	<form action="" name="loginForm">
		<p>
			아이디: <input type="text" name="id">
		</p>
		<p>
			비밀번호: <input type="text" name="passwd">
		</p>
		<p>
			<!--checkForm() : 핸들러 함수 -->
			<input type="button" name="전송" onclick="CheckForm()" />
		</p>
	</form>
</body>
</html>