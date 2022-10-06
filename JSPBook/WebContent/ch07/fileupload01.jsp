<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
파일 업로드 패키지인 common-fileupload는 서버의 메모리상에서 파일 처리가 가능하도록 지원함
이 패키지는 commins-io 패키지를 바탕으로 작성되었으므로
웹 브라우저(크롬)에서 서버(톰켓)로 파일을 업로드하기 위해
오픈 라이브러리 commons-fileupload.jar , commons-io.jar파일을 사용함
웹 어플리케이션의 /webcontent/web-inf/lib폴더에 포함되어야 함
 -->
	<form action="fileupload01_process.jsp" name="fileForm" method="post"
		enctype="multipart/form-data">
		<p>
			이름 <input type="text" name="name" />
		</p>
		<p>
			제목 <input type="text" name="subject">
		</p>

		<p>
			파일 <input type="file" name="filename">
		</p>

		<p>
			<input type="submit" value="파일 업로드">
		</p>
	</form>
</body>
</html>

