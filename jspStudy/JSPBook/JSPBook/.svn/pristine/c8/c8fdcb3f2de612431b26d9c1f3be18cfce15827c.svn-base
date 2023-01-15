<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript">
	function checkLogin(){
		let form = document.loginForm;
		let regExpPass1 = /(\w)\1\1/;
		
		if(form.id.value == ""){
			alert("아이디를 입력해주세요.");
			return false;
		} else if(form.passwd1.value == ""){
			alert("비밀번호를 입력해주세요.");
			return false;
		} else if((form.passwd1.value).search(form.id.value)>-1){
			alert("비밀번호는 ID를 포함할 수 없습니다.");
			return false;
		} else if(!((form.passwd1.value).search(form.passwd2.value)>-1)){
			alert("비밀번호를 확인해주세요.");
			return false;
		// \d : 숫자를 찾음
		// \D : 숫자가 아닌 값을 찾음
		// \w : 알파벳 + 숫자 + _ 를 찾음
		// \W : 알파벳 + 숫자 + _ 를 제외한 모든 문자를 찾음
		} else if(regExpPass1.test(form.passwd1.value)){
			alert("영문, 숫자는 3자 이상 연속 입력할 수 없습니다.");
			return false;
		} 
		form.submit();
	}
</script>
</head>
<body>
	<form name="loginForm" method="post" action="validation2_process.jsp" >
		<p>아이디 : <input type="text" name="id" /></p>
		<p>비밀번호 : <input type="text" name="passwd1" /></p>
		<p>비밀번호 확인 : <input type="text" name="passwd2"/></p>
		<p><input type="button" value="전송" onclick="checkLogin()"/></p>
	</form>
</body>
</html>