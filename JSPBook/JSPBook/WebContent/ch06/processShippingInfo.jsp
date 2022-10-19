<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%
	request.setCharacterEncoding("UTF-8");
	//쿠키 생성
	//요청 파라미터 정보를 쿠키에 넣어주자
	
	Cookie cartId = new Cookie("Shipping_cartId",URLEncoder.encode(request.getParameter("cartId"),"UTF-8"));
	
	Cookie name = new Cookie("Shipping_name",URLEncoder.encode(request.getParameter("name"),"UTF-8"));
	Cookie shippingDate = new Cookie("Shipping_shippingDate",URLEncoder.encode(request.getParameter("shippingDate"),"UTF-8"));
	Cookie country = new Cookie("Shipping_country",URLEncoder.encode(request.getParameter("country"),"UTF-8"));
	Cookie zipCode = new Cookie("Shipping_zipCode",URLEncoder.encode(request.getParameter("zipCode"),"UTF-8"));
	Cookie addressName = new Cookie("Shipping_addressName",URLEncoder.encode(request.getParameter("addressName"),"UTF-8"));

	//쿠키의 유효 기간을 1일로 설정( 초단위 ) 60*60 = 1분
	cartId.setMaxAge(24*60*60);
	name.setMaxAge(24*60*60);
	shippingDate.setMaxAge(24*60*60);
	country.setMaxAge(24*60*60);
	zipCode.setMaxAge(24*60*60);
	addressName.setMaxAge(24*60*60);
	
	//생성된 쿠키를 등록(서버에서 생성이 되어 response 객체에 담겨서 클라이언트로 가여와짐)
	response.addCookie(cartId);
	response.addCookie(name);
	response.addCookie(shippingDate);
	response.addCookie(country);
	response.addCookie(zipCode);
	response.addCookie(addressName);

	response.sendRedirect("orderConfirmation.jsp");
	
	
	
%>