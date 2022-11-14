package kr.or.ddit.vo;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class BookVO {
	private int bookId;
	private String title;
	private String category;
	private int price;
	@DateTimeFormat(pattern = "yy-MM-dd")
	private Date insertDate;
	private String content;
}
