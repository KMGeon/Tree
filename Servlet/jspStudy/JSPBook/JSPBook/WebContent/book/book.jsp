<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page import="vo.BookVO"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.BookRepository"%>
<!DOCTYPE html>
<%  
	String id = request.getParameter("id");

	BookRepository bookRepository = BookRepository.getInstance();
    BookVO bookVO = bookRepository.getBookById(id);
			
%>
<c:set var="bookVO" value="<%=bookVO%>" scope="page" />
<html>
<head>
<link rel="stylesheet" 
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>도서 상세</title>
</head>
<body>
	<fmt:setLocale value="${param.language}" />
	<fmt:bundle basename="bundleBook.message">
	<jsp:include page="menu_book.jsp" />
		<div class="jumbotron">
			<div class="container">
				<h1 class="display-3"><fmt:message key="bookImformation	" /></h1>
			</div>
		</div>
	<div class="container">
		<div class="text-right">
			<a href="?id=${bookVO.bookId}&language=ko">Korea</a>|<a href="?id=${bookVO.bookId}&language=en">English</a>
		</div>
		<div class="row">
			<div class="col-md-6">
				<img alt="${bookVO.name}" title="${bookVO.name}" 
					src="/resources/images/${bookVO.fileName}"
					style="height:80%; width:80%;">
			</div>
			<div class="col-md-6">
				<h3>${bookVO.category}</h3>
				<p>${bookVO.description}</p>
				<p>
				   <b><fmt:message key="bookId" /> : </b>
				   <span class="badge badge-danger">${bookVO.bookId}</span>
				</p>			
				<p><b><fmt:message key="publisher" /></b> : ${bookVO.publisher}</p>
				<p><b><fmt:message key="author" /></b> : ${bookVO.author}</p>
				<p><b><fmt:message key="unitsInStock" /></b> : ${bookVO.unitsInStock}</p>
				<p><b><fmt:message key="totalPages" /></b> : ${bookVO.totalPages}</p>
				<p><b><fmt:message key="releaseDate" /></b> : ${bookVO.releaseDate}</p>
				<h4>${bookVO.unitPrice}원</h4>
				<p><a href="#" class="btn btn-info"><fmt:message key="bookOrder" />&raquo;</a>
				   <a href="books.jsp" class="btn btn-secondary"><fmt:message key="bookList" />&raquo;</a>	
				</p>
			</div>
		</div>	
	</div>
	<jsp:include page="footer_book.jsp" />
	</fmt:bundle>
</body>
</html>