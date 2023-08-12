<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<form action="">
		아이디 <input type="text" id="newID" value="">
		<input type="button" value="중복체크" onclick="f_check()"> 
	</form>
<script>
	function f_check() {
		$.ajax({
			type:"get",
			url:"<%=request.getContextPath()%>/IDCheck",
			data:"merong=" +  $("#newID").val(),
			dataType:"text",
			success:function(p_rslt){
				console.log(p_rslt);
				if(p_rslt == "OK"){
					alert("사용할 수 있는 아이디입니다.");
				}else {
					alert("사용할 수 없는 아이디입니다.");
				}
			},
			error: function (request, status, error) {
		        console.log("code: " + request.status)
		        console.log("message: " + request.responseText)
		        console.log("error: " + error);
		    }
		})
	}
</script>
</body>
</html>