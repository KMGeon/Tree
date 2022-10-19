<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="vo.BookVO"%>
<%@ page import="dao.BookRepository"%>

<%
	request.setCharacterEncoding("utf-8");
	
	String bookId=request.getParameter("bookId");
	String name=request.getParameter("name");
	String unitPrice=request.getParameter("unitPrice");
	String author=request.getParameter("author");
	String publisher=request.getParameter("publisher");
	String releaseDate=request.getParameter("releaseDate");
	String totalPages=request.getParameter("totalPages");
	String description=request.getParameter("description");
	String category=request.getParameter("category");
	String unitsInStock=request.getParameter("unitsInStock");
	String condition=request.getParameter("condition");
	
	Integer price;
	
	if(unitPrice.isEmpty()){
		price=0;
	}else{ price=Integer.valueOf(unitPrice);
	}
	long stock;
	if(unitsInStock.isEmpty()){
		stock=0;
	}else{ stock=Long.valueOf(unitsInStock);
	}
	long pages;
	if(unitsInStock.isEmpty()){
		pages=0;
	}else{ pages=Long.valueOf(totalPages);
	}
	
	BookRepository dao=BookRepository.getInstance();
	
	BookVO book=new BookVO();
	book.setBookId(bookId);
	book.setName(name);
	book.setUnitPrice(price);
	book.setAuthor(author);
	book.setPublisher(publisher);
	book.setReleaseDate(releaseDate);
	book.setTotalPages(pages);
	book.setDescription(description);
	book.setCategory(category);
	book.setUnitsInStock(stock);
	book.setCondition(condition);
	dao.addBook(book);
	response.sendRedirect("bookmarket.jsp");
	
%>