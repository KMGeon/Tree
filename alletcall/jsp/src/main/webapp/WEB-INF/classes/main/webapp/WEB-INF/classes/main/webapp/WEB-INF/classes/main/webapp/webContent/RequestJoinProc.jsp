<%--
  Created by IntelliJ IDEA.
  User: charon
  Date: 2022-08-19
  Time: PM 7:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h2>회원 정보 보기</h2>
    <%
        //post방식으로 데이터가 넘어올때 한글이 깨질수 있기에
        request.setCharacterEncoding("EUC-KR");

        String id = request.getParameter("id");
        String pass1 = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");

        //배열 타입으로 리턴
        //getParameterValues는 name값을 배열로 담아서 꺼내온다.  , getParameterName은 같은 name값을 가진것이 순서없이 담겨진다.
        String [] hobby = request.getParameterValues("hobby");
        String job = request.getParameter("job");
        String age = request.getParameter("age");
        String info = request.getParameter("info");

        if(!pass1.equals(pass2)){
    %>
    <script type="text/javascript">
        alert("비밀번호가 틀립니다."); //경고창
        history.go(-1);//이전 페이지로 이동
    </script>
    <%
    }
    %>

    <%--비밀번호1 , 비밀번호2 비교--%>

</center>
</body>
</html>
