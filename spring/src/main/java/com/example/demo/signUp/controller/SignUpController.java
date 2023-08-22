package com.example.demo.signUp.controller;

import com.example.demo.signUp.controller.form.request.SignUpRequestForm;
import com.example.demo.signUp.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("/sign-up")
    public boolean signUp(@RequestBody SignUpRequestForm requestForm) {
        log.info("requestForm()", requestForm);

        return signUpService.register(requestForm);
    }
}
