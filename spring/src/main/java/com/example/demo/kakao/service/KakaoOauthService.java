package com.example.demo.kakao.service;

public interface KakaoOauthService {
    String getAuthorizeCode();

    String getAccessToken(String code);
}
