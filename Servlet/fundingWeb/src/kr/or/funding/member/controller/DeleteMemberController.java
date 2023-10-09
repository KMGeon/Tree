package kr.or.funding.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.funding.member.VO.MemberVO;
import kr.or.funding.member.service.IMemberService;
import kr.or.funding.member.service.MemberServiceImpl;

@WebServlet("/member/delete.do")
public class DeleteMemberController extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/member/DeleteMember.jsp").forward(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 요청파라미터 정보 가져오기
		String mbsId = req.getParameter("mbsId");
		String mbsPw = req.getParameter("mbsPw");
	
		System.out.println(mbsId);

		// 2. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();

		// 3. 회원정보 수정하기
		MemberVO mv = new MemberVO();
		mv.setMbsId(mbsId);
		mv.setMbsPw(mbsPw);
	
		
		System.out.println(mv.getMbsPw());
	

		int cnt = memService.removeMember(mv);

		String msg = "";

		if (cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}

		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);

		// 4. 목록 조회 화면으로 이동
		String redirectUrl = req.getContextPath() +  "/test/main/main.html";
		System.out.println(redirectUrl);
		resp.sendRedirect(redirectUrl);
	}

}