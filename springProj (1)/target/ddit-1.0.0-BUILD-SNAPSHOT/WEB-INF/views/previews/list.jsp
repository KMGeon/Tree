<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="bd-example">
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">회원번호</th>
				<th scope="col">회원아이디</th>
				<th scope="col">회원명</th>
				<th scope="col">첨부파일</th>
			</tr>
		</thead>
		<tbody>
			<!-- stat.index : 0,1,2
  		 stat.count : 1,2,3 -->
			<c:forEach var="memVO" items="${memVOList}" varStatus="stat">
				<tr>
					<th scope="row">${stat.count}</th>
					<td><a href="/previews/detail?userNo=${memVO.userNo}">${memVO.userNo}</a></td>
					<td>${memVO.userId}</td>
					<td>${memVO.userName}</td>
					<td><c:forEach var="attachVO" items="${memVO.attachVOList}">
							<c:if test="${attachVO.filename!=null}">
								<img src="/resources/images/file.png"
									style="width: 30px; cursor: pointer;"
									onclick="fn_download('${attachVO.filename}')" />
							</c:if>
						</c:forEach></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</div>
<iframe id="ifrm" name="ifrm" style="display: none;"></iframe>
<script type="text/javascript">
function fn_download(param) {
	console.log("param : " + param);
	let vIfrm = document.getElementById("ifrm");
	//UploadController.java 에서 요청을 처리
	// /2022/08/16/abf82e54-c353-4062-a603-8e8b11a34637_tomcate.PNG
	vIfrm.src="/upload/download?fileName="+param;
}
</script>