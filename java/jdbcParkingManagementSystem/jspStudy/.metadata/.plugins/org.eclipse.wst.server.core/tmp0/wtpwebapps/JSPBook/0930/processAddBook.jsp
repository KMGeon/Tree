<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="ch04.dao.BookRepository"%>
<%@page import="ch04.vo.BookVO"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
      <%
      request.setCharacterEncoding("UTF-8");
      
      String bookId = request.getParameter("bookId");
      String name = request.getParameter("name");
      int unitPirce = Integer.parseInt(request.getParameter("unitPirce"));
      String author = request.getParameter("author");
      String publisher = request.getParameter("publisher");
      String releaseDate = request.getParameter("releaseDate");
      int totalPages = Integer.parseInt(request.getParameter("totalPages"));
      String description = request.getParameter("description");
      String category = request.getParameter("category");
      int unitsInStock = Integer.parseInt(request.getParameter("unitsInStock"));
      String condition = request.getParameter("condition");

      System.out.print(bookId);

      BookRepository dao=BookRepository.getInstance();
      
      BookVO addBook =new BookVO();
      addBook.setBookId(bookId);
      addBook.setName(name);
      addBook.setUnitPrice(unitPirce);
      addBook.setAuthor(author);
      addBook.setPublisher(publisher);
      addBook.setReleaseDate(releaseDate);
      addBook.setTotalPages(totalPages);
      addBook.setDescription(description);
      addBook.setCategory(category);
      addBook.setUnitsInStock(unitsInStock);
      addBook.setCondition(condition);
      dao.addBook(addBook);
      response.sendRedirect("products.jsp");
      %>
      
      
   <p>도서 코드 : <%=bookId%></p>
   <p>도서 명 : <%=name%></p>
   <p>가격 : <%=unitPirce%></p>
   <p>저자 : <%=author%></p>
   <p>출판사 : <%=publisher%></p>
   <p>출판일 : <%=releaseDate%></p>
   <p>총페이지 수 : <%=totalPages%></p>
   <p>상세 정보 : <%=description%></p>
   <p>분류 : <%=category%></p>
   <p>재고수 : <%=unitsInStock%></p>
   <p>상태 : <%=condition%></p>
      
</body>
</html>