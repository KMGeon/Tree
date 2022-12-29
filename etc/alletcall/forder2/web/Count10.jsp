<%--
  Created by IntelliJ IDEA.
  User: charon
  Date: 2022-08-14
  Time: 오전 5:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>1~10까지의 숫자를 화면에 표시</h2>
<%
    //1부터 10까지
    for(int i=0;i<10;i++) {

%>
<%= i %>
<%
    }
%>

</body>
</html>
