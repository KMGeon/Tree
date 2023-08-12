package kr.or.funding.ptImfor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.funding.comm.service.AtchFileServiceImpl;
import kr.or.funding.comm.service.IAtchFileService;
import kr.or.funding.comm.vo.AtchFileVO;

@MultipartConfig
@WebServlet("/ptimfor/insertptimfor.do")
public class PtImforController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/test1/notice_seller/register_funding.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 파라미터 데이터 가져오기
				
				String ptNm = req.getParameter("ptNm");
				String mbsNum = req.getParameter("mbsNum");
				String itdt = req.getParameter("itdt");
				String atchFileId = req.getParameter("atchFileId");
				
				String ptPrc = req.getParameter("ptPrc");
				String stDt = req.getParameter("stDt");
				String edDt = req.getParameter("edDt");
				String ptCgy = req.getParameter("ptCgy");
				String pyQty = req.getParameter("pyQty");

				
				System.out.println("nm: "+ptNm);
				System.out.println("mbs: "+mbsNum);
				System.out.println("itdt: "+itdt);
				
				System.out.println("price: "+ptPrc);
				System.out.println("stdt: "+stDt);
				System.out.println("eddt: "+edDt);
				System.out.println("cgy: "+ptCgy);
				System.out.println("qty: "+pyQty);
				
				
				// 2. 서비스 객체 생성하기
				IPtImforService ptimforService = PtImforServiceImpl.getInstance();
				IAtchFileService fileservice = AtchFileServiceImpl.getInstance();
				
				AtchFileVO atchFileVO = fileservice.saveAtchFileList(req);
				System.out.println("NULL:" + atchFileVO);
				PtImforVO vo = new PtImforVO();
				
				//
				
				System.out.println("fileId: " +atchFileVO.getAtchFileId());
				
				vo.setMbsNum(Integer.parseInt(mbsNum));
//				vo.setMbsNum(3);
				vo.setItdt(itdt);
				
				vo.setPtNm(ptNm);
//				vo.setAtchFileId(-1);
				vo.setAtchFileId(atchFileVO.getAtchFileId());
				vo.setPtPrc(Integer.parseInt(ptPrc));
				vo.setPtCgy(ptCgy);
				vo.setPyQty(Integer.parseInt(pyQty));
				vo.setStDt(stDt);
				vo.setEdDt(edDt);

				ptimforService.reaisterPtImfor(vo);
				//
				String redirectUrl = req.getContextPath() + "/view/sellerMypage.jsp";
				
				resp.sendRedirect(redirectUrl);
	}

}
