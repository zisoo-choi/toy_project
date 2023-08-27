package com.example.demo.signUp.controller.form.request;

import com.example.demo.signUp.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestForm {

     private String userId;
     private String userPw;
     private String email;
     private String userPhone;

    public Member toMember() {
        return Member.builder()
                .name(userId)
                .password(userPw)
                .email(email)
                .userPhone(userPhone)
                .build();
    }
}


