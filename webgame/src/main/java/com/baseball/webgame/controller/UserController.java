package com.baseball.webgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.baseball.webgame.model.UserVO;
import com.baseball.webgame.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
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

    /**
     * 로그인 실패 폼

     */
    @GetMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }

    /**
     * 회원가입 진행
     */
    @PostMapping("/signUp")
    public String signUp(UserVO user) {
        userService.joinUser(user);
        return "login";
    }
}