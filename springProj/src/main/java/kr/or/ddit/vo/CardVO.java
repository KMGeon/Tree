package kr.or.ddit.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CardVO {
	private String userId;
	private String no;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date validMonth;
}
