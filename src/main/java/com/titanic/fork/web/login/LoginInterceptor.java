package com.titanic.fork.web.login;

import com.titanic.fork.utils.JwtProvider;
import org.apache.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        if (request.getMethod().equals(LoginEnum.OPTIONS.getValue())) {
            return true;
        }

        try {
            String userEmailInHeader = request.getHeader(LoginEnum.AUTHORIZATION.getValue());
            String jwtUserEmail = JwtProvider.parseJwt(userEmailInHeader);
            request.setAttribute(LoginEnum.USER_EMAIL.getValue(), jwtUserEmail);
            return true;
        } catch (Exception e) {
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return false;
        }
    }
}

