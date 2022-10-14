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

public class LogFileFilter implements Filter{
	/*
	 <init-param>
		<param-name>filename</param-name>
		<param-value>c:\\logs\\monitor.log</param-value>
	</init-param>
	 */
	//filterConfig : {"filename":"c:\\logs\\monitor.log"}
	PrintWriter writer; //선언
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String filename 
			= filterConfig.getInitParameter("filename");//c:\\logs\\monitor.log
		if(filename==null) {
			throw new ServletException("로그 파일의 이름을 찾을 수 없습니다");
		}
		//파일이 있으면..
		try {
			//1) PrintWriter : byte출력스트림과 문자출력스트림을 가지고 객체를 생성할 수 있음
			//				자동 flush 기능 탑재
			//				생성자에 FileNotFoundException 예외가 있어, 반드시 예외 처리를 해야 함
			//				new PrintWriter(Writer out) : 자동 플러시 없음
			//				new PrintWriter(Writer out, true) : 자동 플러시 있음
			//2) FileWriter : 텍스트 데이터를 파일에 저장할 때 사용. 
			//			  문자 단위로 저장하므로 텍스트만 저장 가능
			//			 new FileWriter(filename) => 덮어쓰기
			//			 new FileWriter(filename, true) => 기존 내용 끝에 추가 쓰기
			writer = new PrintWriter(new FileWriter(filename,true),true);
		} catch (IOException e) {
			throw new ServletException("로그 파일을 열 수 없습니다.");
		}
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//날짜 형식 지정 객체 생성
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
				
		//monitor.log 파일에 입력될 데이터 생성
		writer.printf("현재 일시 : %s %n", formatter.format(calendar.getTime()));
		//사용자 IP 주소
		String clientAddr = request.getRemoteAddr();
		writer.printf("클라이언트 주소 : %s %n", clientAddr);
		
		chain.doFilter(request, response);
		
		String contentType = response.getContentType();
		writer.printf("문서의 콘텐츠 유형 %s %n", contentType);
		writer.println("=============================");
	}

	@Override
	public void destroy() {
		writer.close();
	}
	
}






