<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>파일 업로드 예제</title>
</head>
<body>
	<h3>아파치 자카르타 프로젝트의 fileupload모듈을 이용한 파일 업로드</h3>
	<form action="upload.do" method="post" enctype="multipart/form-data">
		파일 선택: <input type="file" name="uploadFile"> 전송자: <input
			type="text" name="sender">

		<button>파일업로드</button>
	</form>
	<!-- form형식은 반드시 post로 보낸다. -->



</body>
</html>