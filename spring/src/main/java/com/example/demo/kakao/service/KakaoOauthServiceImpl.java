package com.example.demo.kakao.service;

import com.example.demo.utility.PropertyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoOauthServiceImpl implements KakaoOauthService{

    final private RestTemplate restTemplate;
    final private PropertyUtil propertyUtil;

    @Override
    public String getAuthorizeCode() {
        final String CLIENT_ID = propertyUtil.getProperty("client_id");
        final String URL = "https://kauth.kakao.com/oauth/authorize";
        final String REDIRECT_URI = "http://localhost:8080/authentication/kakao/callback";

//        return URL + "?client_id=" + CLIENT_ID + "&scope=repo:status read:repo_hook user:email";
        return URL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI+"&response_type=code";
    }

    @Override
    public String getAccessToken(String code) {
        return null;
    }
}
