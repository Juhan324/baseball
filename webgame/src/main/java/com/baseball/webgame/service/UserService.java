package com.baseball.webgame.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baseball.webgame.mapper.UserMapper;
import com.baseball.webgame.mapper.UserRepository;
import com.baseball.webgame.model.CustomUser;
import com.baseball.webgame.model.UserVO;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository repository;
    
    
    @Transactional
    public void joinUser(UserVO user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userMapper.insertUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        UserVO user = userMapper.getUser(id);
        List<CustomUser> user2 = repository.findAll();
        if (user == null){
            throw new UsernameNotFoundException("User not authorized.");
        }
        System.out.println(user2);
        String pw = user.getPassword();
        String role = user.getRole();
        return User.builder()
            .username(id)
            .password(pw)
            .roles(role)
            .build();
    }
}