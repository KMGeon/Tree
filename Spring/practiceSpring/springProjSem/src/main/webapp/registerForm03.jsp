<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<script src="/resources/ckeditor/ckeditor.js"></script>
<title>Spring Form</title>

</head>
<body>
<!-- modelAttribute 속성에 폼 객체의 속성명을 지정함 -->
<!-- 폼 객체의 속성 명(model.addAttribute("member", new MemVO()))
	 스프링 폼 태그의 modelAttribute 속성값은 일치해야 함 -->
<form method="post" action="/form/register">
	<table>
		<tr>
			<th>소개</th>
			<td>
				<textarea name="content" id="content" cols="10" rows="5"></textarea>
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
CKEDITOR.replace("content");
</script>
</body>
</html>








