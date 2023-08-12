<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<title>책 등록하기</title>
</head>
<body>
	<!--
	BookController로 부터 넘어오는 정보 2가지
	1)mav.addObject("data",data) => bookvo객체
	2)mav.addobject("bookId",data.getBookId());
	
	
	 
	jstl : 개발자가 자주 사용하는 패턴을 모아놓은 집합
	=> bookcontroller에서 보내준 테이터를 view에 표현하도록 도와줌
	
	get : 데이터를 변경하지 ㅇ낳을 때
	post: 데이터를 변경할 떼..
	 -->

	<h1>책 상세${value }</h1>
	<p>제목 :${data.title}</p>
	<p>카테고리 :${data.category }</p>
	<p>
		가격 :
		<fmt:formatNumber type="number" maxFractionDigits="3"
			value="${data.price}" />
	</p>
	<p>
		입력일 :
		<fmt:formatDate value="${data.insertDate}"
			pattern="yyyy-MM-dd HH:mm:ss" />
	</p>
	<p>
		<a href="/update?bookId=${bookId}">수정품</a>
	</p>

	<form action="/delete" method="post">
		<input type="hidden" name="bookId" value="${bookId}" /> 
		<input type="submit" value="삭제"> 
	</form>

	<p>
		<a href="/list">목록으로 이동</a>
	</p>


</body>
</html>




