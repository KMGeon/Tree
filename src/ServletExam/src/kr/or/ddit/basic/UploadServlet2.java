package kr.or.ddit.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

// Multipart로 온다는 것을 알려준다.
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3
				, maxFileSize = 1024 * 1024 * 40
				, maxRequestSize = 1024 * 1024 * 50)
@WebServlet("/upload2.do")
public class UploadServlet2 extends HttpServlet {

	private static final String UPLOAD_DIR = "upload_files";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// multipart 파싱 전 파라미터 정보 조회해보기
		System.out.println("Multipart 파싱 전 =>" + req.getParameter("sender"));

		// 웹애플리케이션 루트 디렉토리 기준 업로드 경로 설정하기
		String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIR;

		File uploadDir = new File(uploadPath);

		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			String fileName = "";
			
			for(Part part : req.getParts()) {
				System.out.println(part.getHeader("content-disposition"));
				
				fileName = getFileName(part);
				
				if(fileName != null && !fileName.equals("")) {
					part.write(uploadPath + File.separator + fileName);
					System.out.println("파일명: " + fileName + " 업로드 완료!");
				}
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("파라미터 값: " + req.getParameter("sender"));
		
		resp.setContentType("text/html");
		resp.getWriter().print("업로드 완료...!!!");

	}
	
	/**
	 * Part객체 파싱하여 파일이름 추출하기
	 * @param part Part객체
	 * @return 파일명 : 존재하지 않으면 null 리턴함(폼필드인 경우...) 
	 */
	private String getFileName(Part part) {
		/*
		 * Content-Disposition: form-data
		 * Content-Disposition: form-data; name="fieldName"
		 * Content-Disposition: form-data; name="fieldName"; filename="a.jpg"
		 * */
		
		for(String content : part.getHeader("Content-Disposition").split(";")) {

			if(content.trim().startsWith("filename")) { // filename만 있는지 확인
				// return은 filename a.jpg만 짤라내기
				return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
			}
		}
		
		return null; // filename이 존재하지 않는 경우..(폼필드)
	}

}
