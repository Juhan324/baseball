package com.baseball.webgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.baseball.webgame.model.UserVO;
import com.baseball.webgame.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String root() {
        return "redirect:/login";
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
        return "redirect:/login";
    }

    /**
     * 유저 페이지

     */
    @GetMapping("/user_access")
    public String userAccess(Model model, Authentication authentication) {
        //Authentication 객체를 통해 유저 정보를 가져올 수 있다.
        User user = (User) authentication.getPrincipal();  //userDetail 객체를 가져옴
        model.addAttribute("info", user.getUsername() + "님");      //유저 아이디
        return "user_access";
    }

}