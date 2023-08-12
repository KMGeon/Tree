package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T05ServketCookieTest extends HttpServlet {
	/*
	 * 쿠키에 대해서... 웹서버와 브라우져는 애플리케이션을 사용하는 동안 필요한 값을 쿠키를 통해 공유하여 상태를 유지함
	 * 
	 * 1.구성요소? -이름 -값 -유효시간(초) -도메인 -경로 : 쿠키를 공유할 기준경로를 지정한다.(지정하지 않으면 실행한 url의
	 * 경로부붑ㄴ이 사용됨)
	 * 
	 * 
	 * 2.동작방식 -쿠키 생성단계 : 생성한 쿠키를 응답데이터의 헤더에 저장하여 웹브라우져에 전송 -쿠키 저장단계 : 웹브라우저는 응답데이터에
	 * 포함된 쿠키를 쿠키저장소에 보관한다. -쿠키 전송단계 : 웹브라우져는 저장한 쿠키를 요청이 있을때마다 웹서버에 전송한다.(삭제전까지.)
	 * 웹서버는 브라우져가 전송한 쿠키를 사용하여 필요한 작업을 수행한다.
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		setCookieExam(req , resp);//쿠키 설정 예제
//		readCookieExam(req, resp);
		deleteCookieExam(req, resp);

	}

	private void deleteCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 사용중인 쿠키정보를 삭제하는 방법
		// 1. 사용중인 쿠키정보를 이용하여 쿠키객체를 생성한다.
		// 2. 쿠키객체의 최대지속시간을 0으로 설정한다.
		// 3. 설정한 쿠키객체를 응답헤더에 추가하여 전송한다.

		// 현재 도메인에서 사용중인 쿠키정보 배열 가져오기
		Cookie[] cookies = req.getCookies();

		// 응답헤더에 인코딩 및 content type 설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();

		String title = "쿠키정보 읽기 예제";

		out.println("<!DOCTYPE html>" + "<html><head><title>" + title + "</title></head>" + "<body>");

		if (cookies != null) {
			out.println("<h2>" + title + "</h2>");

			for (Cookie cookie : cookies) {
				if ((cookie.getName()).equals("userId")) {
					// 쿠키정보 삭제하기
					cookie.setMaxAge(0);

					resp.addCookie(cookie);

					out.println("삭제한 쿠키 : " + cookie.getName() + "<br>");
				}
			}

			for (Cookie cookie : cookies) {
				out.println("name : " + cookie.getName() + "<br>");
				out.println("value : " + URLDecoder.decode(cookie.getValue(), "UTF-8") + "<br>");
				out.println("<hr>");
			}
		} else {
			out.println("<h2>쿠키정보가 없습니다.</h2>");
		}
		out.println("</body>");
		out.println("</html>");

	}

	private void readCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		현재 도메인에서 사용중인 쿠키정보 배열 가져오기
		Cookie[] cookies = req.getCookies();

//		응답헤더에 인코딩 및  contenttype설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();
		String title = "쿠키정보 읽기 예제";
		out.println("<DOCTYPE html>" + "<html><head><title>" + title + "</title></head><body>");
		if (cookies != null) {
			out.println("<h2>" + title + "</h2>");
			for (Cookie cookie : cookies) {
				out.println("name :" + cookie.getName() + "<br>");
				out.print("value: " + URLDecoder.decode(cookie.getValue(), "UTF-8") + "<br>");
				out.println("<hr>");
			}

		} else {
			out.print("<h2>쿠키정보가 없습니다.</h2>");

		}
		out.print("</body>");
		out.print("</html>");

	}

	private void setCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub

		/*
		 * 쿠키정보 설정하는 방법 1.쿠키객체 생성하기. 사용불가문자(공백 , [] , ()= , ",/,?,@,:,;) cookie cookie =
		 * new cookie("키"."value"( 쿠키값은 사용불가문자를 제외한 나머지 출력가능한 아스키 문자 사용가능함 =>이외의 값(예를들면
		 * 한글)을 사용시에는 urlencoder.encode()사용하여 처리함
		 * 
		 * 
		 * 2.쿠키 최대 지속시간을 설정한다.(초단위) => 지정하지 않으면 브라우져 종료시 삭제됨
		 * cookie.seetMaxAge(60*60*24);//24시간
		 * 
		 * 3.응답헤더에 쿠키객체를 추가한다. response.addCookie);
		 * 
		 * =>출력버퍼가 플래시 된 이후에는 쿠키를 추가할 수 없다.
		 * 
		 * 
		 */
		req.setCharacterEncoding("UTF-8");

		// 쿠키 생성하기
		Cookie userId = new Cookie("userId", req.getParameter("userId"));
		// 쿠키값에 한글을 사용하여 인코딩 처리를 해준다
		Cookie name = new Cookie("name", URLEncoder.encode(req.getParameter("name"), "utf-8"));

		// 쿠키 소멸시간 성정(초단위)
		userId.setMaxAge(60 * 60 * 24); // 1일
		userId.setHttpOnly(true);

		name.setMaxAge(60 * 60 * 48);// 2일

		// 응답헤더에 쿠키 추가하기
		resp.addCookie(userId);
		resp.addCookie(name);

		// 응답헤더에 인코딩 및 content type 설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();

		String title = "쿠키설정 예제";

		out.println("<DOCTYPE html>" + "<html><head><title>" + title + "</title></head>");
		out.println("<body>" + "<h1 align\"center\">" + title + "</h1>" + "<ul>" + "<li><b>ID</b>: "
				+ req.getParameter("userId") + "</li>" + "<li><b>이름</b>: " + req.getParameter("name") + "</li>"
				+ "</ul>" + "</body>");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
