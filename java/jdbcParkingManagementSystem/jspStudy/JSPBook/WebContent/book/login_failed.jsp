<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<script type="text/javascript">
	alert("로그인에 실패하였습니다.")	
</script>
<% 
	response.sendRedirect("login.jsp?error=1");
%>
</html>