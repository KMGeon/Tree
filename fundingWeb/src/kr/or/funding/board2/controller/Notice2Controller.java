package kr.or.funding.board2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.funding.board2.service.Notice2Service;
import kr.or.funding.board2.vo.Notice2VO;

	@WebServlet(value="/notice2/notice2List.do")

	public class Notice2Controller extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Notice2Service notice2Service = Notice2Service.getInstance();
		
		
		List<Notice2VO> notice2List = notice2Service.getAllNotice2List();
		
		req.setAttribute("notice2List", notice2List);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/board/boardList.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}

