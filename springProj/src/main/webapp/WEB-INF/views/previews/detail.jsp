<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	//onsubmit()할때 이 함수를 가져와라
	function fn_chk() {
		//    return false;
		let userPw = document.getElementById("userPw").value;
		let userPwCheck = document.getElementById("userPwCheck").value;
		let spanPwCheck = document.getElementById("spanPwCheck");

		console.log("userPw : " + userPw);
		console.log("userPwCheck : " + userPwCheck);

		if (userPw != userPwCheck) {
			spanPwCheck.innerHTML = "비밀번호가 다릅니다.";
			return false;
		}
		return true;
	}
</script>
<div class="container">

	<div class="card o-hidden border-0 shadow-lg my-5">
		<div class="card-body p-0">
			<!-- Nested Row within Card Body -->
			<div class="row">
				<!-- 왼쪽 대표 이미지 -->
				<div class="col-lg-5 d-none d-lg-block bg-register-image"
					<c:if test="${memVO.attachVOList[0].filename != null}"> 
                   style="background-image:url('/resources/upload${memVO.attachVOList[0].filename}');background-size:cover;"
                   </c:if>></div>
				<div class="col-lg-7">
					<div class="p-5">
						<div class="text-center">
							<h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
						</div>


						<form:form modelAttribute="memVO" class="user" method="post"
							action="/previews/updatePost" onsubmit="return fn_chk()">
`								<form:hidden path="userNo" />
							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<!-- input type=text -> form:input -->
									<!-- id=userId , name=userId -> path:userId -->
									<form:input class="form-control form-control-user"
										placeholder="userId" path="userId" readonly="true" />
									<font color="red"> <form:errors path="userId" />
									</font>
								</div>
								<div class="col-sm-6">
									<form:input class="form-control form-control-user"
										placeholder="userName" path="userName" />
									<font color="red"> <form:errors path="userName" />
									</font>
								</div>
							</div>
							<div class="form-group">
								<form:input class="form-control form-control-user"
									placeholder="userMail" path="userEmail" />
								<font color="red"> <form:errors path="userEmail" />
								</font>
							</div>
							<div class="form-group">
								<form:input class="form-control form-control-user"
									placeholder="변경일자(yyyyMMdd)" path="updDate" />
								<font color="red"> <form:errors path="updDate" />
								</font>
							</div>
							<!-- 일반 모드 시작 -->
							<div id="spn1">
								<div class="form-group">
									<!-- 
                                  attachVOList : List<AttachVO>
                                  attachVOList[0] : AttachVO
                                -->
									<c:forEach var="attachVO" items="${memVO.attachVOList}">
										<!-- 
                                  /2022/08/16/abf82e54-c353-4062-a603-8e8b11a34637_tomcate.PNG
                                  => /2022/08/16/s_abf82e54-c353-4062-a603-8e8b11a34637_tomcate.PNG
                                -->
										<c:set var="filename" value="${attachVO.filename}" />
										<c:set var="filenameLen" value="${fn:length(filename)}" />
										<img
											src="/resources/upload${fn:substring(filename,0,12)}s_${fn:substring(filename,12,filenameLen)}">
									</c:forEach>
									<form:input class="form-control form-control-user"
										placeholder="첨부파일" path="attachVOList[0].filename" />
									<font color="red"> <form:errors
											path="attachVOList[0].filename" />
									</font>
								</div>
								<p>
									<button type="button" id="edit"
										class="btn btn-warning btn-user btn-block"
										style="width: 50%; float: left;">수정</button>
									<button type="button" id="delete"
										class="btn btn-danger btn-user btn-block" style="width: 50%;">삭제</button>
								</p>
								<p>
									<a href="/previews/list"
										class="btn btn-primary btn-user btn-block">목록</a>
								</p>
							</div>
							<!-- 일반 모드 시작 -->
							<!-- 수정 모드 시작 -->
							<div id="spn2" style="display: none;">
								<!-- 첨부파일 추가 -->
								<a class="btn btn-success btn-circle btn-sm"
									onclick="plusFile()"> <i class="fas fa-check"></i>
								</a>
								<!-- 첨부파일 삭제 -->
								<a class="btn btn-danger btn-circle btn-sm"
									onclick="minusFile()"> <i class="fas fa-trash"></i>
								</a>
								<div class="form-group">
									<form:input class="form-control form-control-user"
										placeholder="첨부파일" path="attachVOList[0].filename" />
									<font color="red"> <form:errors
											path="attachVOList[0].filename" />
									</font>
									<div id="fileForm"></div>
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<form:password class="form-control form-control-user"
											placeholder="Password" path="userPw" />
										<font color="red"> <form:errors path="userPw" />
										</font>
									</div>
									<div class="col-sm-6">
										<input type="password" class="form-control form-control-user"
											id="userPwCheck" placeholder="Repeat Password" /> <font
											color="red"> <span id="spanPwCheck"> </span>
										</font>
									</div>
								</div>
								<button type=submit id="btnSubmit"
									class="btn btn-primary btn-user btn-block" disabled>확인</button>
								<a href="/previews/detail?userNo=${param.userNo}"
									class="btn btn-primary btn-user btn-block">취소</a>
							</div>
							<!-- 수정 보드 끝 -->
						</form:form>
						<p />
						<p />
						<p />
						<p />
						<p />
						<p />
						<br /> <br /> <br /> <br />
						<p />
						<p />
						<p />
						<p />
						<p />
						<p />
						<br /> <br /> <br /> <br />
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
<script type="text/javascript">
	let cnt = 1;

	function plusFile() {
		$("#fileForm")
				.append(
						"<input type='text' class='form-control form-control-user' id='fileNamePlus' name='attachVOList["+cnt+"].filename' placeholder='첨부파일' />");
		cnt++;
	}

	function minusFile() {
		$(
				"input[id='fileNamePlus'][name='attachVOList[" + (--cnt)
						+ "].filename']").remove();
		console.log("cnt : " + cnt);
		if (cnt <= 0) {
			cnt = 1;
		}
	}

	$("#userPw").focusout(function() {
		let userPw = $(this).val();
		let userNo = "${param.userNo}";//기본키 데이터

		console.log("userPw : " + userPw);
		console.log("userNo : " + userNo);

		// 비밀번호가 맞는지 체킹
		let data = {
			"userPw" : userPw,
			"userNo" : userNo
		};

		$.ajax({
			url : "/previews/detailPwCheck",
			contentType : "application/JSON;charset=utf-8",
			//          dataType : "json",
			data : JSON.stringify(data),
			type : "post",
			success : function(result) {
				console.log("result : " + JSON.stringify(result));
				let cnt = result.result;
				if (cnt > 0) {
					$("#btnSubmit").removeAttr('disabled');
				} else {
					$("#btnSubmit").attr("disabled")
				}
			}
		});
	});

	$(function() {
		$("#edit").on("click", function() {
			// 일반 모드는 가림
			$("#spn1").css("display", "none");
			// 수정 모드는 보임
			$("#spn2").css("display", "block");
			$(".form-control-user").remoAttr("readonly");
			$(".img").remove();
			// 입력란 활성화
			$(".form-control-user").removeAttr("readonly");
		})
		//삭제 버튼 클릭
		$(function(){
			$("#delete").on("click",function(){
				$("memVO").attr("action","/previews/deletePost");
				//true(1) , false(0)
				let result= confirm("삭제하겠습니까?");
				console.log("result: "+result);
				if(result>0){
					$("#memVO").submit();
				}else{
					alert("삭제 취소되었습니다.");
				}
			});
		});
	});
</script>