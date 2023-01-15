<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	선택한 과일
	<p>
		<%
			request.setCharacterEncoding("utf-8");
			String[] fruit = request.getParameterValues("fruit");
			//good!
			if (fruit != null) {
				for(String str : fruit){
					out.print(" " + str);
				}
			}
		%>
</body>
</html>




