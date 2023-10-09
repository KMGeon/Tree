package com.joy.playground.domain.domain;

import com.joy.playground.domain.dto.request.UserRequest;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Builder.Default
    private boolean deleted = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();


    //연관관계 편의 메소드
    public void addBoard(Board board) {
        this.boards.add(board);
        board.updateUser(this);
    }

    public void updateUser(UserRequest userRequest) {
        email = userRequest.getEmail();
        password = userRequest.getPassword();
        name = userRequest.getName();
    }

    public void destroy() {
        deleted = true;
    }


    public boolean isDeleted() {
        return deleted;
    }
}
