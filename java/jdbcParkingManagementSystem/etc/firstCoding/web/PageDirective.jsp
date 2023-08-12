<%@ page import="java.util.Date" %>
<%@ page import="java.util.Scanner" %><%--
  Created by IntelliJ IDEA.
  User: charon
  Date: 2022-08-12
  Time: 오후 7:00
  To change this template use File | Settings | File Templates.
--%>
<%--밑에 이걸 써야지 jsp이다--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" errorPage="Error.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<%--<%--%>
<%--    //language속성을 java로 사용해서 여기서 자바코드 사용 가능--%>
<%--    //import는 자바랑 똑같이 여기에 Date를 사용하려면 import를 만들어야된다.--%>

<%--%>--%>
   나 화면에 보여지니

<%
    int a = 10;
%>

<%--에러 페이지--%>

</body>
</html>


<%--
session란
JSP의 내장 객체
페이지가 바뀌어도 데이터가 계속 저장

EX)장바구니 기능 : 페이지가 이동되어도 전에 있었던  ID , PASSWORD를 기억
전에는 디비에 저장했는데 지금은 JSP Session을 이용
디폴트 true (모든 jsp 세션 사용가능)


errorPage ="페이지명"
만약에 에러가 날 경우에는 페이지를 실행시켜라

--%>