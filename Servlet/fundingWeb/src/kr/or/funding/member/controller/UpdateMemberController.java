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



@WebServlet("/member/update.do")
public class UpdateMemberController extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateMemberController() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/member/updateList.jsp").forward(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 요청파라미터 정보 가져오기
		String mbsId = req.getParameter("mbsId");
		
		String mbsPw = req.getParameter("mbsPw");
		
		String SHA256mbsPw = Sha256.testSHA256(mbsPw);
		

		String mbsMail = req.getParameter("mbsMail");
		String mbsAddr = req.getParameter("mbsAddr");
		String mbsPh = req.getParameter("mbsPh");
		String mbsNm = req.getParameter("mbsNm");
		System.out.println(mbsId);
		System.out.println(mbsPw);
		System.out.println(mbsMail);
		System.out.println(mbsAddr);
		System.out.println(mbsPh);
		System.out.println(mbsNm);

		// 2. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();

		// 3. 회원정보 수정하기
		MemberVO mv = new MemberVO();
		mv.setMbsId(mbsId);
		
		mv.setMbsPw(SHA256mbsPw);
		
		mv.setMbsPw(SHA256mbsPw);
		mv.setMbsMail(mbsMail);
		mv.setMbsAddr(mbsAddr);
		mv.setMbsPh(mbsPh);
		mv.setMbsNm(mbsNm);
		
		System.out.println(mv.getMbsPw());
		System.out.println(mv.getMbsMail());
		System.out.println(mv.getMbsAddr());
		System.out.println(mv.getMbsPh());
		System.out.println(mv.getMbsNm());

		int cnt = memService.modifyMember(mv);

		String msg = "";

		if (cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}

		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);

		// 4. 목록 조회 화면으로 이동
		String redirectUrl = req.getContextPath() +  "/ptimfor/ptimforlist.do";
		System.out.println(redirectUrl);
		resp.sendRedirect(redirectUrl);
	}

}
