<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>

<title>Insert title here</title>
</head>
<body>
	<form action="/book/insert" method="post">
		<div style="width: 50%">
			<div class="card-body">
				<div class="form-group">
					<label for="exampleInputEmail1">제목</label> <input type="text"
						class="form-control" id="title" name="title" placeholder="title">
				</div>
			</div>

			<div class="card-body">
				<div class="form-group">
					<label for="exampleInputEmail1">카테고리</label> <input type="text"
						class="form-control" id="category" name="category"
						placeholder="소설">
				</div>
			</div>

			<div class="card-body">
				<div class="form-group">
					<label for="exampleInputEmail1">가격</label> <input type="text"
						class="form-control" id="price" name="price" placeholder="가격">
				</div>
			</div>

			   <div class="form-group">
            <label>내용</label>
            <div class="input-group" id="editor">
               <textarea id="content" name="content" class="form-control" rows="4"></textarea>
            </div>
         </div>



			<div class="card-body">
				<div class="form-group">
					<label for="exampleInputEmail1">시간</label> <input type="text"
						class="form-control" id="insertDate" name="insertDate"
						value="2022-11-14" placeholder="시간" readonly="readonly">
				</div>
			</div>
		</div>
		<input type="submit" value="전송">
	</form>
</body>
</html>