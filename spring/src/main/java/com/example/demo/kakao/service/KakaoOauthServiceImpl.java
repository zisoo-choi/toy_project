package com.example.demo.kakao.service;

import com.example.demo.kakao.dto.KakaoOAuthToken;
import com.example.demo.signUp.entity.Member;
import com.example.demo.signUp.repository.SignUpRepository;
import com.example.demo.utility.PropertyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoOauthServiceImpl implements KakaoOauthService {

    final private PropertyUtil propertyUtil;
    final private SignUpRepository signUpRepository;

    @Override
    public String getAuthorizeCode() {
        final String CLIENT_ID = propertyUtil.getProperty("client_id");
        final String URL = "https://kauth.kakao.com/oauth/authorize";
        final String REDIRECT_URI = "http://localhost:8080/authentication/kakao/callback";
        return URL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&response_type=code";
    }

    @Override
    public KakaoOAuthToken kakaoCallback(String code){
        KakaoOAuthToken kakaoOAuthToken = getAccessToken(code);
        ResponseEntity<String> response = requestUserInfo(kakaoOAuthToken);
        Member user = saveUserInfo(response);

        return kakaoOAuthToken;
    }

    private Member saveUserInfo(ResponseEntity<String> response) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap;
        try{
            jsonMap = objectMapper.readValue(response.getBody(), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON string", e);
        }

        // "properties" 키 아래의 중첩된 JSON 객체 파싱
        Map<String, Object> propertiesMap = (Map<String, Object>) jsonMap.get("properties");
        String nickname = (String) propertiesMap.get("nickname");
        String profile_image = (String) propertiesMap.get("profile_image");

        // "kakao_account" 키 아래의 중첩된 JSON 객체 파싱
        Map<String, Object> kakaoAccountMap = (Map<String, Object>) jsonMap.get("kakao_account");
        String email = (String) kakaoAccountMap.get("email");
        String gender = (String) kakaoAccountMap.get("gender");
        String age_range = (String) kakaoAccountMap.get("age_range");

        Optional<Member> maybeUser = signUpRepository.findByEmail(email);
        Member savedMember;

        if(maybeUser.isEmpty()) {
            String name = nickname;
//            savedMember = signUpRepository.save(new Member(name, nickname, email));
            savedMember = signUpRepository.save(new Member(name, nickname, email, profile_image, gender, age_range));
        } else {
            savedMember = maybeUser.get();
        }
        return savedMember;
    }

    private ResponseEntity<String> requestUserInfo(KakaoOAuthToken kakaoOAuthToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoOAuthToken.getAccess_token());

        final String USER_INFO_REQUEST = "https://kapi.kakao.com/v2/user/me";
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                USER_INFO_REQUEST,
                HttpMethod.GET,
                request,
                String.class
        );
        System.out.println("response.getBody() = " + response.getBody());
        return response;
    }


    private KakaoOAuthToken getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-type", "application/x-www-form-urlencoded");

        final String CLIENT_ID = propertyUtil.getProperty("client_id");
        final String CLIENT_SECRET = propertyUtil.getProperty("client_secrets");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", CLIENT_ID);
        body.add("redirect_uri", "http://localhost:8080/authentication/kakao/callback");
        body.add("code", code);
        body.add("client_secret",CLIENT_SECRET);

        final String CLIENT_TOKEN_REQUEST = "https://kauth.kakao.com/oauth/token";
        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(body, headers);
        ResponseEntity<KakaoOAuthToken> response = restTemplate.exchange(
                CLIENT_TOKEN_REQUEST,
                HttpMethod.POST,
                tokenRequest,
                KakaoOAuthToken.class
        );

        System.out.println(response);
        System.out.println(response.getBody().getAccess_token());
        return response.getBody();
    }

}
