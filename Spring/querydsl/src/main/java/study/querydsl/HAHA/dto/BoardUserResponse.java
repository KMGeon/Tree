package study.querydsl.HAHA.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class BoardUserResponse {
    private String userName;
    private int userAge;
    private String boardName;

    @QueryProjection
    public BoardUserResponse(String userName, int userAge, String boardName) {
        this.userName = userName;
        this.userAge = userAge;
        this.boardName = boardName;
    }
}
