package moon;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/IDCheck")
public class IDCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IDCheck() {
        super();
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 여기서 넘어온 id값이 DB에 있는지 확인해서 없으면 OK, 있으면 NO
		System.out.println("넘어온 아이디값: "+ req.getParameter("merong"));
		if(req.getParameter("merong").equals("ggggg")) {
			resp.getWriter().write("NO");
		}else {
			resp.getWriter().write("OK");
		}
		// 찍힌 값이 DB에 있으면 NO, 없으면 OK, 일단 있다고 가정하면 
	
	}

	

}
