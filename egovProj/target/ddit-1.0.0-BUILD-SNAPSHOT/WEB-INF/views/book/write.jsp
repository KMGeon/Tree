<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<!-- JSTL format을 이용하여 천단위 구분기호를 적용할 수 있음 -->
<!-- JSTL format을 이용하여 날짜 포맷을 적용할 수 있음 -->
<div class="col-md-6" style="width:100%;">
	<form name="frm" id="frm" method="post">
	<div class="card card-primary" style="width:100%;">
		<div class="card-header">
			<h3 class="card-title">Date picker</h3>
		</div>
		<div class="card-body">
<!-- 
			bookVO : BookVO [bookId=1, title=검은개똥이, category=소설
			, price=12000, insertDate=Fri Nov 11 06:03:27 KST 2022]
-->
		<input type="hidden" name="bookId" value="${bookVO.bookId}" />
			<div class="form-group">
				<label>제목:</label>
				<div class="input-group date" id="reservationdate"
					data-target-input="nearest">
					<input type="text" name="title" 
					class="form-control rounded-0" value="${bookVO.title}" readonly />
				</div>
			</div>

			<div class="form-group">
				<label>카테고리:</label>
				<div class="input-group date" id="reservationdatetime"
					data-target-input="nearest">
					<input type="text" name="category" 
					class="form-control rounded-0" value="${bookVO.category}" readonly />
				</div>
			</div>

			<div class="form-group">
				<label>가격:</label>
				<div class="input-group date">
					<input type="text" name="price"  
						id="txtPrice" class="form-control rounded-0" 
						value="<fmt:formatNumber value='${bookVO.price}' pattern='#,###' />" readonly />
				</div>
			</div>
			
			<div class="form-group">
				<label>책 내용:</label>
				<div class="input-group date">
					<textarea name="content"  
						id="content" 
						readonly>${bookVO.content}</textarea>
				</div>
			</div>

			<div class="form-group">
				<label>입력일자:</label>
				<div class="input-group">
					<input type="text" class="form-control" name="insertDate" 
					value="<fmt:formatDate value='${bookVO.insertDate}' pattern='yyyy-MM-dd' />" readonly />
				</div>

			</div>

		</div>
		<!-- 일반모드 시작 -->
		<div id="spn1" class="card-footer">
			<div style="float:right;margin:0 10px 0 0;" >
				<button type="button" id="edit" 
				class="btn btn-block bg-gradient-warning btn-sm">수정</button>
			</div>
			<div style="float:right;margin:0 0 0 10px;">
				<a href="/book/list" 
				class="btn btn-block bg-gradient-success btn-sm">목록</a>
			</div>
		</div>
		<!-- 일반모드 끝 -->
		<!-- 수정모드 시작 -->
		<div id="spn2" class="card-footer" style="display:none;">
			<div style="float:right;margin:0 10px 0 0;" >
				<button type="submit"
				class="btn btn-block bg-gradient-primary btn-sm">확인</button>
			</div>
			<div style="float:right;margin:0 0 0 10px;">
				<a href="/book/detail?bookId=${bookVO.bookId}" 
				class="btn btn-block bg-gradient-danger btn-sm">취소</a>
			</div>
		</div>
		<!-- 수정모드 끝 -->
	</div>
	</form>
</div>
<script type="text/javascript">
$(function(){	
	//수정버튼 클릭-> 수정모드로 전환
	$("#edit").on("click",function(){
		//일반모드
		$("#spn1").css("display","none");
		//수정모드
		$("#spn2").css("display","block");
		//제목, 카테고리, 가격은 수정 가능 but, 입력일자는 readOnly 유지
		$(".rounded-0").removeAttr("readOnly");
		//제목, 카테고리, 가격의 required 속성 추가
		$(".rounded-0").attr("required",true);
		//책 내용 처리
		CKEDITOR.instances['content'].setReadOnly(false);
		//form action 추가
		$("#frm").attr("action","/book/updatePost");		
	});
	
	//숫자입력 validate
	$("#txtPrice").on("keyup",function(){
		$(this).val($(this).val().replace(/[^0-9]/g,""));
	});
	
	
});
</script>
<script type="text/javascript">
	CKEDITOR.replace("content");
</script>












	
	
	