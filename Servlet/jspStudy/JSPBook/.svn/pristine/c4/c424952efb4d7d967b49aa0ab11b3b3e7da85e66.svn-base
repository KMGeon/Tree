<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.DiskFileUpload"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	
	String fileUploadPath = "C:\\upload";

	DiskFileUpload upload = new DiskFileUpload();
	
	List items = upload.parseRequest(request);
	Iterator params = items.iterator();
	while(params.hasNext()){
		FileItem fileItem = (FileItem)params.next();
		if(!fileItem.isFormField()){	
			String fileFieldName = fileItem.getFieldName(); 
			String contentType = fileItem.getContentType(); 
			long fileSize = fileItem.getSize(); 
			String fileName = fileItem.getName();		
			fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
			File file = new File(fileUploadPath+"/"+fileName);
			fileItem.write(file);
			out.println("요청 파라미터 이름 : " + fileFieldName + "<br />");
			out.println("저장 파일 이름: " + fileName + "<br />"); 
			out.println("파일 콘텐츠 타입: " + contentType + "<br />"); 
			out.println("파일 크기: " +fileSize);
		}
	}
	%>	