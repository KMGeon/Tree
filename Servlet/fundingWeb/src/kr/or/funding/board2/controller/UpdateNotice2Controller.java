package kr.or.funding.board2.controller;

import java.io.File;
import java.io.IOException;  
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.funding.board2.service.Notice2Service;
import kr.or.funding.board2.vo.Notice2VO;
import kr.or.funding.comm.service.AtchFileServiceImpl;
import kr.or.funding.comm.service.IAtchFileService;
import kr.or.funding.comm.vo.AtchFileVO;



/**
 * Servlet implementation class UpdateMemberController
 */
@MultipartConfig
@WebServlet("/notice2/notice2Update.do")
public class UpdateNotice2Controller extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      String notice2Id = req.getParameter("NtNum");
      
      // 1. 서비스 객체 생성하기
      Notice2Service notice2Service = Notice2Service.getInstance();
      Notice2VO mv = notice2Service.getNotice2(notice2Id);
      
      IAtchFileService fileService = AtchFileServiceImpl.getInstance();
      
      if(mv.getAtchFileId() > 0) { // 첨부파일 존재
    	  // 1-2. 첨부파일 정보 조회
    	  AtchFileVO fileVO = new AtchFileVO();
    	  fileVO.setAtchFileId(mv.getAtchFileId());
    	  
    	  List<AtchFileVO> atchFileList = 
    			  fileService.getAtchFileList(fileVO);
    	  
    	  req.setAttribute("atchFileList", atchFileList);
      }
      
      req.setAttribute("mv", mv);
      
      req.getRequestDispatcher("/view/board/boardUpdate.jsp").forward(req, resp);
      
      
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse resp)
    */
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   
		// 1. 파라미터 데이터 가져오기
		String ntNum = req.getParameter("ntNum");
		String ntClf = req.getParameter("ntClf");
		String ntTit = req.getParameter("ntTit");
		String ntCnt = req.getParameter("ntCnt");
		//String atchFileId = req.getParameter("atchFileId");
		System.out.println(ntNum);
		System.out.println(ntClf);
		System.out.println(ntTit);
		System.out.println(ntCnt);
		
		
		// 2. 서비스 객체 생성하기
		Notice2Service notice2Service = Notice2Service.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		// 첨부파일 저장하기
		AtchFileVO atchFileVO = fileService.saveAtchFileList(req);
		 
	   // 3. 회원정보 수정하기
		Notice2VO mv = new Notice2VO();
		mv.setNtNum(ntNum);
		mv.setNtClf(ntClf);
		mv.setNtTit(ntTit);
		mv.setNtCnt(ntCnt);
		
		
		
//	   if(atchFileVO == null) { // 새로운 첨부파일이 존재하지 않으면
//		   // 기존 첨부 파일 ID 설정하기
//		   mv.setAtchFileId(Long.parseLong(atchFileId));
//	   } else { 
//		   // 신규 첨부 파일 ID 설정하기
//		   mv.setAtchFileId(atchFileVO.getAtchFileId());
//	   }
	   
	   
	   int cnt = notice2Service.modifyNotice2(mv);
	   
	   String msg = "";
	   
	   if(cnt > 0) {
		   msg = "성공";
	   }else {
		   msg = "실패";
	   }
	   
	  
	   HttpSession session = req.getSession();
	   session.setAttribute("msg", msg);
	   
	  
	   // 4. 목록 조회 화면으로 이동
	   String redirectUrl  = req.getContextPath() + "/notice2/notice2List.do";
	   
	   resp.sendRedirect(redirectUrl);
	   
   }

}