package com.baseball.webgame.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

class AuthFailureHandler implements AuthenticationFailureHandler{

    private static final Logger logger = LoggerFactory.getLogger(AuthFailureHandler.class);
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        // TODO Auto-generated method stub
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String id = request.getParameter("id");
        logger.info(username);
        logger.info(id);
        logger.info(password);
        response.sendRedirect("access_denied");
    }
}