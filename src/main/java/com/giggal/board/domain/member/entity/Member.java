package com.giggal.board.domain.member.entity;

import com.giggal.board.domain.post.entity.Post;
import com.giggal.board.domain.role.entity.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_email")
    private String email;

    private String password;

    @Column(name = "member_name")
    private String name;

    private String ip;

    private String cityName;

    private String continentName;

    private String countryName;

    private String countryIsoCode;

    private String subdivisionName;


    @ManyToMany
    @JoinTable(name = "member_role",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public void changeRole(Role role) {
        roles.add(role);
    }

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Post>posts = new ArrayList<>();

    @Builder
    public Member(
            String email,
            String password,
            String name,
            String ip,
            String cityName,
            String continentName,
            String countryName,
            String countryIsoCode,
            String subdivisionName
    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.ip = ip;
        this.cityName = cityName;
        this.continentName = continentName;
        this.countryName = countryName;
        this.countryIsoCode = countryIsoCode;
        this.subdivisionName = subdivisionName;
    }
}
