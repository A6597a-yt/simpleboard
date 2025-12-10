package com.board.simpleboard.login_board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageCreateDto {
    // 클라이언트 -> 서버
    private String message;

    // 생성자를 통해 엔티티를 dto로 변경
}
