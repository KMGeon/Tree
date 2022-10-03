<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 
JSTL?
JSP 페이지에서 스크립트 요소로 인해 생기는 코드의 복잡함을 해결하기 위한 일종의 사용자 정의 태그 표준
JSP 페이지의 로직을 담당하는 부분인 IF FOR WHIELE DB처리 등과 관련된 코드를 JSTL로 대체하여 코드를 작성한다.
 
** 1)CORE태그 C:??**-> IF , FOR문과 같은 제어기능 , URL처리로 페이지 이동
 2)FORMATTING 태그(*) - > 숫자 , 날짜 , 시간을 제공
 3)SQL 태그 -> X
 4)FUNCTION 태그(*) ->문자열을 처리하는 함수 제공(substr)
 
 --%>

	<%-- action속성 생략시 현재 url로 요청 
method 속성 생략 시 기본은 get방식 --%>

	<form action="">
		<div>
			<input type="text" name="num">
			<button type="submit">짝홀판단</button>
		</div>
	</form>
	<%
	String num = request.getParameter("num");
	%>
	<%-- 아스가르드에 있던 해임달이 지구의 해임달로 온다. --%>
	<%-- jsp의 num이 jstl의 num으로 온다. --%>
	<c:set var="num" value="<%=num%>"></c:set>
	<div>${num}</div>

	<%-- ******************** param.이름****************************** --%>
	<c:set var="num2" value="${param.num}"></c:set>
	${num2}
	<%-- 짝수와 홀수를 판단 --%>
	<c:choose>
		<c:when test="${param.num%2==0}">짝수</c:when>
		<c:when test="${param.num%3==0}">홀수</c:when>
		<c:otherwise> 홀수</c:otherwise>
	</c:choose>


	<%-- 스크립트에서도 가능 --%>
	<script type="text/javascript">
		let num3 = "${param.num}";
		console.log(num3);
	</script>
</body>
</html>