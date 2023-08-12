<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="vo.BookVO"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.BookRepository"%>
<!DOCTYPE html>
<%  
	BookRepository BookDAO = BookRepository.getInstance();
	// SELECT * FROM Book
	List<BookVO> listOfBooks = BookDAO.getAllBook();
	// out.print(listOfBooks);
%>
<c:set var="listOfBooks" value="<%=listOfBooks%>" scope="page" />
<html>
<head>
<link rel="stylesheet" 
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>도서 목록</title>
<style>
.col-md-4{
	display:block;
}
</style>
</head>
<body>
	<jsp:include page="menu_book.jsp" />
		<div class="jumbotron">
			<!-- 내용 드루와 -->
			<div class="container">
				<h1 class="display-3">도서목록</h1>
			</div>
		</div>
	<div class="container">
	   <div class="row" style="justify-content: right; margin:0 0 30px 0;">
         <a href="addBook.jsp" class="btn btn-primary">도서추가</a>
      </div>
		<div class="row" align="left">
			<!-- List<ProductVO> -> 한 행을 꺼내오면 -> ProductVO -->
			<c:forEach var="BookVO" items="${listOfBooks}">
				<div class="col-md-4">
					<img alt="${BookVO.name}" title="${BookVO.name}" 
					src="/resources/images/${BookVO.fileName}"
					style="height:50%; width:100%;">
					<h3>[${BookVO.category}] ${BookVO.name}</h3>
					<p>${BookVO.description}</p>
					<p>${BookVO.author} | ${BookVO.publisher} | ${BookVO.unitPrice}원</p>
					<p><a href="book.jsp?id=${BookVO.bookId}"
					    class="btn btn-secondary" role="button">도서 정보&raquo;</a></p>
					<br />
					<hr />					
				</div>	
			</c:forEach>
		</div>	
	</div>
	<jsp:include page="footer_book.jsp" />
</body>
</html>