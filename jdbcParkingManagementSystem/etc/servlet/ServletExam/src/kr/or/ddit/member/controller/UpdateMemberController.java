package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.service.IMemberService;
import kr.or.ddit.service.MemberServiceImpl;

@WebServlet("/member/update.do")
public class UpdateMemberController extends HttpServlet {

	public UpdateMemberController() {
		super();
	}

	// doget
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String memId = req.getParameter("memId");

		// 1. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		MemberVO mv = memService.getMember(memId);

		req.setAttribute("mv", mv);

		req.getRequestDispatcher("/view/member/updateForm.jsp").forward(req, resp);
	}

//	dopost
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 요청파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");

		// 2. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();

		// 3. 회원정보 수정하기
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);

		int cnt = memService.modifyMember(mv);

		String msg = "";

		if (cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}

//		req.setAttribute("msg", msg);  // 못받음 이유 몰라

		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);

		// 4. 목록 조회 화면으로 이동
		String redirectUrl = req.getContextPath() + "/member/list.do";

		resp.sendRedirect(redirectUrl);
	}

}
