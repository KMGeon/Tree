package study.querydsl.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor@NoArgsConstructor
public class MemberSearchCondition {
    //회원명,팀명,나이
    private String username;
    private String teamName;
    private Integer ageGoe;//크거나 같거나
    private Integer ageLoe;

}
