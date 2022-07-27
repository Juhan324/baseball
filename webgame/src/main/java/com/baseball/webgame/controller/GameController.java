package com.baseball.webgame.controller;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class GameController {
    private final SessionRegistry sessionRegistry;

    
    @RequestMapping("lobby")
    public String lobby(Model model){
        model.addAttribute("loginedUser", sessionRegistry.getAllPrincipals().stream().map(o->((User)o).getUsername()).toList());
        return "lobby";
    }
    
    @GetMapping("/chat")
    public ModelAndView chatGET(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("chat");
        return mv;
    }

}
