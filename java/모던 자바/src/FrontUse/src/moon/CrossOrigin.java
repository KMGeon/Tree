package moon;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hc.client5.http.fluent.Request;

@WebServlet("/CrossOrigin")
public class CrossOrigin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CrossOrigin() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setCharacterEncoding("UTF-8");
//		String kdm = URLEncoder.encode("김다미","utf-8");
//		String retstr = Request.get("https://news.google.com/rss/search?q="+ kdm +"&hl=ko&gl=KR&ceid=KR:ko").execute().returnContent().asString();
//		
//		PrintWriter out = resp.getWriter();
//		out.write(retstr); // 요청받은 곳으로 출력
		
		resp.setCharacterEncoding("UTF-8");
		
		String rs = URLEncoder.encode(req.getParameter("lmj"),"UTF-8");
		String retstr = Request.get("https://news.google.com/rss/search?q="+ rs +"&hl=ko&gl=KR&ceid=KR:ko").execute().returnContent().asString();
		
		System.out.println(retstr);
		
		PrintWriter out = resp.getWriter();
		out.write(retstr); // 요청받은 곳으로 출력
		
		
	}

}
