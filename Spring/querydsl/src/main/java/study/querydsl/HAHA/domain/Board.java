package study.querydsl.HAHA.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.querydsl.study.entity.Team;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id@GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Board(Long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public Board(String name) {
        this.name = name;
    }

    public Board(String name, User user) {
        this.name = name;
        if (user != null) {
        changeUser(user);
        }
    }

    public void changeUser(User user) {
        this.user = user;
        user.getBoards().add(this);
    }

}
