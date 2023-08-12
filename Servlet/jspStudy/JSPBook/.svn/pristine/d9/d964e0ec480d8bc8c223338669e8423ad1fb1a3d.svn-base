<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Validation-RegExp</title>
<script type="text/javascript">
function checkMember(){
	let regExpId = /^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; //영문 대문자/소문자, 한글, 한글의 자음과 모음으로 시작
	let regExpName = /^[가-힣]*$/; //이름은 한글만 사용 가능. ^시작, $끝, *0이상반복
	let regExpPasswd = /^[0-9]*$/;	//비밀번호는 숫자만 사용 가능
	let regExpPhone = /^\d{3}-\d{3,4}-\d{4}$/; //전화번호 형태만 가능. \d{3}:숫자3자.\d{3,4}:숫자3이상4이하
	let regExpEmail 
	= /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; //이메일 형태만 가능. i:ignore(대소문자 구별 안함)
	//폼 object
	let form = document.Member;
	
	let id = form.id.value;	//아이디. 문자형
	let name = form.name.value;	//이름
	let passwd = form.passwd.value;	//비밀번호
	let phone = form.phone1.value + "-" + form.phone2.value + "-" + form.phone3.value; //연락처
	let email = form.email.value; //이메일
	
	//Regular Expression의 메소드 중 test() => boolean으로 리턴
	//아이디 체크. !regExpId.test(id) : false(통과 못함)
	if(!regExpId.test(id)){
		alert("아이디는 문자로 시작해주세요!");
		form.id.select();	//focus()
		return;	//return : J/S 종료
	}
	//이름 체크(한글만 사용 가능)
	if(!regExpName.test(name)){
		alert("이름은 한글만 입력해주세요!");	//smith(x),스미스(o)
		return;
	}
	//비밀번호 체크(비밀번호는 숫자만 사용 가능)
	if(!regExpPasswd.test(passwd)){
		alert("비밀번호는 숫자만 입력해주세요!");
		return;
	}
	//연락처 체크(전화번호 형태만 가능)
	if(!regExpPhone.test(phone)){
		alert("연락처 입력을 확인해주세요!");
		return;
	}
	//이메일 체크(이메일 형식만 가능)
	if(!regExpEmail.test(email)){
		alert("이메일 입력을 확인해주세요!");
		return;
	}
	
	form.submit();
}
</script>
</head>
<body>
	<h3>회원 가입</h3>
	<form name="Member" method="post" action="validation05_process.jsp">
		<p>아이디 : <input type="text" name="id" /></p>
		<p>비밀번호 : <input type="password" name="passwd" /></p>
		<p>이름 : <input type="text" name="name" /></p>
		<p>연락처 : 
			<select name="phone1">
				<option value="010">010</option>
				<option value="011">011</option>
				<option value="016">016</option>
				<option value="017">017</option>
				<option value="019">019</option>
			</select> -
			<input type="text" maxlength="4" size="4" name="phone2" /> -
			<input type="text" maxlength="4" size="4" name="phone3" />
		</p>
		<p>이메일 : <input type="text" name="email" /></p>
		<p><input type="button" value="가입하기" onclick="checkMember()" /></p>
	</form>
</body>
</html>