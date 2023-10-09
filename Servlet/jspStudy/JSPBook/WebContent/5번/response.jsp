<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<!DOCTYPE html>
<title>Implicit Object</title>
</head>
<body>
	<%
		response.setIntHeader("Refresh", 5);
		Calendar calendar = Calendar.getInstance();
	
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String ap;
		if (hour > 12) {
			hour -= 12;
			ap = "PM";
		} else {
			ap = "AM";
		}
		String time = hour+":"+minute+":"+second+" "+ap;
	%>
	<p>현재 시간: <%=time%></p>
	<a href="./response_data.jsp">Google 홈페이지로 이동하기</a>
</body>
</html>





