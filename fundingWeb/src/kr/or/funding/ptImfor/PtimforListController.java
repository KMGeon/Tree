package kr.or.funding.ptImfor;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.funding.comm.service.AtchFileServiceImpl;
import kr.or.funding.comm.service.IAtchFileService;
import kr.or.funding.comm.vo.AtchFileVO;

@WebServlet(value="/ptimfor/ptimforlist.do")
public class PtimforListController extends HttpServlet{
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		IAtchFileService atchfileService = AtchFileServiceImpl.getInstance();
//		List<AtchFileVO> atchfileList = atchfileService.atchfileAll();
//		req.setAttribute("atchfileList", atchfileList);
		

		IPtImforService ptimforService = PtImforServiceImpl.getInstance();
		
		List<PtImforVO> atchList = ptimforService.atchAll();
		System.out.println("===========================");
		System.out.println(atchList);
		System.out.println("===========================");
		req.setAttribute("atchList", atchList);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/main.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
