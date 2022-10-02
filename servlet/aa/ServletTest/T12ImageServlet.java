//package kr.or.ddit.basic;
//
//import java.io.*;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//
//public class T12ImageServlet extends HttpServlet {
//	@Override
//   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      resp.setContentType("image/jpg");
//      ServletOutputStream out = resp.getOutputStream();
//      FileInputStream fis = new FileInputStream("C:\\xampp\\htdocs\\jsstudy\\img\1.jpg");
//     BufferedInputStream bis = new BufferedInputStream(fis); 
//     BufferedInputStream bos = new BufferedInputStream(out); 
//     
//     int byteCnt =0 ;
//     while(byteCnt = bis.read()!=-1) {
//    	 bos.write(byteCnt);
//     }
//     bis.close();
//     bos.close();
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doGet(req, resp);
//	}
//}