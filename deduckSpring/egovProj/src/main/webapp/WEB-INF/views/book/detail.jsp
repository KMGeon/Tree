<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"src="/resources/js/jquery-3.6.0.js"></script>

<title>Insert title here</title>
</head>
<body>
	<input type="text">

	<c:forEach var="bookVO" items="${bookVO}">
		<input type="hidden" id="bookId1" value="${bookVO.bookId }"
			readonly="readonly">
		<div class="card-body">
			<div class="form-group">
				<label>Title:</label> <input type="text"
					class="form-control my-colorpicker1 colorpicker-element"
					data-colorpicker-id="1" data-original-title="" title=""
					value="${bookVO.title}" readonly="readonly">
			</div>

			<div class="form-group">
				<label>Category:</label> <input type="text"
					class="form-control my-colorpicker1 colorpicker-element"
					data-colorpicker-id="1" data-original-title=""
					value="${bookVO.category}" title="">
			</div>

			<div class="form-group">
				<label>Price:</label> <input type="text"
					class="form-control my-colorpicker1 colorpicker-element"
					data-colorpicker-id="1" data-original-title=""
					value='${bookVO.price}' />
			</div>
			<div class="form-group">
				<label>입력일자:</label> <input type="text"
					class="form-control my-colorpicker1 colorpicker-element"
					data-colorpicker-id="1" value='${bookVO.insertDate}'
					patten='yyyy-MM-dd' readonly="readonly" />
			</div>



		</div>
	</c:forEach>
	<div>
		<button type="button" class="btn btn-block btn-outline-info btn-flat"
			style="width: 100px; float: right;"
			onclick="location.href='/book/list'">목록</button>
	</div>

	<div>
		<button type="button" id="edit"
			class="btn btn-block btn-outline-info btn-flat"
			style="width: 100px; float: right;">수정</button>
	</div>

	<script type="text/javascript">
	var bookId2 = document.getElementById("bookId1");
		$(function() {
			alert("query동작");
			$("#edit").on("click",function(){
			
				location.href="/book/update?bookId="+bookId2.value;
			});
		})
	</script>


</body>
</html>