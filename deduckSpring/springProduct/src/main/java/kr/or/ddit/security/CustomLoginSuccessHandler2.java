package kr.or.ddit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginSuccessHandler2 implements 
		AuthenticationSuccessHandler{
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request
			, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
			log.warn("onAuthenticationSuccess에 왔다");
			
			User customUser = (User)auth.getPrincipal();
			
			log.info("username : " + customUser.getUsername());
			log.info("password : " + customUser.getPassword());
			
			clearAuthenticationAttribute(request);
			
			// targetUrl(목적지) : /notice/register를 구함
			SavedRequest savedRequest = requestCache.getRequest(request, response);
			String targetUrl = savedRequest.getRedirectUrl();
			
			log.warn("Login Success targetUrl : " + targetUrl);
			
			response.sendRedirect(targetUrl);
	}
	//인증 오류가 세션에 담겨 있다면 인증이 성공했으므로 clear 해줘야 함
	private void clearAuthenticationAttribute(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			return;
		}
		
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
}









