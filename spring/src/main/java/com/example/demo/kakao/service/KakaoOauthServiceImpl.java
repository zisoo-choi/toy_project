package com.example.demo.kakao.service;

import com.example.demo.kakao.dto.KakaoOAuthToken;
import com.example.demo.kakao.repository.UserRepository;
import com.example.demo.signUp.repository.SignUpRepository;
import com.example.demo.utility.PropertyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoOauthServiceImpl implements KakaoOauthService {

    final private RestTemplate restTemplate;
    final private PropertyUtil propertyUtil;

    @Override
    public String getAuthorizeCode() {
        final String CLIENT_ID = propertyUtil.getProperty("client_id");
        final String URL = "https://kauth.kakao.com/oauth/authorize";
        final String REDIRECT_URI = "http://localhost:8080/authentication/kakao/callback";
        return URL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&response_type=code";
    }

    @Override
    public @ResponseBody String kakaoCallback(String code){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add(
                "Content-Type",
                "application/x-www-form-urlencoded;charset=utf-8"
        );

        final String CLIENT_TOKEN_REQUEST = "https://kauth.kakao.com/oauth/token";
        final String CLIENT_ID = propertyUtil.getProperty("client_id");
        final String CLIENT_SECRET = propertyUtil.getProperty("client_secrets");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", CLIENT_ID);
        params.add("redirect_uri", "http://localhost:8080/authentication/kakao/callback");
        params.add("code", code);
        params.add("client_secret",CLIENT_SECRET);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                CLIENT_TOKEN_REQUEST,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        return "카카오 토큰 요청 완료 -> 토큰 요청에 대한 응답 : "+response;
    }



}
