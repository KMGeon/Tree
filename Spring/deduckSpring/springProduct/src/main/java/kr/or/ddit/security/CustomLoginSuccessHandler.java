package kr.or.ddit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;
/*   /notice/register -> loginForm -> 로그인 -> CustomLoginSuccessHandler(성공)
 	 -> 사용자 작업.. -> /notice/register 로 리다이렉트 해줌
 	 (스프링 시큐리티에서 기본적으로 사용되는 구현 클래스) 
 */
@Slf4j
public class CustomLoginSuccessHandler extends
	SavedRequestAwareAuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth) 
					throws ServletException, IOException {
		log.warn("onAuthenticationSuccess에 왔다");
		
		//Authentication(인증) -> 로그인. 로그인 정보를 담고 있음
		User customUser = (User)auth.getPrincipal();
		//로그인 아이디
		log.info("username : " + customUser.getUsername());
		
		super.onAuthenticationSuccess(request, response, auth);
	}
	
}






