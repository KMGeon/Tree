<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.abc {
   display:flex;
   justify-content: flex-start;

}
.form-control{
   width: 80%;
}
</style>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/validation.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	$(function() {
		//이미지 미리보기 시작////////////////////////
		let sel_file = [];
		
		//input type="file" id="productImage" name="productImage" class="form-control"
		//multiple
		$("#productImage").on("change",handleImgFileSelect);
		//파라미터 e : onchange 이벤트 객체
		function handleImgFileSelect(e){
			//이벤트가 발생된 타겟 안에 이미지 파일들을 가져와보자 
			let files = e.target.files;
			//이미지가 여러개가 있을 수 있으므로 이미지를 분리하여 배열로 만듦
			let fileArr = Array.prototype.slice.call(files);
			//파일 타입의 배열 반복. f : 파일 배열 안에 들어있는 각각의 이미지 파일 객체
			fileArr.forEach(function(f){
				//이미지 파일이 아닌 경우 이미지 미리보기 실패로 처리
				if(!f.type.match("image.*")){
					alert("이미지 확장자만 가능합니다.");
					//함수를 종료
					return;
				}
				//이미지 객체를(f) 전역 배열타입 변수(sel_file)에 넣자
				sel_file.push(f);
				//이미지 객체를 읽을 자바스크립트의 reader 객체 생성
				let reader = new FileReader();
				//e : reader가 이미지 객체를 읽는 이벤트
				reader.onload = function(e){
					//e.target : 이미지 객체
					//e.target.result : reader가 이미지를 다 읽은 결과
					let img_html = "<img src=\"" + e.target.result + "\"  />";
					//div 사이에 이미지가 렌더링되어 화면에 보임
					//객체.append : 누적, .html : 새로고침, innerHTML : J/S
					$(".imgs_wrap").append(img_html);
				}
				//f : 이미지 파일 객체를 읽은 후 다음 이미지 파일(f)을 위해 초기화
				reader.readAsDataURL(f);
			});//end forEach
		}
		//이미지 미리보기 끝////////////////////////
		
		$('#aSubmit').on('click', function() {
			$('#frm').submit();
		});
		
		$("#btnDup").on("click",function(){
			let memId = $("#memId").val();
			
			console.log("memId : " + memId);
			
			if(memId==""){
				alert("아이디가 없습니다.아이디를 입력해주세요.");
				$("#memId").focus();
				return;
			}
			
			let data = {"memId":memId};
			
			//아작났어유.피씨다탔어
			$.ajax({
				url:"/board/chkDup",
				contentType:"application/json;charset=utf-8",
				data:JSON.stringify(data),
				type:"post",
				success:function(result){
					//result : {"result":"1"}
					console.log("result : " + JSON.stringify(result));
					console.log("result.result : " + result.result);
					//중복 시 1, 중복안되면 0
					let dupRslt = result.result;
					
					if(dupRslt>0){
						alert("사용중인 아이디가 있습니다.");
						$("#memId").focus();
						//등록 버튼 비활성화
						$("#btnSubmit").attr("disabled","disabled");
					}else{//중복안되어서 통과
						//등록 버튼 활성화
						$("#btnSubmit").removeAttr("disabled");
					}
				}
			});
		});
	});
</script>


<div class="card shadow mb-4">
	<!-- Card Header - Accordion -->
	<a href="#collapseCardExample" class="d-block card-header py-3"
		data-toggle="collapse" role="button" aria-expanded="true"
		aria-controls="collapseCardExample">
		<h6 class="m-0 font-weight-bold text-primary">Collapsable Card
			Example</h6>
	</a>
	<!-- Card Content - Collapse -->
	<div class="collapse show" id="collapseCardExample">
		<div class="card-body">
			<!-- ================card body 시작================= -->
			<form id="frm" name="frm" action="/create2" method="post"
				enctype="multipart/form-data">
				<label for="exampleFormControlInput1" class="form-label">memId</label>
				<div class="mb-3 abc">
					<input type="text" class="form-control" name="memId" id="memId"
						placeholder="memId" required />
					<button type="button" id="btnDup" class="btn btn-primary btn-icon-split btn-sm">
                        <span class="icon text-white-50">
                            <i class="fas fa-flag"></i>
                        </span>
                        <span class="text">아이디 중복 체크</span>
                    </button>
				</div>
				<div class="mb-3">
					<label for="exampleFormControlTextarea1" class="form-label">memName</label>
					<input type="text" class="form-control" name="memName" id="memName"
						placeholder="memName" required />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">memJob</label>
					<input type="text" class="form-control" name="memJob" id="memJob"
						placeholder="memJob" />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">memLike</label>
					<input type="text" class="form-control" name="memLike" id="memLike"
						placeholder="memLike" />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">memHp</label>
					<input type="text" class="form-control" name="memHp" id="memHp"
						placeholder="memHp" required />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">증명사진</label>
					<input class="form-control" type="file" id="productImage"
					name="pictureArray" multiple>
				</div>
				<div class="mb-3">
					<div class="imgs_wrap">
						
					</div>
				</div>
				<div class="mb-3">
					<button type="button" id="btnSubmit" onclick="CheckAddMem()" 
						class="btn btn-primary btn-icon-split" disabled> <span
						class="icon text-white-50"> <i class="fas fa-flag"></i>
					</span> <span class="text">등록</span>
					</button>
					<a href="/board/list" type="button"  
						class="btn btn-secondary btn-icon-split" > <span
						class="icon text-white-50"> <i class="fas fa-flag"></i>
					</span> <span class="text">취소</span>
					</a>
				</div>
			</form>
		</div>
	</div>
	
	
	
	
	
</div>