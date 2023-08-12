<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/validation.js"></script>

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
						placeholder="memId" value="${memVO.memId}" readonly />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlTextarea1" class="form-label">memName</label>
					<input type="text" class="form-control" name="memName" id="memName"
						placeholder="memName" value="${memVO.memName}" readonly />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">memJob</label>
					<input type="text" class="form-control" name="memJob" id="memJob"
						placeholder="memJob"  value="${memVO.memJob}" readonly />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">memLike</label>
					<input type="text" class="form-control" value="${memVO.memLike}" name="memLike" id="memLike"
						placeholder="memLike" readonly />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">memHp</label>
					<input type="text" class="form-control" value="${memVO.memHp}" name="memHp" id="memHp"
						placeholder="memHp" readonly />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">증명사진</label>
					<input class="form-control" type="file" id="productImage"
					name="pictureArray" multiple style="display:none;" />
				</div>
				<div class="mb-3">
					<div class="imgs_wrap">
						<!-- MemController에서 직접 옴 -->
						<c:forEach var="attachVO" items="${attachVOList}">
							<!-- /2022/11/03/c9cf7b80-e006-4833-a069-9be13a0e94f5_ksh.jpg -->
							<img src="/resources/upload${attachVO.attachName}" style="width:100px;" />
						</c:forEach>
						<hr />
						<!-- memVO 객체의 멤버변수를 끄집어냄 -->
						<c:forEach var="attachVO" items="${memVO.attachVOList}">
							<!-- /2022/11/03/c9cf7b80-e006-4833-a069-9be13a0e94f5_ksh.jpg -->
							<img src="/resources/upload${attachVO.attachName}" style="width:100px;" />
						</c:forEach>			
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