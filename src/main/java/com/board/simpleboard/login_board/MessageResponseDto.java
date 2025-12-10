package com.board.simpleboard.login_board;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponseDto {
    // 서버 -> 클라이언트
    private Long id;
    private String author;
    private String message;
    private LocalDateTime createdAt;
    // entity -> dto static 변환메서드
    public static MessageResponseDto e2d(BoardEntity e){
        MessageResponseDto dto = new MessageResponseDto();
        dto.setId(e.getId());
        dto.setAuthor(e.getAuthor());
        dto.setMessage(e.getMessage());
        dto.setCreatedAt(e.getCreatedAt());
        return dto;
    }
}
