<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//addCart.jsp?id=P1235
	String id = request.getParameter("id");
	
	//id에 값이 없다면.. =>장바구니에 담을 상품이 없다면...
	//먼저 걸러준다고 생각하면 좋음
	
	if(id==null || id.trim().equals("")){
		//상품 목록으로 이동
		response.sendRedirect("products.jsp");
		return ;	
	}
	
	//1)P1235에 해당되는 ProductVO를 가져옴
	//select * from product where product_id = "P1235"
	
	//2)cartlist라는 속성 session.getAttribute("cartlist");
	session.getAttribute("cartlist");
%>