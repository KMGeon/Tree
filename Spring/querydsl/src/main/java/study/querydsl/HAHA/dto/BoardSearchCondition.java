package study.querydsl.HAHA.dto;

import lombok.Getter;

@Getter
public class BoardSearchCondition {
    private String username;
    private String boardName;
    private Integer ageGoe;
    private Integer ageLoe;
}
