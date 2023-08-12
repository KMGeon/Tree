<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="vo.BookVO"%>
<%@ page import="dao.BookRepository"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<%
	// 스크립틀릿 태그
// 기본적으로 3개의 상품이 들어 있는 객체 생성
BookRepository bookDAO = BookRepository.getInstance();
// SELECT * FROM PRODUCT
List<BookVO> listOfBook = bookDAO.getAllBook();
%>
<c:set var="listOfBook" value="<%=listOfBook%>" scope="page" />
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>도서 목록</title>
</head>
<body>
	<!-- 머리글에 해당하는 menu.jsp 파일의 내용을 포함하도록
	include 액션 태그를 작성 -->
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<!-- 내용 들어온다 -->
		<div class="container">
			<h1 class="display-3">도서목록</h1>
		</div>
	</div>
	<!-- =========== 도서 목록 시작 ============ -->
	<div class="container">
		<div>
			<!-- List<ProductVO> -> 한 행을 꺼내오면 -> ProductVO -->
			<c:forEach var="bookVO" items="${listOfBook}">
				<div class="row">
					<div class="col-3">
						<!-- vo.멤버변수로 접근 -->
						<!-- 				<div style="display: inline-block; width: 700px;"> -->
						<img alt="${bookVO.name}" title="${bookVO.name}"
							src="/resources/images/${bookVO.filename}" style="width: 100%;">
					</div>
					<div class="col-9">
						<h3>${bookVO.name}</h3>
						<p>${bookVO.description}</p>
						<p>${bookVO.author}|${bookVO.publisher}|${bookVO.unitPrice}원</p>

						<!-- 도서 아이디에 대한 상세 정보 페이지가 연결되도록 상세 정보 버튼 작성 -->
						<!-- 				</div> -->
						<div style="display: inline-block;">
							<p>
								<a href="bookmarketdetail.jsp?id=${bookVO.bookId}"
									class="btn btn-secondary" role="button">상세 정보&raquo;</a>
							</p>
						</div>
					</div>
				</div>
				<hr>
			</c:forEach>
		</div>
	</div>
	<!-- =========== 도서 목록 시작 ============ -->
	<jsp:include page="footer.jsp" />
</body>
</html>