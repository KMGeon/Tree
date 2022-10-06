<%@page import="ch04.vo.ProductVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ch04.dao.ProductRepository"%>

<%
	// 폼 페이지에서 입력된 데이터를 서버(톰캣)로 전송 시 한글이 깨지지 않도록 하자
	request.setCharacterEncoding("UTF-8");
	
	String productId = request.getParameter("productId");
	String pname = request.getParameter("pname");
	String unitPrice = request.getParameter("unitPrice");
	String description = request.getParameter("description");
	String manufacturer = request.getParameter("manufacturer");
	String category = request.getParameter("category");
	String unitsInStock = request.getParameter("unitsInStock");
	String condition = request.getParameter("condition");
	// 가격은 숫자형
	int price;
	// 폼 페이지에서 상품 가격이 입력되지 않았다면 0으로 처리
	if(unitPrice.isEmpty()) {
		price = 0;
	} else {
		price = Integer.parseInt(unitPrice);
	}
	
	// 상품 재고수도 숫자형
	int stock;
	if(unitsInStock.isEmpty()) {
		stock = 0;
	} else {
		stock = Integer.parseInt(unitsInStock);
	}
	
	ProductRepository dao = ProductRepository.getInstance();
	
	// 빈(Bean) 객체에 데이터를 태워서 add하자
	ProductVO productVO = new ProductVO();
	productVO.setProductId(productId);
	productVO.setPname(pname);
	productVO.setUnitPrice(price);
	productVO.setDescription(description);
	productVO.setManufacturer(manufacturer);
	productVO.setCategory(category);
	productVO.setUnitsInStock(stock);
	productVO.setCondition(condition);
	
	// insert into product(...) values(...);
	// 폼 페이지에서 입력된 데이터를 저장하도록 ProductRepository 클래스의 addProduct 메소드를 호출
	dao.addProduct(productVO);
	
// 	out.print("productVO : " + productVO.toString());
	
	// products.jps 페이지로 강제 이동
	response.sendRedirect("products.jsp");
%>













