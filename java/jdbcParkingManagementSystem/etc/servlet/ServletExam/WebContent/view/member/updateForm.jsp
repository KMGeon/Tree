<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	MemberVO mv = (MemberVO)request.getAttribute("mv");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기존 회원 수정</title>
</head>
<body>
	<form action="update.do" method="post">
	<input type="hidden" name="memId" value="<%=mv.getMemId() %>">
		<table>
			<tr>
				<td>ID :</td>
				<td><%=mv.getMemId() %></td>
			</tr>
			<tr>
				<td>이름 :</td>
				<td><input type="text" name="memName" value="<%=mv.getMemName() %>"></td>
			</tr>
			<tr>
				<td>전화번호 :</td>
				<td><input type="text" name="memTel" value="<%=mv.getMemTel() %>"></td>
			</tr>
			<tr>
				<td>주소 :</td>
				<td><textArea rows="3" cols="20" name="memAddr"><%=mv.getMemAddr() %></textArea></td>
			</tr>
		</table>
		<input type="submit" value="회원정보수정">
	</form>
</body>
</html>