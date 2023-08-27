package com.example.demo.kakao.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoOauthAccessTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;
}
