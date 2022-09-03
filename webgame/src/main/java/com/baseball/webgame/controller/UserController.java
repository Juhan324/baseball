package com.baseball.webgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    
    @GetMapping
    public String root() {
        return "redirect:/lobby";
    }

    /**
     * 로그인 폼

     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 회원가입 폼

     */
    @GetMapping("/signUp")
    public String signUpForm() {
        return "signUp";
    }

}