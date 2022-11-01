package kr.or.ddit.vo;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//자바빈 클래스
@Data
@NoArgsConstructor
@Component
public class AddressVO {
	private String userId;
	private String postCode;
	private String address;
	private String addessDetail;

}
