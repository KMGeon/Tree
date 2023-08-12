<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.fileupload.DiskFileUpload"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.BookRepository"%>
<%@ page import="vo.BookVO"%>
<%@ page import="java.io.File"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>

<%
	//폼 페이지에서 입력된 데이터를 서버(톰켓)로 전송 시 한글이 깨지지 않도록 하자
	request.setCharacterEncoding("UTF-8");

	// 1) 어디에 저장할 것인가?
	String path = "D:\\A_TeachingMaterial\\3.HighJava\\workspace\\JSPBook\\WebContent\\resources\\images";
	// 2) 파일 업로드 객체 생성
	DiskFileUpload upload = new DiskFileUpload();
	// 3) 환경설정. bit -> bytes -> Kbytes -> Mbytes -> Gbytes
	upload.setSizeMax(1000000);  // 1Mbytes
	upload.setSizeThreshold(4096);  // 메모리 최대 크기(4M)
	upload.setRepositoryPath(path);  // 임시 저장 경로
	// 4) 폼 페이지의 item 받아오기. 
	List items = upload.parseRequest(request);
	// 5) 열거형 타입으로 변환
	Iterator params = items.iterator();

    String bookId = "";
    String name = "";
    String unitPrice = "";
	String author = "";
	String publisher = "";
	String releaseDate = "";
	String totalPages = "";
	String description = "";
	String category = "";
	String unitsInStock = "";
	String condition = "";
	String fileName = "";
	// 가격은 숫자형
	int price = 0;
	// 도서 재고수도 숫자형
	int stock = 0;
	// 총 페이지수도 숫자형
	int pages = 0;
 
	BookRepository dao = BookRepository.getInstance();
	

	// 6) 요청 파라미터가 없을 때까지 반복
		while(params.hasNext()) {
			FileItem item = (FileItem)params.next();	
			// Spring 프레임워크에서는 MultipartFile로 쉽게 처리됨
			if(item.isFormField()) {  // 6-1) 일반 데이터
				// 6-1-1) 파라미터의 이름 가져오기
				if(item.getFieldName().equals("bookId")) {
					// 6-1-2) 파라미터의 값 가져오기
					bookId = item.getString("UTF-8");
				} else if (item.getFieldName().equals("name")) {
					name = item.getString("UTF-8");
				} else if (item.getFieldName().equals("unitPrice")) {
					unitPrice = item.getString("UTF-8");
					
					// 폼 페이지에서 상품 가격이 입력되지 않았다면 0으로 처리
					if (unitPrice.isEmpty()) {
						price = 0;
					} else {
						price = Integer.parseInt(unitPrice);
					}
					
				} else if (item.getFieldName().equals("author")) {
					author = item.getString("UTF-8");
				} else if (item.getFieldName().equals("publisher")) {
					publisher = item.getString("UTF-8");
				} else if (item.getFieldName().equals("releaseDate")) {
					releaseDate = item.getString("UTF-8");
				} else if (item.getFieldName().equals("totalPages")) {
					totalPages = item.getString("UTF-8");
					
					if (totalPages.isEmpty()) {
						pages = 0;
					} else {
						pages = Integer.parseInt(totalPages);
					}
					
				} else if (item.getFieldName().equals("description")) {
					description = item.getString("UTF-8");
				} else if (item.getFieldName().equals("category")) {
					category = item.getString("UTF-8");
				} else if (item.getFieldName().equals("unitsInStock")) {
					unitsInStock = item.getString("UTF-8");
					
					if (unitsInStock.isEmpty()) {
						stock = 0;
					} else {
						stock = Integer.parseInt(totalPages);
					}
				
				} else if (item.getFieldName().equals("condition")) {
					condition = item.getString("UTF-8");
				} 				
			} else {  // 6-2) 파일 데이터
				// <input type="file" name="productImage" class="form-control" />
				String fileFieldName = item.getFieldName();  // 요청 파라미터명
				fileName = item.getName();  // 저장 파일명(P1237.png)
				String contentType = item.getContentType();  // images/jpeg
				// 순수한 파일명만 추출
				fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
				long fileSize = item.getSize();  // 파일 크기
				// 파일 객체 생성(copy 설계)
				File file = new File(path +"/" + fileName);
				// copy 실행
				item.write(file);
				
				out.println("----------------<br />");
				out.println("요청 파라미터 명: " + fileFieldName + "<br />");
	            out.println("저장 파일 이름: " + fileName + "<br />");
	            out.println("파일 콘텐츠 유형: " + contentType + "<br />");
	            out.println("파일 크기: " + fileSize + "<br />");
			}
			
		}  // end while
		
	// 빈(Bean) 객체에 데이터를 태워서 add하자
	BookVO addBook = new BookVO();
	addBook.setBookId(bookId);
	addBook.setName(name);
	addBook.setUnitPrice(price);
	addBook.setAuthor(author);
	addBook.setPublisher(publisher);
	addBook.setReleaseDate(releaseDate);
	addBook.setTotalPages(pages);
	addBook.setDescription(description);
	addBook.setCategory(category);
	addBook.setUnitsInStock(stock);
	addBook.setCondition(condition);
	addBook.setFileName(fileName);
	
	// insert into book(......) values(.....);
	// 폼 페이지에서 입력된 데이터를 저장하도록
	// BookRepository 클래스의  addBook 메소드를 호출
	dao.addBook(addBook);
	
	// books.jsp로 강제 이동
	response.sendRedirect("books.jsp");
%>
