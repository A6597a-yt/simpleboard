package com.board.simpleboard.user;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    // 로그인
    @PostMapping("/login")
    public void login(@RequestBody LoginDto dto, HttpSession session) {
        UserEntity user = userService.login(dto.getUsername());

        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        // 세션에 로그인 유저 저장
        session.setAttribute("loginUser", user);
    }

    // 현재 로그인한 사용자 확인
    @GetMapping("/me")
    public UserEntity me(HttpSession session) {
        return (UserEntity) session.getAttribute("loginUser");
    }

    // 로그아웃
    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate(); // 세션 완전 삭제
    }
}
