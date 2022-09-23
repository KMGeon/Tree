package kr.or.funding.ptImfor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/ptimfor/ptImforDetail.do")
public class DetailController extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String ptNum = req.getParameter("ptImforNum");
	IPtImforService ptimForService = PtImforServiceImpl.getInstance();
	
	PtImforVO pvo = new PtImforVO();
	
	pvo.setPtNum(Integer.parseInt(ptNum));
	
	List<PtImforVO>  pv= (List<PtImforVO>) ptimForService.selectDetail(pvo);
	
	req.setAttribute("ptDetail", pv);
	req.getRequestDispatcher("/view/goodsDetail.jsp").forward(req, resp);
	
	
	//req.getRequestDispatcher("/View/goodsDetail.jsp").forward(req, resp);
	//resp.sendRedirect("/Funding/view/goodsdetail.jsp");
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);


	}
}
