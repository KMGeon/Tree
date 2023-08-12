package kr.or.funding.oneIq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value = "/oneiq/oneiqupdate.do")
public class OneiqUpdateController extends HttpServlet{
	
	public OneiqUpdateController() {
		super();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String iqNum = req.getParameter("IqNum");// 회원번호
			IOneIqService oneiqService = OneiqServiceImpl.getInstance();
			OneiqVO mv = oneiqService.getOneiq(iqNum);
					
			req.setAttribute("mv", mv);
			
			req.getRequestDispatcher("/view/oneiq_update.jsp").forward(req, resp);
			
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1 파리미터 가져오기
		req.setCharacterEncoding("UTF-8");
		// 1. 파라미터 가져오기
		String iqTit = req.getParameter("iqTit");// 문의제목
		String iqCgy = req.getParameter("iqCgy");// 카테고리
		String iqCnt = req.getParameter("iqCnt");// 문의내용
		String iqNum = req.getParameter("iqNum");
		
		System.out.println(iqTit);
		System.out.println(iqCgy);
		System.out.println(iqCnt);
		String mbsNum = req.getParameter("mbsNum");// 회원번호
			
		
		
		// 2. 서비스 객체 생성하기
		IOneIqService oneiqService = OneiqServiceImpl.getInstance();
		
		// 3. 게시글 수정하기
		OneiqVO mv = new OneiqVO();
		mv.setIqTit(iqTit);
		mv.setIqCgy(iqCgy);
		mv.setIqCnt(iqCnt);
		mv.setMbsNum(Integer.parseInt(mbsNum));
		mv.setIqNum(Integer.parseInt(iqNum));
		System.out.println();
		
		int cnt = oneiqService.modifyOneiq(mv);
		
		String msg ="";
		if(cnt >0) {
			// 성공
			msg = "성공";
		}else {
			// 실패
			msg = "실패";
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);
		
		
		// 4. 목록 화면으로 이동
		
		String redirectUrl = req.getContextPath() + "/oneiq/oneiqlist.do";
		
		resp.sendRedirect(redirectUrl);
		
		
	}

}
