package kr.or.funding.pyHis.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.funding.pyHis.service.IPyHisService;
import kr.or.funding.pyHis.service.PyHisServiceImpl;
import kr.or.funding.pyHis.vo.PyHisVO;
import kr.or.funding.util.MailUtil;

@WebServlet("/pyhis.do")
public class PyHisInsertController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String 	ptNum = req.getParameter("ptNum");
		String	mbsNum =req.getParameter("mbsNum");
		String	pyAm =req.getParameter("pyAm");
		String	commi =req.getParameter("commi");
		String	pyQty =req.getParameter("pyQty");
		String mbsNm = req.getParameter("mbsNm");
		String mbsMail = req.getParameter("mbsMail");
		String mbsAddr = req.getParameter("mbsAddr");
		String ptNm = req.getParameter("ptNm");
		
		
		System.out.println("제품명: "+ptNm);
		MailUtil.sendMail("wnsgur0718@naver.com", "wnsgur3337", mbsMail, mbsAddr, mbsNm, ptNm ,pyAm, pyQty);

	
		PyHisVO pv = new PyHisVO();
		
		pv.setPtNum(Integer.parseInt(ptNum));
		pv.setMbsNum(Integer.parseInt(mbsNum));
		pv.setPyAm(Integer.parseInt(pyAm));
		pv.setCommi(Integer.parseInt(commi));
		pv.setPyQty(Integer.parseInt(pyQty));
		
		IPyHisService pyHisSer = PyHisServiceImpl.getInstance();
		int phv = pyHisSer.insertPyHis(pv);
		
		resp.sendRedirect("/Funding/ptimfor/ptImforDetail.do?ptImforNum="+ptNum);
	}
}
