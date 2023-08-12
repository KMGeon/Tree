<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="ch04.vo.ProductVO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.net.URLDecoder"%>
<%  // 스크립틀릿
   String Shipping_cartId = "";
   String Shipping_name = "";
   String Shipping_shippingDate = "";
   String Shipping_country = "";
   String Shipping_zipCode = "";
   String Shipping_addressName = "";
   
   // request 객체에 있는 모든 쿠키 객체를 받자
   Cookie[] cookies = request.getCookies();
   // 쿠키의 갯수만큼 반복
   for(int i=0; i<cookies.length; i++) {
      Cookie thisCookie = cookies[i];
      // 쿠키 이름 가져옴
//       out.print(thisCookie.getName() + "<br>");
      
      // 쿠키 값 가져옴
      //URLDecoder.encode(request.getParameter("name"), "UTF-8");
//       out.print(URLDecoder.decode(thisCookie.getValue()) + "<br>");

      if(thisCookie.getName().equals("Shipping_name")) {
         Shipping_name = URLDecoder.decode(thisCookie.getValue());
      }
      if(thisCookie.getName().equals("Shipping_shippingDate")) {
         Shipping_shippingDate = URLDecoder.decode(thisCookie.getValue());
      }
      if(thisCookie.getName().equals("Shipping_country")) {
         Shipping_country = URLDecoder.decode(thisCookie.getValue());
      }
      if(thisCookie.getName().equals("Shipping_zipCode")) {
         Shipping_zipCode = URLDecoder.decode(thisCookie.getValue());
      }
      if(thisCookie.getName().equals("Shipping_addressName")) {
         Shipping_addressName = URLDecoder.decode(thisCookie.getValue());
      }
      if(thisCookie.getName().equals("Shipping_cartId")) {
         Shipping_cartId = URLDecoder.decode(thisCookie.getValue());
      }
   }
%>

<!DOCTYPE html>
<html>
<head>
<title>Cart</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
</head>
<body>
	<!--
       머리글에 해당하는 menu.jsp 파일의 내용을 포함하도록
       include 액션 태그를 작성
    -->
	<jsp:include page="./menu.jsp" />
	<div class="jumbotron">
		<!-- container : 이 안에 내용이 있다 -->
		<div class="container">
			<h1 class="display-3">주문 완료</h1>
		</div>
	</div>
	<!-- 주문 완료시작 -->
	<div class="container">
	<h2 class="alert alert-danger">주문해주셔서 감사합니다.</h2>
	<p><%= Shipping_shippingDate %> </p>
	<p><%=Shipping_cartId %> </p>
	</div>
	
	<div class="container">
	<p> <a href="products.jsp" class="btn btn-secondary">&laquo;상품목록</a> </p>
	</div>
	<!-- --------------------- 주문 정보 끝 ------------------------- -->
	<!-- 끝맺음 -->
	<jsp:include page="./footer.jsp" />
</body>
</html>