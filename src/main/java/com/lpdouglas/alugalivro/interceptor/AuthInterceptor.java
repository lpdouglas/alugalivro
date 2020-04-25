package com.lpdouglas.alugalivro.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Value("${session.user}")
    private String SESSION_USER;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object controller) {

        String uri = request.getRequestURI();
        if(uri.endsWith("/login") || uri.endsWith("/error")){
            return true;
        }

        if(request.getSession().getAttribute(SESSION_USER) != null) {
            return true;
        }

        response.setStatus(401);
        return false;
    }
}
