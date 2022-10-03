<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장함수s</title>
</head>
<body>

	<%-- ?name=개똥이 =>쿼리 스트링 / 요청파라미터 / http파라미터 --%>
	<%-- name=개똥이 => request객체에 들어감 --%>
	<form action="process.jsp" method="post">
		<p>
			이름 : <input type="text" name="name" /> <input type="submit"
				value="전송">
		</p>
	</form>
</body>
</html>