package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T03ServletParameterTest extends HttpServlet {

//	요청하는 정보를 담아서 보낸다. 즉 url 정보를 담아서 보낸다.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		Post방식으로 넘어온, body 데이터를 인코딩 처리함
//		get방식의 경우에는 tomcat의 rioencoding 설정을 이용함
//		반드시 요청객체에서 값을 가져오기 전에 먼저 설정해야 적용됨
		req.setCharacterEncoding("UTF-8"); // 꺼내기 전에 무조건 이거 설정 아니면 한글 깨짐
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String hobby = req.getParameter("hobby");
		String rlgn = req.getParameter("rlgn");

		String[] food = req.getParameterValues("food");

//		<---------------------------------------------------------------->
//		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<body>");
		out.print("<p>username: " + username + "</p>");
		out.print("<p>password: " + password + "</p>");
		out.print("<p>gender: " + gender + "</p>");
		out.print("<p>hobby: " + hobby + "</p>");
		out.print("<p>rlgn: " + rlgn + "</p>");

		if (food != null) {
			for (String f : food) {
				out.print("<p>gender: " + f + "</p>");

			}
		}
//		전체 파라미터 정보 가져오기
		Enumeration<String> params = req.getParameterNames();
		while (params.hasMoreElements()) {
			String param = params.nextElement();

			out.print("<p>파라미터 이름: " + param + "</p>");
		}
		out.print("</html>");
		out.print("</body>");
	}

}
