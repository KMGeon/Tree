package com.fastcampus.ch4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class BoardDto {
    private  Integer bno;
    //null이 int로 들어가면 변환에러가 발생 integer로 들어오면 변환에러 x
    private  String title , content,writer,view_cnt,comment_cnt;
    private Date reg_date;

}
