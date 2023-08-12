<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 예제</title>
</head>
<body>
<h3>아파치 자카르타 프로젝트의 fileupload모듈을 이용한 파일 업로드</h3>
<form action="upload.do" method="post" enctype="multipart/form-data">
	파일 선택: <input type="file" name="uploadFile">
	전송자: <input type="text" name="sender">
	<button type="submit">파일업로드</button>
	
</form>

<hr>
<h3>서블릿3 부터 지원하는 Part 인터페이스를 이용한 파일 업로드</h3>
<form action="upload2.do" method="post" enctype="multipart/form-data">
	파일 선택: <input type="file" name="uploadFile">
	전송자: <input type="text" name="sender">
	<button type="submit">파일업로드</button>
	
</form>
</body>
</html>