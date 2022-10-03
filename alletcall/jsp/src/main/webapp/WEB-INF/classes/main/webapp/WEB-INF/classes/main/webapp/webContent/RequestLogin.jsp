<%--
  Created by IntelliJ IDEA.
  User: charon
  Date: 2022-08-18
  Time: 오전 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<center>
  <h2></h2>
  <form action="RequestLoginProc.jsp" method="get">
    <table width="400" border="1">
      <tr height="60">
        <td align="center"width="150">아이디</td>
        <td align="left"width="250"><input type="text"name="id"></td>
      </tr>

      <tr height="60">
        <td align="center"width="150">
          패스워드
        </td>
        <td align="left"width="250">
          <input type="password" name="pass">
        </td>
      </tr>
      <tr height="60">
        <td colspan="2"align="center"><input type="submit"value="전송">
        </td>
      </tr>
    </table>
  </form>
</center>
</body>
</html>
