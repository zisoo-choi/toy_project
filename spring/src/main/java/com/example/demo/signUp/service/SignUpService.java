package com.example.demo.signUp.service;

import com.example.demo.signUp.controller.form.request.SignUpRequestForm;

public interface SignUpService {
    boolean register(SignUpRequestForm requestForm);

}
