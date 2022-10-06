<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Validation</title>
<script type="text/javascript">
	function checkLogin() {
		let form = document.loginForm;

		if (form.id.value.length<4||form.id.value.length>12) {
			alert("아이디를 4~12자 이내로 입력 가능합니다.");
			form.id.focus();
			return false;
		}

		if (form.passwd.value.length < 4) {
			alert("비밀번호를 4자 이상으로 입력해주세요");
			form.passwd.select(); //focus or select 사용
			return false;
		}
		form.submit();
	}
</script>
</head>
<body>
<!-- 		...jsp?id=001%비밀번호=1234 -->
	<form name="loginForm" action="validations03_process.jsp" method="post">
		<p>
			아이디 : <input type="text" name="id" />
		</p>
		<p>
			비밀번호 : <input type="password" name="passwd" />
		</p>
		<p>
			<input type="button" value="전송" onclick="checkLogin()" />
		</p>
	</form>
</body>
</html>