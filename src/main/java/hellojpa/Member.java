package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long memberId;
    @Column
    private String userName;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne
    @JoinColumn(name ="locker_id" )
    private  Locker locker;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);//현재 나 자신을 넣어주고
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", userName='" + userName + '\'' +
                ", team=" + team +
                '}';
    }
}