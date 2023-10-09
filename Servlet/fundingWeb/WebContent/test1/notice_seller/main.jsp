<%@page import="java.util.List"%>
<%@page import="kr.or.funding.ptImfor.PtImforVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
  List<PtImforVO> myList = (List<PtImforVO>)request.getAttribute("atchList");
  for(PtImforVO oneVo : myList){
%>
  <% if(oneVo.getFileSn() == 1 && oneVo.getAtchFileId()==141 ){ %>
  <p>첨부파일ID<%=oneVo.getAtchFileId()%></p>
  <img src="<%=request.getContextPath()%>/sunju/<%=oneVo.getStreFileNm()%>" height='300' width='300' >
<% 	  
  }}
%>
</body>
</html>