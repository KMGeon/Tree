package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.DynAnyPackage.Invalid;

public class T06ServletSessionTest extends HttpServlet {
	/*
	 * 세션(httpSession)객체에 대하여... -세션을 통하여 사용자(브라우져)별로 구분하여 정보를 관리할 수 있다.(세션id이용)
	 * -쿠키를 사용 할 때보다 보안이 향상된다.(정보가 서버에 저장되기 때문에...)
	 * 
	 * -세션객체를 가져오는 방법 httpSession session = request.getSession(boolean값); boolean값 :
	 * true인 경우 => 세션객체가 존재하지 않으면 새로 생성된다. false인 경우 => 세션객체가 존재하지 않으면 null을 리턴한다.
	 * 
	 * -세션 삭체 처리 방법 1,invalidate()메서드 호출 2.setmaxinactiveinterval(int interval) 호출
	 * ->일정시간 동안 요청이 없으면 세션 객체 삭제 3.web.xml에<session-config>설정하기
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션을 가져오는데 없으면 새로 생성한다.
		HttpSession session = req.getSession();

		// 생성시간 가져오기
		Date createTimeDate = new Date(session.getCreationTime());

		// 마지막 접근시간 가져오기
		Date lastAccessTime = new Date(session.getLastAccessedTime());

		String title = "재방문을 환영합니다.";

		int VisitCnt = 0; // 방문횟수
		String userId = "pc22";// 사용자 아이디

		if (session.isNew()) {
			title = "처음 방문을 환영합니다.";
			session.setAttribute("userId", userId);
		} else {
			VisitCnt = (Integer) session.getAttribute("visitCnt");
			VisitCnt++;
			userId = (String) session.getAttribute("userId");
		}
		session.setAttribute("visitCnt", VisitCnt);

//	1번 방법 	session.invalidate(); 사용자를 바꿔줌
//	2번 방법 session.setMaxInactiveInterval(10); 시간마다 삭제
//	3번 방법은 web.xml
//		응답헤더에 인코딩 및 content-type 설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();

		out.println("<!DOCTYPE html><head><title>" + title + "</title></head>" + "<body><h1 align=\"center\">" + title
				+ "</h1>" + "<h2 align=\"center\">세션 정보</h2>" + "<table border=\"1\" align=\"center\">"
				+ "<tr bgcolor=\"orange\"><th>구분</th><th>값</th></tr>" + "<tr><td>세션ID</td><td>" + session.getId()
				+ "</td></tr>" + "<tr><td>생성시간</td><td>" + createTimeDate + "</td></tr>" + "<tr><td>마지막접근시간</td><td>"
				+ lastAccessTime + "</td></tr>" + "<tr><td>UserID</td><td>" + userId + "</td></tr>"
				+ "<tr><td>방문횟수</td><td>" + VisitCnt + "</td></tr>" + "</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
