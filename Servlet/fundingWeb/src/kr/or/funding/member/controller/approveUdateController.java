package kr.or.funding.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.funding.member.VO.MemberVO;
import kr.or.funding.member.service.IMemberService;
import kr.or.funding.member.service.MemberServiceImpl;

@WebServlet("/member/approveUdate.do")
public class approveUdateController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mbsId = req.getParameter("mbsId");

		// 1. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		MemberVO mv = new MemberVO();
		mv.setMbsId(mbsId);
		System.out.println(mbsId);
		int cnt = memService.agreeSeller(mv);

		String msg = "";

		if (cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}

		req.setAttribute("mv", mv);

		resp.sendRedirect("/Funding/member/approve.do");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
