function CheckAddMem(){
	let memHp = document.getElementById("memHp");
	let memName = document.getElementById("memName");
	
	if(memName.value==""){
		alert("회원명은 필수입니다.");
		return false;
	}
	
	//휴대폰번호 체크
	//01로 시작하고 (0,1,6,7,8,9 중 하나의 문자) 
	//-은 있을수도 없을수도 (숫자만 들어가면서 3~4 길이의 문자) 
	//- 은 있을수도 없을수도 (숫자만 들어가면서 4 길이의 문자) 
	if(!check(/^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/,
		memHp,"휴대폰 번호를 확인해주세요")){
		return false;	//함수 멈춰
	}
	
	//regExp : 정규식 / e : 대상객체 / msg : 메시지
	function check(regExp, e, msg){
		//test() : 맞니?틀리니? / exec() : 찾자
		//i) e.value => P1234
		//ii) e.value => S1234
		//iii) e.value => 1200000.35(o)
		//iv) e.value => 1200000.357(x)
		if(regExp.test(e.value)){
			return true;
		}
		alert(msg);
		e.select();	//글자선택
		e.focus();	//커서위치
		return false; //check()를 호출한곳으로 false를 리턴
	}
	//<form name="frm" ...
	document.frm.submit();
}









