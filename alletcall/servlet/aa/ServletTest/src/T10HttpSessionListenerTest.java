package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T10HttpSessionListenerTest extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		httpsession 객체 생성 및 소멸
		HttpSession session = req.getSession(); // 기본값 true임 , 만들기 싫으면 false

		// httpsessionattributelistener 관련 이벤트
		session.setAttribute("ATTR1", "속성1");// 생성
		session.setAttribute("ATTR1", "속성11");
		session.setAttribute("ATTR2", "속성2");
		session.removeAttribute("ATTR1");
		
//		실행하면 attr2를 삭제를 안했는데 destory가 되면서 attr2가 삭제됨

		session.invalidate(); // 세션 종료.
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
