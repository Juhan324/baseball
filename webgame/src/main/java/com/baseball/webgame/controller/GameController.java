package com.baseball.webgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameController {
    @Autowired
    private SessionRegistry sessionRegistry;

    
    @RequestMapping("lobby")
    public String lobby(Model model){
        model.addAttribute("loginedUser", sessionRegistry.getAllPrincipals().stream().map(o->((User)o).getUsername()).toList());
        return "lobby";
    }
}
