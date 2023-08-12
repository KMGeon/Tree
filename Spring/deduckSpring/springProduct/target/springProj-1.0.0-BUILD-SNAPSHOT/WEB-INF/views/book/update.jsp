<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<title>책 등록하기</title>
<script type="text/javascript">
	//jQuery 시작!
	$(function() {
		$("#btnCancel").on("click", function() {
			//el 태그 데이터를 js변수에 저장
			let bookId = "${param.bookId}";
			location.href = "/detail?bookId=" + bookId;
			//param => data
		});
	});

	$(function() {
		$("#btnList").on("click", function() {
			//el 태그 데이터를 js변수에 저장
			location.href = "/list";
			//param => data
		});
	});
</script>
</head>
<body>
	<h1>책 수정</h1>
	<!-- bookVO에서 넘어온 데이터 m.addattribute("data",data) -->

	<!-- 여기서 업데이트는 기본키가 있어야 된다 그러면 히든으로 하며노딤 -->
	<form action="/update" method="post">
		<input type="hidden" name="bookId" value="${data.bookId }">
		<p>
			제목 : <input type="text" name="title" value="${data.title }"
				required="required" />
		</p>
		<p>
			카테고리 : <input type="text" name="category" value="${data.category }"
				required="required" />
		</p>
		<p>
			가격 : <input type="text" name="price" value="${data.price }"
				required="required" />
		</p>
		<p>
			<input type="submit" value="저장" /> <input type="button" value="취소"
				id="btnCancel" />
			<button type="button" id="btnList">목록</button>
		</p>
	</form>

</body>
</html>




