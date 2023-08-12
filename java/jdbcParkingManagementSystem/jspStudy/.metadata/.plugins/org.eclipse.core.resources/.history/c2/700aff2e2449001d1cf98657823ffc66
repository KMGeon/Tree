<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page import="java.util.List"%>
<%@ page import="ch04.vo.BookVO"%>
<%@ page import="ch04.dao.BookRepository"%>
<!DOCTYPE html>
<%//스크립틀릿 태그 
	//기본적으로 3개의 상품이 생성되어 있는 객체
	BookRepository bookDAO = BookRepository.getInstance();
	List<BookVO> listOfBooks = bookDAO.getAllBooks();
%>

<c:set var="listOfBooks" value="<%=listOfBooks%>" scope="page" />
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>도서 목록</title>
</head>
<body>
	<!-- 머리글에 해당하는 munu.jsp 파일의 내용을 포함하도록 include액션 태그 작성 -->
	<jsp:include page="/ch06/menu.jsp"/>
	<div class="jumbotron">
		<!-- 내용넣기! -->
		<div class="container">
			<h1 class="display-3">도서 목록</h1>
		</div>
	</div>
	<!-- =========== 상품목록 시작!============ -->
	<div class="container">
		<div class="col" align="left">
			<!-- List<ProductVO> 한 행을 꺼내오면  ProductVO-->
			<c:forEach var="bookVO" items="${listOfBooks}">
				<div class="col-lg">
					<h3>${bookVO.name}</h3>
					<p>${bookVO.description}</p>
					<p>${bookVO.author} | ${bookVO.publisher} | ${bookVO.unitPrice}원</p>
					<hr />
					<!-- 상품 아이디에 대한 상세 정보 페이지가 연결되도록 상세 정보 버튼 작성 -->
					
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- =========== 상품목록 끝 ! =========== -->
	<jsp:include page="/ch06/footer.jsp" />
</body>
</html>