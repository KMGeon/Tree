<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@page import="bookRepository.BookRepository"%>
<%@page import="bookRepository.BookVO"%>
<!DOCTYPE html>
<%
	String bookId = request.getParameter("id");
	BookRepository brp = BookRepository.getInstance();
	//SELECT * FROM BOOK WHERE BOOK_ID = 'P101';
	BookVO bookVO = brp.getBookById(bookId);
%>

<c:set var="bookVO" value="<%=bookVO %>" scope="page" />
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>상품 상세 정보</title>
</head>
<body>
<!-- 머리글에 해당하는 menu.jsp파일의 내용을 포함하도록 include 액션태그 작성 -->
	<jsp:include page="../project/menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 상세 정보</h1>
		</div>
	</div>
	
	<div class="container"> 
		<div class="row" >
			<div class="col">
				<h1>[${bookVO.category}] ${bookVO.name}</h1>
				<p>${bookVO.description}</p>
				<p><b>도서 코드 : </b><span class="badge badge-danger">${bookVO.bookId}</span></p>
				<p><b>출판사</b> : ${bookVO.publisher}</p>
				<p><b>저자</b> : ${bookVO.author}</p>
				<p><b>재고 수</b> : ${bookVO.unitsInStock}</p>
				<p><b>총 페이지 수</b> : ${bookVO.totalPages}</p>
				<p><b>출판일</b> : ${bookVO.releaseDate}</p>
				<p><b>저자</b> : ${bookVO.author}</p>
				<h4>${bookVO.unitPrice}원</h4>
				<p>
					<a href="#" class="btn btn-info">상품주문&raquo;</a>
					<a href="books.jsp" class="btn btn-secondary">상품목록&raquo;</a>
				</p>
			</div>
		</div>
	</div>
	
	<jsp:include page="../project/footer.jsp"/>
</body>
</html>