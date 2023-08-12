package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//context는 애플리케이션이 있으면 계속 있음 없어지면 같이 없어짐
// request는 reposnce를 받으면 request없어짐
public class T11ServletContextListenerTest extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		req.getServletContext().setAttribute("ATTR1", "속성1");
		req.getServletContext().setAttribute("ATTR1", "속성11");
		req.getServletContext().setAttribute("ATTR2", "속성2");
		req.getServletContext().removeAttribute("ATTR1");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
