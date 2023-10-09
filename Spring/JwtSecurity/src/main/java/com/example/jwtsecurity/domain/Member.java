package com.example.jwtsecurity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="member")
@Getter
@Builder
@AllArgsConstructor
public class Member {
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 255)
    private String email;

    @Column(length = 50)
    private String name;

    @Column(length = 500)
    private String password;

    @CreationTimestamp // 현재시간이 저장될 때 자동으로 생성.
    private LocalDateTime regdate;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "member_role",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public Member() {

    }

    @Override
    public String toString() {
        return "User{" +
                "memberId=" + memberId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", regdate=" + regdate +
                '}';
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}

