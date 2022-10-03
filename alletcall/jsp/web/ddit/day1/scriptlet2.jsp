<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	//스크립틀릿
	//0~10까지의 짝수를 출력하도록 스크립틀릿 태그를 작성해보
	for (int i = 0; i < 11; i++) {
		if (i % 2 == 0) {
			//out내장객체
			//println()메소드 => 개행 잘 안됨
			out.println(i+"<br/>");
		}
	}
	%>

</body>
</html>