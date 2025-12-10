package com.board.simpleboard;
import com.board.simpleboard.user.UserRepository;
import com.board.simpleboard.user.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            userRepository.save(new UserEntity("Admin", "ADMIN"));
            userRepository.save(new UserEntity("Auser", "USER"));
            userRepository.save(new UserEntity("Buser", "USER"));
        }
    }
}
