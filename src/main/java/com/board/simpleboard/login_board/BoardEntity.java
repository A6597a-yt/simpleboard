package com.board.simpleboard.login_board;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 PK
    private Long id;

    private String author; // Admin, Auser, Buser
    
    @Column(length = 2000)
    private String message;

    private LocalDateTime createdAt;
}
