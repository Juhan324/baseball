package com.baseball.webgame.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baseball.webgame.mapper.UserMapper;
import com.baseball.webgame.model.User;

@Service
public class UserService implements UserDetailsService {
    private UserMapper userMapper;

    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Transactional
    public void joinUser(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userMapper.insertUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        User user = userMapper.getUser(id);
        if (user == null){
            throw new UsernameNotFoundException("User not authorized.");
        }
        return user;
    }
}