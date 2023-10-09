<%@page import="java.util.ArrayList"%>
<%@page import="ch04.vo.ProductVO"%>
<%@page import="ch04.dao.ProductRepository"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//addCart.jsp?id=P1235
	String id = request.getParameter("id");//P1235
	
	//id에 값이 없다면.. => 장바구니에 담을 상품이 없다면...
	if(id==null || id.trim().equals("")){
		//상품 목록으로 이동
		response.sendRedirect("products.jsp");
		//현재 jsp를 더 이상 실행 안함
		return;
	}
	
	//상품 저장소 객체
	ProductRepository dao = ProductRepository.getInstance();
	
	//1) P1235에 해당되는 productVO를 가져옴
	//select * from product where product_id = 'P1235'
	//id : P1235
	ProductVO product = dao.getProductById(id);
	if(product == null){	//해당 상품이 없으면 예외 처리 페이지로 이동
		response.sendRedirect("exceptionNoProductId.jsp");
	}
	
	//2) session 객체에 cartlist라는 속성이 있는지 체킹 => 장바구니
	//   session.getAttribute("cartlist")
	//장바구니 안에는 상품들(List<ProductVO>)이 있다
	ArrayList<ProductVO> list 
		= (ArrayList<ProductVO>)session.getAttribute("cartlist");
		
	//2-2) cartlist라는 장바구니가 없으면 장바구니를 생성
	//   session.setAttribute("cartlist")
	if(list == null){
		//list는 null이므로 여기서 리스트를 생성해주자
		list = new ArrayList<ProductVO>();
		//cartlist라는 세션 속성 명으로 세션 생성
		session.setAttribute("cartlist", list);
	}
	//장바구니에 있는 상품이 파라미터로 넘어온 상품코드(id)와 같으면 1 증가
	int cnt = 0;
	//3-1) cartlist라는 장바구니에 P1235라는 상품이 이미 있는 경우
	//	   P1235라는 상품 객체(productVO)의 quantity 멤버변수의 값을 1 증가
	for(int i=0;i<list.size();i++){
		//list는 장바구니 안의 상품목록(P1234,P1235,P1236)
		//list => List<ProductVO>
		//list.get(0) => ProductVO
		//list.get(0).getProductId() => P1234
		//String id = request.getParameter("id");//P1235
		if(list.get(i).getProductId().equals(id)){
			cnt++;
			//장바구니에 상품이 이미 들어있으므로 장바구니에 담은 개수에 1 증가
			//list.get(i) => productVO(상품)
			//list.get(i).setQuantity() => 상품의 수량을 세팅
			list.get(i).setQuantity(list.get(i).getQuantity()+1);
		}
	}
	//3-2) cartlist라는 장바구니에 P1235라는 상품이 없는 경우(cnt => 0)
	//	   cartlist라는 장바구니에 P1235라는 상품을 넣자. quantity 값을 1로 처리
	if(cnt==0){
		product.setQuantity(1);
		list.add(product);
	}
	
	//장바구니 확인
	//list : List<ProductVO>	
// 	for(ProductVO vo : list){
// 		out.println("vo : " + vo.toString() + "<br /><hr />");
// 	}
	
	//4) product.jsp?id=P1235 경로로 되돌아가자
	response.sendRedirect("product.jsp?id="+id);
%>






