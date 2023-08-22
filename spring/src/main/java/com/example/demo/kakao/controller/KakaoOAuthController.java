package com.example.demo.kakao.controller;

import com.example.demo.kakao.service.KakaoOauthService;
import com.example.demo.signUp.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class KakaoOAuthController {

    final private KakaoOauthService kakaoOauthService;
    final private SignUpService accountService;

    @GetMapping("/kakao/login")
    public String requestKakaoAuthorizeCode() {
        log.info("requestKakaoAuthorizeCode");

        return kakaoOauthService.getAuthorizeCode();
    }

    @GetMapping("/kakao/callback")
    public @ResponseBody String kakaoCallback(String code) {
        log.info("카카오 코드를 받았습니다.");
        return "카카오 인증 완료: 코드값:" + code;
    }

}
