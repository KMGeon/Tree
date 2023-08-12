<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05.Form Processing</title>
</head>
<body>
    <!--책의 결과 화면URL에 입력 데이터정보가 없으므로 post방식임  -->
<!-- 
form01_processe.jsp?name=개똥이&addr=대전&email=test@test.com
 -->
    <form action="form02_process.jsp" method="post">
        이름: <input type="text" name="name">
        <p> 주소: <input type="text" name="address">
        <p> 이메일: <input type="text" name="email">
        <p> 전송:<input type="submit" value="전송">
    </form>
</body>
</html>
