package com.board.simpleboard.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    private String username;// Admin, Auser, Buser

    private String role;    // ADMIN or USER

    public UserEntity(String str1, String str2) {
        this.username = str1;
        this.role = str2;
    }
}
