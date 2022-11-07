package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//회원정보 자바빈 클래스
@Data
public class MemVO {
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private int coin;
	@DateTimeFormat(pattern = "yyyyMMdd")
	private Date regDate;
	@DateTimeFormat(pattern = "yyyyMMdd")
	private Date updDate;
	private String enabled;
	private String filename;	//첨부파일명
	//중첩된 자바빈 클래스의 유효성 검사
	private List<MemAuthVO> memAuthVOList;
	//첨부파일
	private MultipartFile[] memImage;
	//첨부파일리스트
	private List<AttachVO> attachVOList;
	//행번호
	private int rnum;
	//페이지번호
	private int pnum;
	
}








