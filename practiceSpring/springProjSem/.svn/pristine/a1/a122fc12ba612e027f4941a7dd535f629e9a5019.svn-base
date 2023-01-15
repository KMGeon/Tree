<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<title>책 등록하기</title>
<script type="text/javascript">
//jQuery 시작!
$(function(){
	$("button").on("click",function(){
		//BookController의 /list URI에 매핑된 메소드를 실행
		location.href="/list";
	});
});
</script>
</head>
<body>
<h1>책 등록</h1>
<!-- http://localhost/create?title=개똥이월드&category=소설&price=10000 -->
<form action="/create" method="post">
	<p>제목 : <input type="text" name="title" required="required" /></p>
	<p>카테고리 : <input type="text" name="category" required="required" /></p>
	<p>가격 : <input type="text" name="price" required="required" /></p>
	<p>
		<input type="submit" value="저장" />
		<button type="button">목록</button>
	</p>
</form>
</body>
</html>




