package kr.or.funding.board2.controller;

import java.io.IOException; 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.funding.board2.service.Notice2Service;
import kr.or.funding.board2.vo.Notice2VO;
import kr.or.funding.comm.service.AtchFileServiceImpl;
import kr.or.funding.comm.service.IAtchFileService;
import kr.or.funding.comm.vo.AtchFileVO;


@WebServlet("/notice2/notice2Detail.do")
public class DetailNotice2Controller extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		String notice2Id = req.getParameter("NtNum");
	      System.out.println(notice2Id);
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
	      
	      req.getRequestDispatcher("/view/board/boardDetail.jsp").forward(req, resp);
	      
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
