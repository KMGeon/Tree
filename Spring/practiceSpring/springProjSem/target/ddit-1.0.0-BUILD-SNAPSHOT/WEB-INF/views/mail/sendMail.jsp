<%@ page import="java.util.Random" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
    <title>웹 메일 보내기</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/mail/sendMailProcess" method="post">
    <div>
        <input type="text" id="from" name="from"
               placeholder="보내는 사람" value="pos04167@naver.com"
               readonly="readonly" />
    </div>
    <div>
        <input type="text" id="to" name="to"
               placeholder="받는 사람" required="required" />
    </div>
    <div>
        <input type="text" id="subject" name="subject"
               placeholder="제목" required="required" />
        <% Random random = new Random();
                int checkNum = random.nextInt(888888) + 111111;%>
        <textarea rows="7" cols="5" id="text" name="text" ><%= checkNum%><%= checkNum%></textarea>
    </div>
    <button type="submit">메일 전송하기</button>
</form>
</body>
<script type="text/javascript">
    CKEDITOR.replace("text");
</script>