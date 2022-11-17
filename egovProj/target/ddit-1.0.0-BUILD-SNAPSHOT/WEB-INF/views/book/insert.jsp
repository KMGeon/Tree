<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<!-- JSTL format을 이용하여 천단위 구분기호를 적용할 수 있음 -->
<!-- JSTL format을 이용하여 날짜 포맷을 적용할 수 있음 -->
<div class="col-md-6" style="width:100%;">
	<form name="frm" id="frm" method="post" action="/book/insertPost">
	<div class="card card-primary" style="width:100%;">
		<div class="card-header">
			<h3 class="card-title">${bodyTitle}</h3>
		</div>
		<div class="card-body">
			<div class="form-group">
				<label>제목:</label>
				<div class="input-group date" id="reservationdate"
					data-target-input="nearest">
					<input type="text" name="title" 
					class="form-control rounded-0" value="" required />
				</div>
			</div>

			<div class="form-group">
				<label>카테고리:</label>
				<div class="input-group date" id="reservationdatetime"
					data-target-input="nearest">
					<input type="text" name="category" 
					class="form-control rounded-0" value="" required />
				</div>
			</div>

			<div class="form-group">
				<label>가격:</label>
				<div class="input-group date">
					<input type="text" name="price"  
						id="txtPrice" class="form-control rounded-0" 
						value="" required />
				</div>
			</div>
			
			<div class="form-group">
				<label>내용:</label>
				<div class="input-group date">
					<textarea id="content" name="content" 
					class="form-control" rows="4" style="height: 124px;"></textarea>
				</div>
			</div>

		</div>
		<div id="spn2" class="card-footer">
			<div style="float:right;margin:0 10px 0 0;" >
				<button type="submit"
				class="btn btn-block bg-gradient-primary btn-sm">확인</button>
			</div>
			<div style="float:right;margin:0 0 0 10px;">
				<a href="/book/list" 
				class="btn btn-block bg-gradient-danger btn-sm">목록</a>
			</div>
		</div>
	</div>
	</form>
</div>
<script type="text/javascript">
$(function(){	
		
	//숫자입력 validate
	$("#txtPrice").on("keyup",function(){
		$(this).val($(this).val().replace(/[^0-9]/g,""));
	});
		
});
</script>
<script type="text/javascript">
	CKEDITOR.replace("content");
</script>
	












	
	
	