package com.dev.wanted.domain.entity;

import com.dev.wanted.domain.dto.request.UserSignupRequestDto;
import com.dev.wanted.domain.role.entity.Role;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String password;


    @Builder.Default
    private boolean deleted = false;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Todo> todos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "member_role",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }


    public void changeRole(Role role) {
        roles.add(role);
    }

    @Builder
    public User(Long id, String account, String password, boolean deleted) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.deleted = deleted;
    }

    public static User createUser(UserSignupRequestDto userSignupRequestDto, PasswordEncoder passwordEncoder) {
        return new User(
                userSignupRequestDto.getAccount(),
                passwordEncoder.encode(userSignupRequestDto.getPassword())
        );
    }

    protected User() {
    }
}
