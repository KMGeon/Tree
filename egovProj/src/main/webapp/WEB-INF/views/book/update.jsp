<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<body>
	<form action="/book/updatePost" method="post">
		<c:forEach var="bookVO" items="${bookVO}">
			<input type="hidden" id="bookId" name="bookId" value="${bookVO.bookId }">
			<div class="card-body">
				<div class="form-group">
					<label>Title:</label> <input type="text"
						class="form-control my-colorpicker1 colorpicker-element"
						data-colorpicker-id="1" data-original-title="" title=""
						id="title" name="title"
						value="${bookVO.title}" readonly="readonly">
				</div>

				<div class="form-group">
					<label>Category:</label> <input type="text"
						class="form-control my-colorpicker1 colorpicker-element"
						data-colorpicker-id="1" data-original-title="" id="category" name="category"
						value="${bookVO.category}" title="">
				</div>

				<div class="form-group">
					<label>Price:</label> <input type="text"
						class="form-control my-colorpicker1 colorpicker-element"
						data-colorpicker-id="1" data-original-title="" id="price" name="price"
						value='${bookVO.price}' />
				</div>
				<div class="form-group">
					<label>입력일자:</label> <input type="text"
						class="form-control my-colorpicker1 colorpicker-element"
						data-colorpicker-id="1" value='22-10-01' id="insertDate" name="insertDate"
						 readonly="readonly" />
				</div>

				<div>
					<input type="submit"
						class="btn btn-block btn-outline-info btn-flat"
						style="width: 100px; float: right;" value="수정"/>
				</div>

			</div>
		</c:forEach>
	</form>
	<script type="text/javascript">
	
		function 
	
	
	</script>
</body>
</html>