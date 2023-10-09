package com.project.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Session> sessions = new ArrayList<>();


    public Session addSession() {
        Session session = Session.builder()
                .user(this)
                .build();
        sessions.add(session);

        return session;
    }


}
