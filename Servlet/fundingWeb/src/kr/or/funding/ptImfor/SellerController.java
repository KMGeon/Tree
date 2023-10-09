package kr.or.funding.ptImfor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value = "/ptimfor/seller.do")
public class SellerController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ptNum = req.getParameter("ptImforNum");
		IPtImforService ptimForService = PtImforServiceImpl.getInstance();
		
		PtImforVO pvo = new PtImforVO();
		
		pvo.setPtNum(Integer.parseInt(ptNum));
		
		List<PtImforVO>  pv= (List<PtImforVO>) ptimForService.selectDetail(pvo);
		
		req.setAttribute("ptDetail", pv);
		req.getRequestDispatcher("/view/goods_order.jsp").forward(req, resp);
		
		
		
//		String ptNum = req.getParameter("ptImforNum");
//		IPtImforService ptimForService = PtImforServiceImpl.getInstance();
//		
//		PtImforVO pvo = new PtImforVO();
//		
//		pvo.setPtNum(Integer.parseInt(ptNum));
//		List<PtImforVO> seller = (List<PtImforVO>) ptimForService.selectDetail(pvo);
//		
//		
//		
//		req.setAttribute("ptDetail", seller);
//		req.getRequestDispatcher("/view/notice_seller.jsp").forward(req, resp);
//	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

	}
}
