package com.board.simpleboard.login_board;

import java.util.*;
import com.board.simpleboard.user.UserEntity;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class BoardController {

    private final BoardService boardService;

    // 1) 메시지 작성 (POST /messages)
    @PostMapping
    public MessageResponseDto create(@RequestBody MessageCreateDto dto,
                                     HttpSession session) {

        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");

        if (loginUser == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        return boardService.create(dto, loginUser);
    }


    // 2) 메시지 전체 조회 (GET /messages)
    @GetMapping
    public List<MessageResponseDto> list() {
        return boardService.listAll();
    }


    // 3) 메시지 삭제 (DELETE /messages/{id})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       HttpSession session) {

        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");

        if (loginUser == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        boardService.delete(id, loginUser);
    }
}