package kr.or.funding.oneIq;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/oneiq/oneiqDetail.do")
public class OneiqDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    		String iqNum = req.getParameter("IqNum");
    	
    		IOneIqService oneiqService = OneiqServiceImpl.getInstance();
    		System.out.println("iqNum "+iqNum);
    		OneiqVO mv = oneiqService.getOneiq(iqNum);
//    		
//    	
//    		// 1. 서비스객체 생성
//    		req.setAttribute("mv", mv);
//    		System.out.println(mv);
    	
    		req.setAttribute("mv", mv);
    		
    		req.getRequestDispatcher("/view/oneiq_detail.jsp").forward(req, resp);
    	
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doGet(req, resp);
    }
}
