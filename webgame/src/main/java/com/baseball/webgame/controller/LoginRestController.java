package com.baseball.webgame.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baseball.webgame.VO.UserVO;
import com.baseball.webgame.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginRestController {
    
    private final UserService userService;
    

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