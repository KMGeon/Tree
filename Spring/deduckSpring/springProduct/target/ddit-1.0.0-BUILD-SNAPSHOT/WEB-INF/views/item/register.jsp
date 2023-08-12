<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<title>Item</title>
<script type="text/javascript">
$(function(){
	let formObj = $("#item");
	
	$("#btnRegister").on("click",function(){
		//attr : id, action, method, enctype
		formObj.attr("action","/item/register");
		formObj.attr("method","post");
		formObj.submit();
	});
});
</script>
</head>
<body>
<!-- ITEM 테이블 
ITEM_ID,ITEM_NAME,PRICE,DESCRIPTION
 -->
<h2>REGISTER</h2>
<form id="item" action="/item/register" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th>상품명</th>
			<td><input type="text" name="itemName" /></td>
		</tr>
		<tr>
			<th>가격</th>
			<td><input type="text" name="price" /></td>
		</tr>
		<tr>
			<th>파일</th>
			<td><input type="file" name="picture" /></td>
		</tr>
		<tr>
			<th>개요</th>
			<td>
				<textarea name="description"></textarea>
			</td>
		</tr>
	</table>
</form>
<div>
	<button type="button" id="btnRegister">Register</button>
	<button type="button" id="btnList">Lit</button>
</div>
</body>
</html>



