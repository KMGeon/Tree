package ch12;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//Filter 인터페이스를 구현하는 클래스
//이 클래스는 web.xml과 밀접한 연관 관계가 있음
public class AuthenFilter implements Filter {

	//필터를 초기화
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filter01 초기화..");
	}

	//필터를 리소스(jsp)에 적용
	//FilterChain : 연속적으로 필터가 있으면 다음 필터로 제어를 넘겨줌
	@Override
	public void doFilter(ServletRequest request, ServletResponse response
			, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter01.jsp 수행");
		String name = request.getParameter("name");
		//파라미터의 값이 없다면{"name":""}
		if(name==null || name.equals("")) {
			//response : 응답
			//응답 웹 페이지에 messge를 출력하도록 함
			response.setCharacterEncoding("UTF-8");	//문자 인코딩
			response.setContentType("text/html;charset=UTF-8");	//콘텐츠 유형
			PrintWriter writer = response.getWriter();
			String message = "입력된 name 값은 null입니다.";
			writer.println(message);
			return;
		}
		//연속 필터가 있으면 다음 필터로 제어를 넘겨줌
		chain.doFilter(request, response);
	}
	
	//필터를 종료 전 호출
	@Override
	public void destroy() {
		System.out.println("Filter01 해제..");
	}
	
}



