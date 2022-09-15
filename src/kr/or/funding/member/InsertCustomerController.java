
package kr.or.funding.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/insertCus.do")
public class InsertCustomerController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/test/accounts/join.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 파라미터 데이터 가져오기
		String mbsId = req.getParameter("mbsId");
		String mbsPw = req.getParameter("mbsPw");
		String mbsMail = req.getParameter("mbsMail");
		String mbsAddr = (req.getParameter("mbsAddr1")+" "+req.getParameter("mbsAddr2"));
		
		String brDt = req.getParameter("brDt");
		String mbsPh = req.getParameter("mbsPh");
		String rfCd = req.getParameter("rfCd");
		String mbsAhy = req.getParameter("mbsAhy");
		String mbsNm = req.getParameter("mbsNm");

		// 2. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();

		// 3. 회원정보 등록하기
		MemberVO mv = new MemberVO();
		mv.setMbsId(mbsId);
		mv.setMbsPw(mbsPw);
		mv.setMbsMail(mbsMail);
		mv.setMbsAddr(mbsAddr);
		mv.setBrDt(brDt);
		mv.setMbsPh(mbsPh);
		mv.setRfCd(rfCd);
		mv.setMbsNm(mbsNm);

		int cnt = memService.registerMember(mv);

		String msg = "";
		if (cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}

		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);

		// 4. 목록 조회 화면으로 이동
//		req.getRequestDispatcher("/accounts/join.html").forward(req, resp);

		String redirectUrl = req.getContextPath() + "/test/main/main.html";

		resp.sendRedirect(redirectUrl);
	}
}
 

