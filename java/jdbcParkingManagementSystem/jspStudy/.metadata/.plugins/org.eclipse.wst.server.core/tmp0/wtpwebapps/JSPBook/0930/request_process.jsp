<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Implicit Objects</title>
</head>
<body>
   <p>전송된 요청 파라미터 : <%=request.getQueryString()%></p>
   <!-- ******* 현재 JSP 페이지의 웹 어플리케이션 콘텍스트 경로를 가져옴 -->
   <p>컨텍스트 경로 : <%=request.getContextPath()%></p>
   <!-- *** 웹 브라우저에 요청한 URI를 가져옴 -->
   <p>요청 URI : <%=request.getRequestURI()%></p>
</body>
</html>