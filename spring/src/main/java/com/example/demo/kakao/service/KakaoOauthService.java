package com.example.demo.kakao.service;

import com.example.demo.kakao.dto.KakaoOAuthToken;

public interface KakaoOauthService {
    String getAuthorizeCode();

    String kakaoCallback(String code);
}
