<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<!DOCTYPE html>
<html>
<head>
<title>제이쿼리로 동적으로 생성된 버튼에 이벤트를 달아보자</title>
<script type="text/javascript">
	$(function(){
		$("#firstButton").on("click",function(e){
			let bodyHtml = "<button type='button' id='secondButton'>두 번째 버튼(동적 생성)</button>"
			$("mainDiv").append(bodyHtml);
		});
		//두 번째 버튼 이벤트
		//두 번째 버튼을 클릭하면 alert()를 처리해보자
		//$("secondButton").on("click",function(){alert("하이")});
		$(document).on("click","#secondButton",function(){alert("하이")}));
	})
	$(document).

</script>
<script type="text/javascript">
	let c_values = "500,200,600,700,100,300";
</script>
</head>
<body>
<div id="mainDiv">
	<button id="firstButton">첫 번째 버튼</button> 
</div>
</body>
</html>