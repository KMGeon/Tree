package kr.or.funding.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.funding.member.service.IMemberService;
import kr.or.funding.member.service.MemberServiceImpl;

@WebServlet(value="/member/idCheck.do")
public class IdCheckController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.파라미터 데이터 가져오기
		String mbsId = req.getParameter("userId");
		System.out.println("넘어온 아이디값: "+ req.getParameter("userId"));
		
		IMemberService memService = MemberServiceImpl.getInstance();
		
		boolean idCheck = memService.checkMember(req.getParameter("userId"));
		
		System.out.println("DB조회 결과: " + idCheck);
		
		if(idCheck==false) {
			resp.getWriter().write("NO");
		}else {
			resp.getWriter().write("OK");
		}
				

	}
	
}
