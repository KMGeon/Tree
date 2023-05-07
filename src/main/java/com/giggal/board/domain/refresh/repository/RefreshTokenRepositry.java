package com.giggal.board.domain.refresh.repository;


import com.giggal.board.domain.refresh.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepositry extends JpaRepository<RefreshToken, Long> {
}
