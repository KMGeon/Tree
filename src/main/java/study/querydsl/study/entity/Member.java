package study.querydsl.study.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter@Setter
@ToString(of = {"id","username","age"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;




    public Member(String username , int age) {
        this(username,age,null);
    }

    public Member(String username ) {
        this(username,0);
    }


    public Member(String username , int age , Team team){
        this.username = username;
        this.age = age;
        if(team != null){
            changeTeam(team);
        }
    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
