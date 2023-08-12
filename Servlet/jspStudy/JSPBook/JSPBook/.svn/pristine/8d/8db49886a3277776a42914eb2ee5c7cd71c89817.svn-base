<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page import="ch04.vo.BookVO"%>
<%@ page import="java.util.List"%>
<%@ page import="ch04.dao.BookRepository"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<title>도서 등록</title>
</head>
<body>

   <!-- 머리글에 해당하는 menu.jsp 파일의 내용을 포함하도록
   include 액션 태그를 작성  -->
   <jsp:include page="menu.jsp" />
   <div class="jumbotron">
      <!-- 내용 드루와 -->
      <div class="container">
         <h1 class="display-3">도서 등록</h1>
      </div>
   </div>
   <!-- ============= 도서 등록 시작 ==============  -->
   <div class="container">
      <form name="newBook" action="processAddBook.jsp"
         class="form-horizontal" method="post">
         <div class="form-group row">
            <label class="col-sm-2">도서 코드</label>
            <div class="col-sm-3">
               <input type="text" name="bookId" class="form-control" />
            </div>
         </div>
         <div class="form-group row">
            <label class="col-sm-2">도서 명</label>
            <div class="col-sm-3">
               <input type="text" name="name" class="form-control" />
            </div>
         </div>
         <div class="form-group row">
            <label class="col-sm-2">가격</label>
            <div class="col-sm-3">
               <input type="text" name="unitPirce" class="form-control" />
            </div>
         </div>
         <div class="form-group row">
            <label class="col-sm-2">저자</label>
            <div class="col-sm-3">
               <input type="text" name="author" class="form-control" />
            </div>
         </div>
         <div class="form-group row">
            <label class="col-sm-2">출판사</label>
            <div class="col-sm-3">
               <input type="text" name="publisher" class="form-control" />
            </div>
         </div>
         <div class="form-group row">
            <label class="col-sm-2">출판일</label>
            <div class="col-sm-3">
               <input type="text" name="releaseDate" class="form-control" />
            </div>
         </div>
         <div class="form-group row">
            <label class="col-sm-2">총페이지 수</label>
            <div class="col-sm-3">
               <input type="text" name="totalPages" class="form-control" />
            </div>
         </div>
         <div class="form-group row">
            <label class="col-sm-2">상세 정보</label>
            <div class="col-sm-3">
               <input type="text" name="description" class="form-control" />
               <textarea name="description" rows="2" cols="50"
                class="form-control"></textarea>
            </div>
         </div>
               <div class="form-group row">
            <label class="col-sm-2">분류</label>
            <div class="col-sm-3">
               <input type="text" name="category" class="form-control" />
            </div>
         </div>
         <div class="form-group row">
            <label class="col-sm-2">재고수</label>
            <div class="col-sm-3">
               <input type="text" name="unitsInStock" class="form-control" />
            </div>
         </div>
         <div class="form-group row">
            <label class="col-sm-2">상태</label>
            <div class="col-sm-5">
               <input type="radio" name="condition" value="New" />신규 도서
               <input type="radio" name="condition" value="Old" />중고 도서
               <input type="radio" name="condition" value="Refurbished" />E-Book
            </div>
         </div>
         <div class="form-group row">
            <div class="col-sm-offset-2 col-sm-10">
               <input type="submit" class="btn btn-primary" value="등록" />
               <a href="products.jsp" class="btn btn-primary">도서 목록</a>
            </div>
         </div>
      </form>
   </div>   
   <!-- ============= 도서 등록 끝 ===============  -->
   <jsp:include page="footer.jsp" />
</body>
</html>   