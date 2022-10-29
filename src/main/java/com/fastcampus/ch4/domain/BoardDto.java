package com.fastcampus.ch4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;


@Data

@ToString
public class BoardDto {
    private int bno;
    //null이 int로 들어가면 변환에러가 발생 integer로 들어오면 변환에러 x
    private String title, content, writer, view_cnt, comment_cnt;
    private Date reg_date;

    public BoardDto() {
    }

    public BoardDto(int bno, String title, String content, String writer, String view_cnt, String comment_cnt, Date reg_date) {
        this.bno = bno;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.view_cnt = view_cnt;
        this.comment_cnt = comment_cnt;
        this.reg_date = reg_date;
    }

    public BoardDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;

    }
}
