package com.board.simpleboard.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity login(String username) {
        return userRepository.findById(username).orElse(null);
    }
}
