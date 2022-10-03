package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.multipart.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3, maxFileSize = 1024 * 1024 * 40, maxRequestSize = 1024 * 1024 * 50)
@WebServlet("/upload2.do")
public class UploadServlet2 extends HttpServlet {

	private static final String UPLOAD_DIR = "upload_files";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("multipart 파싱 전=>" + req.getParameter("sender"));

		String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIR;
//		separator는 / 기능을 한다.
		File uploadDir = new File(uploadPath);

		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			String fileName = "";
//			멀티파트여서 s붙음
			for (javax.servlet.http.Part part : req.getParts()) {
				System.out.println(part.getHeader("content-disposition"));

				fileName = getFileName(part);
				if (fileName != null && !fileName.equals("")) {
					part.write(uploadPath + File.separator + fileName);
					System.out.println("파일명" + fileName + "업로드 완료");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("파라미터 값: " + req.getParameter("sender"));
		resp.setContentType("text/html");
		resp.getWriter().print("업로드 완료...!!");
	}

	/*
	 * Part객체 파싱하여 파일이름 추출하기 param : part Part 객체 /return 파일명 : 존재하지 않으면 null 리턴함
	 */
	private String getFileName(javax.servlet.http.Part part) {
	/*
	 * Content-Disposiotion : form-data
	 * Content-Disposiotion : form-data; name="filedName"
	 * Content-Disposiotion : form-data ; name = "filedName";filename="a.jpg 
	 * split을 사용해서 배열을 만들어줌 
	 */
		for (String content : part.getHeader("Content-Disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
//				return에서 하는거는 filename=a.jpg 라는 배열이 들어오면 a.jpg를 추출하는 거
				return content.substring(content.indexOf("=")+1).trim().replace("\"", "");
			}
			
			
		}
		return null;
	}

}
