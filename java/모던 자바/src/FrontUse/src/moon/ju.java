package moon;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ju")
public class ju extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ju() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rs = req.getParameter("fbi");

		resp.setCharacterEncoding("UTF-8"); // 응답 한글 깨짐
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
//		out.write("<h1>" + rs + " 님 안녕하세요.</h1>");
		out.write("{\"name\":\""+ rs +"\"}"); // json문자열 리턴 {name:...}
		// 나중에는 Jacson 라이브러리라는 자동으로 json문자여롤 바꿔주는 라이브러리를 씀
		// 지금은 직접 json 문자열을 한번 만들어 봄

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String rs = req.getParameter("friend");
		rs = URLDecoder.decode(rs,"UTF-8");

		resp.setCharacterEncoding("UTF-8"); // 응답 한글 깨짐
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.write("<h1>" + rs + " 친구님 하이요.</h1>");
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
