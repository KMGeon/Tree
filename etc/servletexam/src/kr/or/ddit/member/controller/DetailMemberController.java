package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.service.IMemberService;
import kr.or.ddit.service.MemberServiceImpl;

@WebServlet("/member/detail.do")
public class DetailMemberController extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   
      String memId = req.getParameter("memId");

      // 1. 서비스 객체 생성하기
      IMemberService memService = MemberServiceImpl.getInstance();
      MemberVO mv = memService.getMember(memId);

      req.setAttribute("mv", mv);

      req.getRequestDispatcher("/view/member/detail.jsp").forward(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }

}