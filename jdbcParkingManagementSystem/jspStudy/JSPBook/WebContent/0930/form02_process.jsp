<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05.Form Processing</title>
</head>
<body>
<!-- 
form01_processe.jsp?name=개똥이&addr=대전&email=test@test.com
 -->
    <%
        request.setCharacterEncoding("utf-8");
    	//{"name", "addr", "email"}
    	//연습용
        Enumeration en=request.getParameterNames();
        //{"name", "addr", "email"}
        //실전
        Enumeration em=request.getParameterNames();
        //em.hasMoreElements() : 요소가 있을 때까지 반복
        while(em.hasMoreElements())
        {	
        	//name, addr, email
            String name=(String)em.nextElement();
        	//request.getParameter("name")
            String pValue=request.getParameter(name);
            out.println(name+" : "+pValue + "<br>");
        }
        
    %>
</body>
</html>
