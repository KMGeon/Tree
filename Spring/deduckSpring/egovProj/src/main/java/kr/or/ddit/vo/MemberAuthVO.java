package kr.or.ddit.vo;

import lombok.Data;

//PoJo(Plain Oriented Java Object)에서 멀어짐
@Data
public class MemberAuthVO {
	private String memId;		//******* username
	private String auth;	//******* auth
}
