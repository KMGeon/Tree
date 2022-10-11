<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Validation</title>
<script type="text/javascript">
	function checkLogin(){
		const form = document.loginForm; 
		let id = form.id.value;
		let passwd = form.passwd.value;
		if(id == ""){
			alert("아이디를 입력해주세요");
			return false; 
		}else if(passwd == ""){
			alert("비밀번호를 입력해주세요");
			form.passwd.focus(); 
			return false;
		}
		//passwd : a0011234
		//id : b001
		if(passwd.search(id) > -1 ){
			alert("비밀번호는 ID를 포함 할 수 없습니다.")
			return false;
		}
		form.submit()
	}
</script>
</head>
<body>
	<form action="validation01_process.jsp" name="loginForm" method="post">
		<p>아이디 : <input type="text" name="id"/></p>
		<p>비밀번호 : <input type="password" name="passwd"/></p>
		<!--  CheckForm() : 핸들러 함수  -->
		<p><input type="button" value="전송" onclick="checkLogin()"/></p>
	</form>
</body>
</html>





