<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>어서오세요</title>
<style type="text/css">
</style>
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="./welcome.jsp"> HOME </a>
			</div>
		</div>
	</nav>
	<%!//선언문 , 전역변수
	String greeting = "Welcome to Web Shopping Mall";
	String tagline = "Welcome to Web Market";%>
	<!-- 표현문 -->
	<div class="jumbotraon">
		<div class="container">
			<h1 class="display-3"><%=greeting%></h1>
		</div>
	</div>
	<!-- main태그는 주 내용을 작성할 때 여기에 쓴다. -->
	<main role="main">
		<div class="container">
			<div class="text-center">
				<h3><%=tagline%></h3>
			</div>
			<hr>
		</div>
	</main>

	<footer class="container">
	<hr>
		<p>&copy;WebMarket</p>
	</footer>
</body>
</html>