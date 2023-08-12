package ch12;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {
	//전역 멤버변수
	PrintWriter writer;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Webmarket 초기화..");
		
		//{"filename","c:\\logs\\webmarket.log"}
		String filename = filterConfig.getInitParameter("filename"); //c:\\logs\\webmarket.log
		
		if(filename == null) {
			throw new ServletException("로그 파일의 이름을 찾을 수 없습니다.");
		}
		
		//로그 파일이 있다면
		try {
			writer = new PrintWriter(new FileWriter(filename, true),true);
		} catch (IOException e) {
			throw new ServletException("로그 파일을 열 수 없습니다.");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response
			, FilterChain chain)
			throws IOException, ServletException {
		//접속한 클라이언트 IP
		writer.println(" 접속한 클라이언트 IP : " + request.getRemoteAddr());
		//접근한 URL 경로
		HttpServletRequest req;	//선언
		String currentPath = "";
		String queryString = "";
		
		//instanceof : 어떤 클래스를 상속받았는지 확인할 때 사용
		//			   상속받았다면 true / 상속 안받았다면 false
		//HttpServletRequest가 request의 타입과 같은 타입 이거나,
		//HttpServletRequest가 request의 타입을 상속받았다면 true
		if(request instanceof HttpServletRequest) {
			req = (HttpServletRequest)request;
			//접근한 URI 경로(http://localhost/ch12/test.jsp)
			currentPath = req.getRequestURI();
			//요청 파라미터(있거나 없거나)	//...id=a001&passwd=1234
			queryString = req.getQueryString();
			//삼항연산자(queryString : ?id=a001&passwd=1234)
			queryString = queryString==null?"":"?"+queryString;
		}
		//http://localhost/ch12/test.jsp?id=a001&passwd=1234
		writer.println("접근한 URL 경로 : " + (currentPath + queryString));
		long start = System.currentTimeMillis();
		//요청 처리 시작 시각
		writer.println("요청 처리 시작 시각 : " + getCurrentTime());
		
		//필터가 여러개 있을 때 다음 필터에 제어권 넘겨줌
		chain.doFilter(request, response);
		
		long end = System.currentTimeMillis();
		//요청 처리 종료 시각
		writer.println("요청 처리 종료 시각 : " + getCurrentTime());
		//요청 처리 소요 시간(종료 시각 - 시작 시각)
		writer.println("요청 처리 소요 시간 : " + (end-start) + "ms");
		writer.println("===================================");
	}

	@Override
	public void destroy() {
		writer.close();
	}

	//현재 시각 리턴 메소드
	private String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}
}











