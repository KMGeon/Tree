package kr.or.funding.oneIq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value ="/oneiq/oneiqdelete.do")
public class OneiqDeleteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String iqNum = req.getParameter("IqNum");// 게시글 번호
		
		// 1. 서비스객체 생성하기
		IOneIqService oneiqService = OneiqServiceImpl.getInstance();
		
		// 2. 삭제처리
		int cnt = oneiqService.removeOneiq(iqNum);
		
	     String msg = "";
	      
	      if(cnt >0) {
	    	  msg = "성공";
	      }else {
	    	  msg = "실패";
	      }
		
	      HttpSession session = req.getSession();
	      session.setAttribute("msg", msg);
	      
	      // 3. 목록화면으로 이동하기
	      String redirectUrl = req.getContextPath() + "/oneiq/oneiqlist.do";
	      
	      resp.sendRedirect(redirectUrl);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
