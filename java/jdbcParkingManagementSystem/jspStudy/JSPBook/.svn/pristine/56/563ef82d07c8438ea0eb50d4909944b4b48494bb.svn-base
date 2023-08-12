<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="vo.BookVO"%>
<%@ page import="dao.BookRepository"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<%
	// 스크립틀릿 태그
	String id = request.getParameter("id");
	BookRepository bookRepository = BookRepository.getInstance();
	// SELECT * FROM PRODUCT
	BookVO bookVO = bookRepository.getBookById(id);
%>
<c:set var="bookVO" value="<%=bookVO%>" scope="page" />
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>도서 상세 정보</title>
</head>
<body>
	<!-- 머리글에 해당하는 menu.jsp 파일의 내용을 포함하도록
	include 액션 태그를 작성 -->
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<!-- 내용 들어온다 -->
		<div class="container">
			<h1 class="display-3">도서 정보</h1>
		</div>
	</div>
	<!-- =========== 도서 목록 시작 ============ -->
	<div class="container">
		<div>
			<!-- List<ProductVO> -> 한 행을 꺼내오면 -> ProductVO -->
				<!-- vo.멤버변수로 접근 -->
				<div>
				<img alt="${bookVO.name}" title="${bookVO.name}"
							src="/resources/images/${bookVO.filename}" style="width: 150px;">
				<h3>${bookVO.name}</h3>
				<p>${bookVO.description}</p>
				<p>
					<b>도서 코드 : </b>
					<span class="badge badge-danger">${bookVO.bookId}</span>
				</p>
				<p><b>출판사</b> : ${bookVO.publisher}</p>
				<p><b>저자</b> : ${bookVO.category}</p>
				<p><b>재고 수</b> : ${bookVO.unitsInStock}</p>
				<p><b>총 페이지수</b> : ${bookVO.totalPages}</p>
				<p><b>출판일</b> : ${bookVO.releaseDate}</p>
				<h4>${bookVO.unitPrice}원</h4>
				<p>
					<a href="#" class="btn btn-info">도서주문&raquo;</a>
					<a href="bookmarket.jsp" class="btn btn-secondary">도서목록&raquo;</a>
				</p>
				</div>
		</div>
	</div>
	<!-- =========== 도서 목록 시작 ============ -->
	<jsp:include page="footer.jsp" />
</body>
</html>