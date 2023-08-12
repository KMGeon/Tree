package kr.or.ddit.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MemberVO {
    private int memNum;
    private  String memEmail;
    private  String memPasswd;
    private  String memName;
    private int memWhdrlWhth;
    private String memPhoneNumber;
    private Date memSgnupDate;
}
