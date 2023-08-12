package kr.or.ddit.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.or.ddit.vo.MemberVO;
import lombok.NoArgsConstructor;


public class CustomUesr extends User {

	private static final long serialVersionUID = 1L;

	private MemberVO memberVO;
	//username : 사용자id , password : 비밀번호 , authorities : 권한 리스트
	public CustomUesr(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}
	
	//사용자가 정의한 membervo타입을 스프링 시큐리티 userDetails타입으로 변환
	public  CustomUesr(MemberVO memberVO) {
		super(memberVO.getMemId() , memberVO.getMemPass(),memberVO.getMemberAuthVOList().stream()
				.map(auth->new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		this.memberVO = memberVO;
	}
	//외부에서 memberVO 맴버변수를 사용하려면 GETTER메소드가 필요하다.
}
