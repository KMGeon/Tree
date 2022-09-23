<%@page import="java.util.List"%>
<%@page import="kr.or.funding.member.VO.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	MemberVO mv = (MemberVO) request.getAttribute("mv");
%>    
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>기존 회원 수정</title>
</head>
<body>
	<form action="/Funding/member/update.do" method="post">

		<table>
			<tr>
				<td>I D:</td>
				<td><input type="text" name="mbsId" value="test" /></td>
			</tr>
			<tr>
				<td>이름:</td>
				<td><input type="text" name="mbsNm" /></td>
			</tr>
			<tr>
				<td>비밀번호:</td>
				<td><input type="password" name="mbsPw"  /></td>
			</tr>
			<tr>
				<td>주소:</td>
				<td><input type="text" name="mbsAddr"  /></td>
				
				</textarea></td>
			</tr>
			<tr>
				<td>전화번호:</td>
				<td><input type="text" name="mbsPh"  /></td>
		
				</textarea></td>
			</tr>
			<tr>
				<td>e-mail:</td>
				<td><input type="email" name="mbsMail"  /></td>
				</textarea></td>
			</tr>
		</table>
		<input type="submit" value="회원정보수정" />

	</form>
</body>
</html>