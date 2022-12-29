package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.service.IMemberService;
import kr.or.ddit.service.MemberServiceImpl;

@WebServlet("/member/delete.do")
public class DeleteMemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String memId = req.getParameter("memId");

		// 1. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();

		// 2. 삭제처리
		int cnt = memService.removeMember(memId);

		String msg = "";

		if (cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}

		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);

		// 3. 목록화면으로 이동하기
		String redirectUrl = req.getContextPath() + "/member/list.do";

		resp.sendRedirect(redirectUrl);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}