<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<title>책 목록</title>
</head>
<body>
	<h1>책 목록</h1>
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>카테고리</th>
				<th>가격</th>
			</tr>
		</thead>

		<tbody>
			<!-- forEach태그
			배열(string , int) , collection 또는 map에 저장되어 있는 값을
			순서대로 처리할 때 사용함 
		 -->
			<!-- data : mav.addobject("data",list)
				List<bookvo> list...
				
				%var : 변수
				items : 아이템(배열 , collection , map)
				varstatus : 루프 정보를 담은 객체 활용
				-index : 루프 실행 시 현재 인덱스(0부터 시작)
				-count : 실행 회수 (1부터 시작.
			 -->

			<c:forEach var="row" items="${data}" varStatus="stat">
				<tr>
					<td>${row.bookId }</td>
					<td><a href="/detail?bookId=${row.bookId }">${row.title}</a></td>
					<td>${row.category}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${row.price}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<a href="/create">책 등록</a>
	</p>




</body>
</html>




