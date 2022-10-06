<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.DiskFileUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 파일 단순 복사 -->

<%
//1)폼 페이지에서 전송된 파일을 저장할 서버의 경로를 작성(서버에 저장)
String fileuploadPath = "C:\\upload";
//2)파일 업로드를 위해 commons-fileUpload 패키지에 포함되어 있는 DiskFileUpload객체를 생성
DiskFileUpload upload = new DiskFileUpload();

//업로드 객체 환경설정
upload.setSizeMax(1000000);//업로드 최대크기(1mb)
upload.setSizeThreshold(4096); //메모리상에 저장할 최대 크기 (4kb)
upload.setRepositoryPath(fileuploadPath); //업로드된 파일을 입시로 저장할 경로

//3)웹 브라우저가 전송한 multipart/form-data 유형의 요청 파라미터를 가져옴
//폼 페이지에서 전송된 요청 파라미터를 전달받음

//파싱 : 구문분석 + 의미분석
List items = upload.parseRequest(request); //리스트에 파일이 담긴다.

//4)폼 페이지에서 전송된 요청 파라미터를 iterator 클래스로 변환
Iterator params = items.iterator();

//5)폼 페이지에서 정송된 요청 파라미터가 없을 때까지 반복

while (params.hasNext()) {
	//6)FileItem클래스의 메소드를 사용하여 요청 파라미터가 일반 데이터인지 파일인지 분석 및 처리
	FileItem fileItem = (FileItem) params.next();

	if (fileItem.isFormField()) { //여기서 formfiled란 input type="text" / check박스를 의미한다. / 이름과 제목이 isformfiled에 해당한다.
		//일반 데이터라면 (name , subject)
		String name = fileItem.getFieldName(); //파라미터 이름
		String value = fileItem.getString("UTF-8"); //파라미터 값
		out.print(name + ":" + value);
	} else {
		String fileFieldName = fileItem.getFieldName();//요철 파라미터 이름(filename)
		String contentType = fileItem.getContentType(); //파일 콘텐츠 유형(mine타입)->이미지 , jar , hwp인지 
		long fileSize = fileItem.getSize(); //파일크기 

		//7)클라이언트에서 업로드할 대상 찾기 , 일반 데이터가 아니면 파일 일것임
		//filename = 		c://user//cake.jpg
		String fileName = fileItem.getName();
		fileName = fileName.substring(fileName.lastIndexOf("//") + 1); //cake.jpg

		//8)서버로 업로드
		File file = new File(fileuploadPath + "/" + fileName);
		//서버로 복사 실행
		fileItem.write(file);

		out.print("--------------------" + "<br/>");
		out.print("요청 파라미터 이름:" + fileFieldName + "<br/>"); //filename
		out.print("저장 파일 이름:" + fileName + "<br/>"); //cake.jpg
		out.print("파일 콘텐츠 타입" + contentType + "<br/>"); //jpg
		out.print("파일 크기:" + fileSize + "<br/>"); //68KB
	}
}
%>