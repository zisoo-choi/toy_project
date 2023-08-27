package com.example.demo.signUp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String userPw;

    private String userAddress;

    private String userPhone;

    @Getter
    private String email;

    public Member(String userId, String userPw, String userAddress, String userPhone, String email) {
        this.userId = userId;
        this.userPw = userPw;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.email = email;
    }

    public Member(String email) {
        this.email = email;
    }
}
