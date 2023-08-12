package ch12;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}	

	@Override
	public void doFilter(ServletRequest req, ServletResponse res
			, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		//세션 객체를 생성*****
		HttpSession session = request.getSession();
		//로그인이 안되었음
		if(session == null) {
			//로그인 안되었다면 
			response.sendRedirect("/ch12/loginForm.jsp");
		}
		//session.setAttribute("userID","admin"); 이것이 실행되어 있어야 함
		String id = (String)session.getAttribute("userID");
		
		if(id == null) {
			response.sendRedirect("/ch12/loginForm.jsp");
		}
		//필터가 여러개일 때 다음 필터로 제어권을 이전
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		
	}
	
}
