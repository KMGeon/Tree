package kr.or.funding.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.funding.comm.service.AtchFileServiceImpl;
import kr.or.funding.comm.service.IAtchFileService;
import kr.or.funding.comm.vo.AtchFileVO;
import kr.or.funding.crypt.Sha256;
import kr.or.funding.member.VO.MemberVO;
import kr.or.funding.member.service.IMemberService;
import kr.or.funding.member.service.MemberServiceImpl;

@MultipartConfig
@WebServlet("/member/insertSel.do")
public class InsertSellerController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/test/seller/join.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 파라미터 데이터 가져오기
		String mbsId = req.getParameter("mbsId");
		String mbsPw = req.getParameter("mbsPw");
		
		String SHA256mbsPw = Sha256.testSHA256(mbsPw);
		
		
		String mbsMail = req.getParameter("mbsMail");
		String mbsAddr = (req.getParameter("mbsAddr1") + " " + req.getParameter("mbsAddr2"));
		String brDt = (req.getParameter("brDt1") + " " + req.getParameter("brDt2") + " " + req.getParameter("brDt3"));
		String mbsPh = req.getParameter("mbsPh");
		String rfCd = req.getParameter("rfCd");
		String mbsNm = req.getParameter("mbsNm");
		

		System.out.println("id: "+mbsId);
		System.out.println("pw: "+mbsPw);
		System.out.println("mail: "+mbsMail);
		System.out.println("addr: "+mbsAddr);
		System.out.println("date: "+brDt);
		System.out.println("phone: "+mbsPh);
		System.out.println("이름: "+mbsNm);
		

		// 2. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		
		AtchFileVO atchFileVO = fileService.saveAtchFileList(req);
		
		System.out.println(req.getServletContext().getRealPath("/"));
		System.out.println(getServletContext().getRealPath("/"));

		// 3. 회원정보 등록하기
		MemberVO mv = new MemberVO();
	
		mv.setMbsId(mbsId);
		mv.setMbsPw(SHA256mbsPw);
		
		
		System.out.println(mbsPw);
		System.out.println(SHA256mbsPw);
		
		
		mv.setMbsMail(mbsMail);
		mv.setMbsAddr(mbsAddr);
		mv.setBrDt(brDt);
		mv.setMbsPh(mbsPh);
		mv.setRfCd(rfCd);
		mv.setMbsNm(mbsNm);
		mv.setAtchFileId(atchFileVO.getAtchFileId());
		
		
		System.out.println(mv.getMbsId());
		System.out.println(mv.getMbsPw());
		System.out.println(mv.getMbsMail());
		System.out.println(mv.getMbsAddr());
		System.out.println(mv.getBrDt());
		System.out.println(mv.getMbsPh());
		System.out.println(mv.getRfCd());
		System.out.println(mv.getMbsNm());
		System.out.println(atchFileVO.getAtchFileId());

		int cnt = memService.sellerMember(mv);
		String msg = "";
		if (cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}

		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);

		// 4. 목록 조회 화면으로 이동
		String redirectUrl = req.getContextPath() + "/ptimfor/ptimforlist.do";

		resp.sendRedirect(redirectUrl);
	}
}
