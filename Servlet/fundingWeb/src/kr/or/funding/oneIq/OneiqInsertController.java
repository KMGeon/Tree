package kr.or.funding.oneIq;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value ="/oneiq/oneiqinsert.do")
public class OneiqInsertController extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.getRequestDispatcher("/Funding/view/oneiq_insert.jsp").forward(req, resp);
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			// 1. 파라미터 가져오기
			String iqTit = req.getParameter("iqTit");// 문의제목
			String iqCgy = req.getParameter("iqCgy");// 카테고리
			String iqCnt = req.getParameter("iqCnt");// 문의내용
			String mbsNum = req.getParameter("mbsNum");// 회원번호
			
			System.out.println(iqTit);
			System.out.println(iqCgy);
			System.out.println(iqCnt);
			System.out.println(mbsNum);
			
			
			// 2. 서비스 객체 생성
			IOneIqService oneiqService = OneiqServiceImpl.getInstance();
		
			// 3. 회원정보 등록하기
			OneiqVO mv = new OneiqVO();
			mv.setIqTit(iqTit);
			mv.setIqCgy(iqCgy);
			mv.setIqCnt(iqCnt);
			mv.setMbsNum(Integer.parseInt(mbsNum));
			
			int cnt = oneiqService.registerOneiq(mv);
			
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
