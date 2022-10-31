package kr.or.ddit.vo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data
@NoArgsConstructor
@ToString
public class MemberVO {
	private String userId="아이디", password="비밀번호";
	private int coin = 100;
}
