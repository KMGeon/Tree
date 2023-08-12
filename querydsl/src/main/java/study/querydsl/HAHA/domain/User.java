package study.querydsl.HAHA.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity@Getter
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    @OneToMany(mappedBy = "user")
    private List<Board>boards = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    @Builder
    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
