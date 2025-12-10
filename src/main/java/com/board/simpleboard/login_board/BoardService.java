package com.board.simpleboard.login_board;

import java.time.LocalDateTime;
import com.board.simpleboard.user.UserEntity;

import java.util.*;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public MessageResponseDto create(MessageCreateDto dto, UserEntity loginUser){
        // entity 만들기
        BoardEntity entity = new BoardEntity();
        entity.setAuthor(loginUser.getUsername());
        entity.setMessage(dto.getMessage());
        entity.setCreatedAt(LocalDateTime.now());
        // db 저장
        BoardEntity saved = boardRepository.save(entity);
        // entity -> dto 변환하여 반환
        return MessageResponseDto.e2d(saved);
    }

    public void delete(Long id, UserEntity loginUser){

        // id로 entity 조회
        Optional<BoardEntity> optionalEntity = boardRepository.findById(id);

        if(optionalEntity.isEmpty()){
            throw new IllegalArgumentException(id + " 는 없는 아이디입니다.");
        }
        BoardEntity entity = optionalEntity.get();

        // 권한 체크
        boolean isAdmin = "ADMIN".equals(loginUser.getRole());
        boolean isOwner = entity.getAuthor().equals(loginUser.getUsername());

        if (!isAdmin && !isOwner){
            throw new IllegalStateException("삭제 권한이 없습니다.");
        }
        // 삭제
        boardRepository.delete(entity);

    }

    public List<MessageResponseDto> listAll() {
        return boardRepository.findAll().stream()
                .map(MessageResponseDto::e2d)
                .toList();
    }
}
