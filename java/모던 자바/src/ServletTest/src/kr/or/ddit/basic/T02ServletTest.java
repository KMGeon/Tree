package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T02ServletTest extends HttpServlet {
	
/*
 * 서블릿 동작 방식에 대하여...
 * 
 * 1. 사용자(클라이언트)가 URL을 클릭하면 HTTP Request를 서블릿 컨테이너로 전송(요청)한다.
 * 2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해 처리해야 할지를 
 *  검색한다.(로딩이 안된 경우에는 로딩을 수행한다. 로딩시 init()메소드 호출됨.)
 * 3. 서블릿 컨테이너는 요청을 처리할 개별 스레드 객체를 생성하여 해당 서블릿 객체의 service()메소드를 호출한다.
 *  (HttpServletRequest 및 HttpServletResponse객체를 생성하여 파라미터로 넘겨준다.)
 * 4. service() 메소드는 메소드 타입을 체크하여 적절한 메소드를 호출한다.(doGet, doPost, doPut, doDelete)
 * 5. 요청 처리가 완료되면 HttpServletRequest 및  HttpServletResponse 객체는 소멸된다.
 * 6. 컨테이너로부터 서블릿이 제거되는 경우에 destroy()메소드가 호출된다.
 * 
 * */	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		// Request객체의 메소드 확인하기
		System.out.println("getCharacterEncoding() : " + req.getCharacterEncoding());
		System.out.println("getContentLength() : " + req.getContentLength());
		System.out.println("getQueryString() : " + req.getQueryString());
		System.out.println("getProtocol() : " + req.getProtocol());
		System.out.println("getMethod() : " + req.getMethod());
		System.out.println("getRequestURI() : " + req.getRequestURI());
		System.out.println("getRemoteAddr() : " + req.getRemoteAddr());
		System.out.println("getRemotePort() : " + req.getRemotePort());
		
		// 요청메세지로부터 name값을 가져옴
		String name = req.getParameter("name");
		System.out.println("name => " + name);
		String age = req.getParameter("age");
		System.out.println("age => " + age);
		
		// 응답메세지 인코딩 방식 설정
		resp.setCharacterEncoding("UTF-8");
		// 응답메세지
		resp.setContentType("text/plain");
		
		// 응답 메세지 생성하기
		PrintWriter out = resp.getWriter();
		
		out.println("name => " + name);
		out.println("age => " + age);
		out.println("서블릿 경로 => " + req.getServletPath());
		out.println("서블릿 컨텍스트 경로 => " + req.getContextPath());
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
	
}
