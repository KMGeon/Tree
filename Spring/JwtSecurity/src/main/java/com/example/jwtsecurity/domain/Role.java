package com.example.jwtsecurity.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="role")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Role {
    @Id
    @Column(name="role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(length = 20)
    private String name;

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                '}';
    }
}