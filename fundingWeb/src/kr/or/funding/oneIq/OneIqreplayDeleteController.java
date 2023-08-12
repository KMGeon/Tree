package kr.or.funding.oneIq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/oneiq/oneiqreplydelete.do")
public class OneIqreplayDeleteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String iqNum = req.getParameter("IqNum");// 회원번호
		IOneIqService oneiqService = OneiqServiceImpl.getInstance();
		OneiqVO mv = oneiqService.getOneiq(iqNum);
				
		req.setAttribute("mv", mv);
		
		req.getRequestDispatcher("/view/oneiq_detail.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String cmmCnt = req.getParameter("cmmCnt");
		String iqNum = req.getParameter("iqNum");
		String mbsNum = req.getParameter("mbsNum");// 회원번호
		
		System.out.println("cmmCnt=" +cmmCnt);
		System.out.println("iqNum=" +iqNum);
		System.out.println("mbsNum=" +mbsNum);
		

		
		//2. 서비스 객체 생성하기
		IOneIqService oneiqService = OneiqServiceImpl.getInstance();
		
		// 3. 답글 등록하기
		OneiqVO mv = new OneiqVO();
		mv.setCmmCnt(cmmCnt);
		mv.setMbsNum(Integer.parseInt(mbsNum));
		mv.setIqNum(Integer.parseInt(iqNum));
		System.out.println();
		
		int cnt = oneiqService.replayDeleteOneiq(mv);
		
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
		
		//4 목록화면으로 이동
		
		String redirectUrl = req.getContextPath() + "/oneiq/oneiqlist.do";
		
		resp.sendRedirect(redirectUrl);
	}
}
