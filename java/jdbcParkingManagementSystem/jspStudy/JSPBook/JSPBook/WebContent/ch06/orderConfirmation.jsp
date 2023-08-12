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
			<h1 class="display-3">주문 정보</h1>
		</div>
	</div>
	<!-- --------------------- 주문 정보 시작 ------------------------- -->
	<div class="container col-8 alert alert-info">
		<div class="text-center">
			<h1>영수증</h1>
		</div>
		<!-- 고객 정보 시작 : cookie 사용 -->
		<div class="row justify-content-between">
			<strong>배송 주소</strong> 성명 :
			<%= Shipping_name %><br> 우편번호 :
			<%= Shipping_zipCode %><br> 주소 :
			<%= Shipping_addressName %>&nbsp;<%= Shipping_country %><br>
		</div>
		<div class="col-4" align="right">
			<p>
				<em>배송일 : <%= Shipping_shippingDate %></em>
			</p>
		</div>
		<!-- 고객 정보 끝 -->
		<!-- 상품 정보 시작 : session 사용 -->
		<div>
			<table class="table table-hover">
				<tr>
					<th class="text-center">상품명</th>
					<th class="text-center">#</th>
					<th class="text-center">가격</th>
					<th class="text-center">금액</th>
				</tr>
				<%  // 스크립틀릿
               double sum = 0;
               // cartlist라는 이름의 장바구니(=세션)
               ArrayList<ProductVO> cartList = (ArrayList<ProductVO>) session.getAttribute("cartlist");
               // 상품 목록을 하나씩 출력해보자
               for(int i=0; i<cartList.size(); i++) {
                  // 상품목록에서 상품을 하나 끄집어 내자
                  ProductVO product = cartList.get(i);
                  double total = product.getUnitPrice() * product.getQuantity();
                  BigDecimal totalBig = new BigDecimal(total);
                  
                  sum += total;
            %>
				<tr>
					<td class="text-center"><em><%= product.getPname() %></em></td>
					<td class="text-center"><%= product.getQuantity() %></td>
					<td class="text-center"><fmt:formatNumber
							value="<%= product.getUnitPrice() %>" pattern="#,###" /></td>
					<td class="text-center"><fmt:formatNumber
							value="<%= totalBig %>" pattern="#,###" /></td>
				</tr>
				<%
               }
               
               // ###.0 => ###
               BigDecimal bdm = new BigDecimal(sum);
            %>
				<tr>
					<td></td>
					<td></td>
					<td class="text-right"><strong>총액:</strong></td>
					<td class="text-center text-danger"><strong><fmt:formatNumber
								value="<%= bdm %>" pattern="#,###" /></strong></td>
				</tr>
			</table>
			<a href="shippingInfo.jsp?cartId=" <%=Shipping_cartId %>
				class="btn btn-secondary" role="button"> 이전 </a> <a
				href="thankCustomer.jsp" class="btn btn-success" role="button">주문완료</a>
				 <a
				href="checkOutCancelled.jsp" class="btn btn-success" role="button">취소</a>
		</div>
		<!-- 상품 정보 끝 : session 사용 -->
	</div>
	<!-- --------------------- 주문 정보 끝 ------------------------- -->
	<!-- 끝맺음 -->
	<jsp:include page="./footer.jsp" />
</body>
</html>