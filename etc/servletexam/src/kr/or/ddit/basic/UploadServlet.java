package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
	private static final String UPLOAD_DIR = "upload_files";
	private static final int MEMORY_THRESHODL = 1024 * 1024 * 3;// 메모리 임계크기 ( 이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 40;// 파일 한개당 최대 크기
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 요청 파일 최대 크기

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*
		 * System.out.println("================================================");
		 * 
		 * // Reader rd =new InputStreamReader(req.getInputStream());//byte기반 이니깐
		 * inputstreamreader로 묶음
		 * 
		 * BufferedReader br = new BufferedReader(new
		 * InputStreamReader(req.getInputStream()));
		 * 
		 * String readLine = ""; while((readLine=br.readLine())!=null) {
		 * System.out.println(readLine); }
		 * System.out.println("================================================");
		 */
		// multipart 파싱 전 파라미터 정보 조회해 보기
		System.out.println("multipart 파싱 전=>" + req.getParameter("sender"));
		if (ServletFileUpload.isMultipartContent(req)) {
			// 폼필드 데이터 저장용 맵
			Map<String, String> forMap = new HashMap<String, String>();

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHODL);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);

//			웹애플리케이션 루트 디렉토리 
			String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIR;

			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {

				List<FileItem> formItems = (List<FileItem>) upload.parseParameterMap(req);

				if (formItems != null && formItems.size() > 0) {
					for (FileItem item : formItems) {
						if (!item.isFormField()) { // 파일인 경우...
							String fileName = item.getName();
							String filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);

							item.write(storeFile);
							System.out.println("업로드 완료됨-> 파일명: " + fileName);
						} else { // 폼 데이터인 경우...
//							폼필드의 값이 한글인 경우에는 해당 문자열을 ㅈㄱ절히 변환을 해주어야 한다.
							forMap.put(item.getFieldName(), item.getString("UTF-8"));

						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
//			멀티파트 파싱 후 파라미터 정보 조회해 보기
			System.out.println("MULTIPART 파싱 후" + forMap.get("sender"));
			resp.setContentType("text/html");
			resp.getWriter().print("업로드 서블릿 종료됨");
		}
	}

}
