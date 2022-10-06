<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Enumeration"%>
<!DOCTYPE html>
<html>
<head>
<title>Form Processing</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>요청 파라미터 이름</th>
			<th>요청 파라미터 값</th>
		</tr>
		<%! // 선언문
			// String[] -> String으로 합쳐주는 메소드
			String arrToStr(String[] arr) {
				String str = "";
				for(String s : arr) {
					str += " " + s;
				}
				
				return str;
		}
		%>
		<%
			request.setCharacterEncoding("UTF-8");
			// ?id=a001&name=개똥이
			// getParameterName() : 모든 입력 양식의 요청 파라미터 이름을
			// 순서에 상관없이 Enumeration(열거형) 형태로 전달받는다
			Enumeration paramNames = request.getParameterNames(); // id, name
			String paramValue = "";
			// hasMoreElements() : 열거형 요소가 있으면 treu, 없으면 false 반환
			// 전송된 요청 파라미터가 없을 때까지 반복
			while(paramNames.hasMoreElements()) {
				// nextElement() : 열거형 요소를 반환해준다
				// 폼 페이지에서 전송된 요청 파라미터의 이름을 가져온다
				String name = (String) paramNames.nextElement();
				// 취미의 경우 String[] 형태로 처리해야 한다
				if(name.equals("hobby")) {
					paramValue = arrToStr(request.getParameterValues(name));
				} else {
					paramValue = request.getParameter(name);
				}
				out.print("<tr><td>" + name + "</td><td>" + paramValue + "</td><td></td></tr>");
				
			}
		%>
	</table>
</body>
</html>