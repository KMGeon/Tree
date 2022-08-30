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

@WebServlet("/member/insert.do") // 하나만 쓸떄는 생략해도 된다는게 뭔말일까유 // (value=??) 이걸 안써도 되는건가?
public class InsertMemberController extends HttpServlet {

//	doget
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		getRequestDispatcher.forward메서드를 이용하여 페이지를 이동시키는 메서드
//		이동하면서 내장객체 req , resp를 넘겨준다.
//		getRequestDispatcher() 보통 , 목록보기 , 수정폼(추가) , 삭제폼
//		forward(req , resp) 두 객체는 해당주소에 대해 요청하고 응답하라는 뜻
		req.getRequestDispatcher("/view/member/insertForm.jsp").forward(req, resp);
	}

//	dopost
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 파라미터 데이터 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");

		// 2. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();

		// 3. 회원정보 등록하기
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);

		
		// registerMember메서드 : MemberVO에 담겨진 자료를 DB에 insert하는 메서드
		// mv DB에 insert할 자료가 저장된 MemberVO객체 DB작업이
		// 성공하면 1 이상의 값이 반환되고, 실패하면 0이 반환됨
		int cnt = memService.registerMember(mv);

		String msg = "";
		if (cnt > 0) {
			// 성공
			msg = "성공";
		} else {
			// 실패
			msg = "실패";
		}

		HttpSession session = req.getSession();
//		session에 "msg" 객체에 msg를 넣는다.
		session.setAttribute("msg", msg);

		// 4. 목록조회 화면으로 이동
//		req.getRequestDispatcher("/member/list.do").forward(req, resp);

		String redirectUrl = req.getContextPath() + "/member/list.do";

		resp.sendRedirect(redirectUrl);
	}
}
