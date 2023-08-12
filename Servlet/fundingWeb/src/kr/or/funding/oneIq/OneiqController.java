package kr.or.funding.oneIq;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/oneiq/oneiqlist.do")
public class OneiqController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		IOneIqService oneiqService = OneiqServiceImpl.getInstance();
		
		
		List<OneiqVO> oneiqList = oneiqService.findAll();
		
		req.setAttribute("oneiqList", oneiqList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/oneiq_notice.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
