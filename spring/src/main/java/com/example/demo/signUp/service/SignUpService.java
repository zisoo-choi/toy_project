package com.example.demo.signUp.service;

import com.example.demo.signUp.controller.form.request.SignUpRequestForm;

public interface SignUpService {
    boolean register(SignUpRequestForm requestForm);

    Long findAccountIdByEmail(String email);

    Long signUpWithEmail(String email);
}
