<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%
    //사용자의 정보가 정보가 저장되어있는 객체 requrest의 getParameter() 사용자의 정보를 추출
    String id=request.getParameter("id");//사용자의 iud값을 읽어드려서 변수에 저장
    String pass = request.getParameter("pass");
%>
    <h2>
        당신의 아이디는 <%=id%>이고 패스워드는 <%= pass %> 입니다.
    </h2>
<a href="RequestLoginProc.jsp"></a>
</body>
</html>
