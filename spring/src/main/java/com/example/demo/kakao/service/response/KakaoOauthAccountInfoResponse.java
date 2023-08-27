package com.example.demo.kakao.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class KakaoOauthAccountInfoResponse {

    @JsonProperty("profile_nickname")
    private String profile_nickname;

    @JsonProperty("profile_image")
    private String profile_image;

    @JsonProperty("account_email")
    private String account_email;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("age_range")
    private int age_range;

}
