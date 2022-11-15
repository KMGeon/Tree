package kr.or.ddit.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class AttachVO {
	private String userNo;
	private String seq;
	private String filename;
	private String filesize;
	private String regdate;
	private String tid;
	private String attachName;
	private int attachSize;
	private String attachType;
}
