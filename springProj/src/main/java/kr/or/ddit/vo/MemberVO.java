package kr.or.ddit.vo;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Component
public class MemberVO {
		//1:1의 관계
		
	   private AddressVO addressVO;
	   private java.util.List<CardVO> cardlist;//1:n관계
	// 자바빈 클래스
	   // 회원 아이디
	   private String userId = "gaeddongi";
	   // 비밀번
	   private String password = "java";
	   // 보유 코인
	   private int coin = 100;
	   // 생일(기본 : 2022/11/01 => 변경 : 20221101 => 변경 : 2022-11-01)
	   @DateTimeFormat(pattern = "yyyy-MM-dd")
	   private Date birth;
	   // 성별
	   private String gender;
	   // 국적
	   private String nationality;
	   // 보유 자동차들
	   private String[] cars;
	   private String car;
	   // 취미들
	   private String[] hobbyList;
	   private String hobby;
	   // 결혼유무
	   private boolean marriaged;

	   

	}