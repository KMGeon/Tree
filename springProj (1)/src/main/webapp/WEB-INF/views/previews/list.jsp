<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
				<c:forEach var="attachVO" items="${memList}" varStatus="stat">
					<tr>
						<th scope="row">${stat.count}</th>
						<td>${vo.userNo}</td>
						<td>${vo.userId}</td>
						<td>${vo.userName}</td>

						<c:forEach var="attachVO" items="${memVO.attachVOList}">
							<c:if test="${memVO.attachVOList}=''">
								<td><img src="/resources/images/파일아이콘.png"
									style="width: 30px; cursor: pointer;"
									onclick="fn_download('${attachVO.fileName}')" />
							</c:if>
						</c:forEach>


						</td>
					</tr>

				</c:forEach>
			</tbody>

		</table>
	</div>
	<iframe id="ifrm" name="ifrm" style="display: none;"></iframe>
	<script type="text/javascript">
		function fn_download(param) {

			console.log("param: " + param);
			let vIfrm = document.getElementById("ifrm");
			vIfrm.src = "/upload/download?fileName" + param;
		}
	</script>
</body>
</html>