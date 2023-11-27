package com.giggal.board.domain.refresh.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private String value;

    @Builder
    public RefreshToken(Long id, Long memberId, String value) {
        this.id = id;
        this.memberId = memberId;
        this.value = value;
    }
}
