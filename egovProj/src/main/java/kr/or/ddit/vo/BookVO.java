package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;

import javax.jws.soap.SOAPBinding.Style;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

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
	@NumberFormat(style =org.springframework.format.annotation.NumberFormat.Style.NUMBER)
	private int price;
	@DateTimeFormat(pattern = "yy-MM-dd")
	private Date insertDate;
	private String content;
	private List<AttachVO>attachVOList;
}
