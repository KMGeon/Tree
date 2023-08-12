package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 서블릿 동작 방식에 대하여... 
 * 1.사용자(클라이언트)가 url을 클릭하면 http request를 servlet container로 전송한다.
 * 2.컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해 처리해야 할지를 검색한다.
 * (로딩이 안된 경우에는 로딩을 수행한다. 로딩시 init()메서드 호출됨
 * 3.서블릿 컨테이너는 요청을 처릴할 개별 스레드 객체를 생성하여 해당 서블릿 객체의 service()
 * 메서드를 호출한다. (httpservletrequest 및 httpservletreponse 객체를 생성하여 파라미터로 넘겨준다.)
 * 4.service()메서드는 메서드 타입을 첵크하여 적절한 메서드를 호출한다.(doget , dopost, doput, dodelete등)
 * 5.요청 처리가 완료되면 , httpservletrequest및 httpservletresponse 객체는 소별된다.
 * 6.컨테이너로부터 서블릿이 제거되는 경우에 destory()메서드가 호출된다.
 * 
 */

public class T02ServletTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		// request객체의 메서드 확인하기
		System.out.println("getCharacterEncoding(): " + req.getCharacterEncoding());
		System.out.println("getContentLength(): " + req.getContentLength());
		System.out.println("getQueryString(): " + req.getQueryString());
		System.out.println("getProtocol(): " + req.getProtocol());
		System.out.println("getMethod(): " + req.getMethod());
		System.out.println("getRequestURI(): " + req.getRequestURI());
		System.out.println("getRemoteAddr(): " + req.getRemoteAddr());
		System.out.println("getRemotePort(): " + req.getRemotePort());

		// 요청메시지로부터 name값을 가져옴
		String name = req.getParameter("name");
		System.out.println("name=>" + name);
		String age = req.getParameter("age");
		System.out.println("age=>" + age);

		// 응답메시지 인코딩 방식 설정(content-type
		resp.setCharacterEncoding("UTF-8");
		// 응답메시지 컨텐트타입 설정
		resp.setContentType("text/plain");
		// 응답 메세지 생성하기
		PrintWriter out = resp.getWriter();

		out.print("name=>" + name);
		out.print("age=>" + age);
		out.print("서블릿 경로: =>" + req.getServletPath()); // 이건 어플리케이션 이름
		out.print("서블릿 컨텍스트 경로=>" + req.getContextPath()); // 

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
