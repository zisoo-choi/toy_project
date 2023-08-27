package com.example.demo.signUp.entity;

import com.example.demo.kakao.dto.KakaoOAuthToken;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nickname;
    private String profile_image;
    private String gender;
    private String age_range;

    private String password;

    private String userPhone;

    @Getter
    private String email;


    public Member(String name, String nickname, String email) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

    public Member(String email) {
        this.email = email;
    }

    public Member(String name, String nickname, String email, String profileImage, String gender, String ageRange) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.profile_image = profileImage;
        this.gender = gender;
        this.age_range = ageRange;
    }
}
