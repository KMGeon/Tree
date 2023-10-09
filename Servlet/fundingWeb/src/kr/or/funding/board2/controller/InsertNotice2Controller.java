package kr.or.funding.board2.controller;

import java.io.File;
import java.io.IOException;

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



@MultipartConfig
@WebServlet("/notice2/notice2Insert.do")
public class InsertNotice2Controller extends HttpServlet {
	
	  private static final String MBS_NUM = "1";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/Funding/view/board/boardInsert.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String ntNum = req.getParameter("ntNum"); 
		String ntClf = req.getParameter("ntClf"); 
		String ntTit = req.getParameter("ntTit"); 
		String ntCnt = req.getParameter("ntCnt"); 
		
		
		Notice2Service notice2Service = Notice2Service.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();

		// 첨부파일 저장하기
		AtchFileVO atchFileVO = fileService.saveAtchFileList(req);
		
		Notice2VO mv = new Notice2VO();
		mv.setNtNum(ntNum);
		mv.setNtClf(ntClf);
		mv.setNtTit(ntTit);
		mv.setNtCnt(ntCnt);
		
		
		
		mv.setAtchFileId(-1);
		
		
		int cnt = notice2Service.registerNotice2(mv);
		
		String msg ="";
		if(cnt >0) {
			// 성공
			msg = "성공";
		}else {
			// 실패
			msg = "실패";
		}
		
		
		
		// 4. 목록 화면으로 이동.
		
		String redirectUrl = req.getContextPath() + "/notice2/notice2List.do";
		
		resp.sendRedirect(redirectUrl);
	}
}












