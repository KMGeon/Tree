package kr.or.ddit.basic;


import java.io.IOError;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T01ServletLifeCycle extends HttpServlet {
	/*
	 * 서블릿이란? 컨테이너(서블릿 엔진에)에 의해 관리되는 자바기반 웹 컴포넌트로서 , 동적인 웹컨텐츠 생성을 가능하게 해준다.
	 */

	@Override
	public void init() throws ServletException {
//		초기화 코드 작성
		System.out.println("init() 호출됨...");
	}
	public T01ServletLifeCycle() {
		System.out.println("T01ServletLifeCycle생정자 호출됨");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service()호출됨...");

		super.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		메서드방식이get인 경우 호출됨
		System.out.println("doGet()호출됨...");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		메서드 방식이 post인 경우 호출됨
		System.out.println("dopost()호출됨");

	}

	@Override
	public void destroy() {
//		객체 소멸시 (컨테이너로부터 서블릿객체 제거시) 필요한 코드 작성...
		System.out.println("destroy()호출됨...");
	}
	
}
