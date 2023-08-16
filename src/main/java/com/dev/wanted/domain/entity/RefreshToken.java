package com.dev.wanted.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String value;

    @Builder
    public RefreshToken(Long id, Long userId, String value) {
        this.id = id;
        this.userId = userId;
        this.value = value;
    }
}
