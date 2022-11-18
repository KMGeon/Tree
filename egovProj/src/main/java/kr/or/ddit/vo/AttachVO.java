package kr.or.ddit.vo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Component
public class AttachVO {
	private String userNo;
	private int seq;
	private String filename;
	private long filesize;
	private String regdate;
	private String tid;
	private String attachName;
	private int attachSize;
	private String attachType;
}
