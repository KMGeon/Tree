<%@page import="kr.or.funding.member.VO.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<MemberVO> memList = (List<MemberVO>) request.getAttribute("memList");

String msg = (String) session.getAttribute("msg") == null ? "" : (String) session.getAttribute("msg");
session.removeAttribute("msg"); // 꺼내온 메세지 속성값 삭제하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>

	<table border="1">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이메일</th>
			<th>5</th>
			<th>6</th>
			<th>7</th>
			<th>8</th>
			<th>9</th>
			<th>10</th>
			<th>11</th>
			<th>12</th>
			<th>판매자</th>

		</tr>

		<%
		int memSize = memList.size();
		if (memSize > 0) {
			for (int i = 0; i < memSize; i++) {
		%>

		<tr>
			<td><%=memList.get(i).getMbsNum()%></td>
			<td>><%=memList.get(i).getMbsId()%></td>
			<td><%=memList.get(i).getMbsPw()%></td>
			<td><%=memList.get(i).getMbsMail()%></td>
			<td><%=memList.get(i).getMbsAddr()%></td>
			<td><%=memList.get(i).getBrDt()%></td>
			<td><%=memList.get(i).getMbsPh()%></td>
			<td><%=memList.get(i).getRfCd()%></td>
			<td><%=memList.get(i).getMilgNum()%></td>
			<td><%=memList.get(i).getMbsAhy()%></td>
			<td><%=memList.get(i).getMbsNm()%></td>
			<td><%=memList.get(i).getAtchFileId()%></td>
			
		</tr>

		<%
		}
		} else {
		%>
		<tr>
			<td colspan="5">회원정보가 없습니다.</td>
		</tr>
		<%
		}
		%>
		<tr>
			<td colspan="5"><a href="/Funding/view/member/login.jsp">[회원정보
					업데이트]</a> <a href="delete.do">[회원정보 삭제]</a></td>
		</tr>
	</table>
	<%
	if (msg.equals("성공")) {
	%>
	<script>
		alert("정상적으로 처리되었습니다.");
	</script>
	<%
	}
	%>
</body>
</html>