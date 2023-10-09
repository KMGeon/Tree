package kr.or.funding.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.funding.crypt.Sha256;
import kr.or.funding.member.VO.MemberVO;
import kr.or.funding.member.service.IMemberService;
import kr.or.funding.member.service.MemberServiceImpl;

@WebServlet("/login.do")
public class LoginCotroller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String mbsId = req.getParameter("mbsId");
		String mbsPw = req.getParameter("mbsPw");

		String SHA256mbsPw = Sha256.testSHA256(mbsPw);

		// 2. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		// 3. 회원정보 등록하기
		MemberVO mv = new MemberVO();
		mv.setMbsId(mbsId);
		mv.setMbsPw(SHA256mbsPw);
		mv = memService.login(mv);

		if (mv == null) {
			req.setAttribute("fail", "로그인 실패");
			String redirectUrl = req.getContextPath() + "/view/logout.jsp";
			System.out.println(redirectUrl);
			resp.sendRedirect(redirectUrl);
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("memList", mv);
			String redirectUrl = req.getContextPath() + "/ptimfor/ptimforlist.do";
			System.out.println(redirectUrl);
			resp.sendRedirect(redirectUrl);
		}

	}

}
