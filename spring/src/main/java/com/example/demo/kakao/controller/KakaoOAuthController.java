package com.example.demo.kakao.controller;

import com.example.demo.kakao.dto.KakaoOAuthToken;
import com.example.demo.kakao.service.KakaoOauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class KakaoOAuthController {

    final private KakaoOauthService kakaoOauthService;

    @GetMapping("/kakao/login")
    public String requestKakaoAuthorizeCode() {
        log.info("requestKakaoAuthorizeCode");
        return kakaoOauthService.getAuthorizeCode();
    }

    @GetMapping("/kakao/callback")
    public KakaoOAuthToken kakaoCallback(@RequestParam("code") String code) {
        log.info("카카오 코드를 받았습니다. 토큰 요청을 하겠습니다 !");
        return kakaoOauthService.kakaoCallback(code);
    }

}
