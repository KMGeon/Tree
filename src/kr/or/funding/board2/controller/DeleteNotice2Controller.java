package kr.or.funding.board2.controller;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.funding.board2.service.Notice2Service;
import kr.or.funding.board2.vo.Notice2VO;



@WebServlet("/notice2/notice2Delete.do")
public class DeleteNotice2Controller extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String notice2Id = req.getParameter("NtNum");
		
		// 1. 서비스 객체 생성하기
		Notice2Service notice2Service = Notice2Service.getInstance();
		
		// 2. 삭제 처리
			//Notice2VO mv = notice2Service.getNotice2(notice2Id);
			
			System.out.println("삭제cnt: "+notice2Id);
			int cnt = notice2Service.removeNotice2(notice2Id);	
			
			String msg = "";
			
			if(cnt > 0) {
				msg = "성공";
				System.out.println("삭제 완료.");
			} else {
				msg = "실패";
				System.out.println("삭제 실패.");
			}
			
			HttpSession session = req.getSession();
			session.setAttribute("msg", msg);
	
	
		resp.getWriter().write("OK");
		
		
		// 3. 목록화면으로 이동하기
		String redirectUrl = req.getContextPath() + "/notice2/notice2List.do";
		
		resp.sendRedirect(redirectUrl);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
