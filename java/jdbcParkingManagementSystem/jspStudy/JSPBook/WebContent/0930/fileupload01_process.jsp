<%@ page contentType="text/html; charset=UTF-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<!-- cos.jar -->
<%
	DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
	//request : JSP 내장객체(요청 정보를 담고있음)
	//10*1024*1024 : 10Mb
	MultipartRequest multipartRequest = 
			new MultipartRequest(request,"C:\\upload", 10*1024*1024, "utf-8", policy);
// 	String file = (String)multipartRequest.getFileNames().nextElement();
	Enumeration params = multipartRequest.getFileNames();
	//폼 필드 내에 객체가 있을때까지 반복
	//params : <input type="file" name="file1">
	while(params.hasMoreElements()){
		//요청 파라미터 이름 : file1
		String file = (String)params.nextElement();
		//실제 파일 이름 : cake03.jpg
		String original = multipartRequest.getOriginalFileName(file);
		//저장 파일 이름 : cake032.jpg
		String filename = multipartRequest.getFilesystemName(file);
		//파일 콘텐츠 유형 : image/jpeg
		String type = multipartRequest.getContentType(file);
		
		File files = multipartRequest.getFile(file);
%>
		<p> 요청 파라미터 이름 :  <%=file%>
		<p> 실제 파일 이름 : <%=original%>
		<p> 저장 파일 이름 : <%=filename%>
		<p> 파일 콘텐츠 유형 : <%=type%>
<%	
		//파일 크기 : 78258
		if(files != null){
			out.print("<p>파일 크기 :" + files.length() + "</p>");
		}
	}
%>




