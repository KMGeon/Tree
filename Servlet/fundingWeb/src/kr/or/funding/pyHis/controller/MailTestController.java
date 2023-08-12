package kr.or.funding.pyHis.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.funding.util.MailUtil;

@WebServlet("/mailTest.do")
public class MailTestController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cMail = request.getParameter("email");
		Random random = new Random();
		String fst = Integer.toString(random.nextInt(9));
		String fend = Integer.toString(random.nextInt(9));
		String code = fst + fend;
	
		//MailUtil.sendMail("wnsgur0718@naver.com", "wnsgur3337", cMail, code);
		response.getWriter().write(code);  // 아작스 요청에 응답
	}
	
}
