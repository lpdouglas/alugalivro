package com.lpdouglas.alugalivro.controller;

import com.lpdouglas.alugalivro.dto.LoginDto;
import com.lpdouglas.alugalivro.model.UserSession;
import com.lpdouglas.alugalivro.service.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class LoginController {

    private final LoginService loginService;
    @Value("${session.user}")
    private String SESSION_USER;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public boolean signIn(@Valid @RequestBody LoginDto login, HttpSession session){
        if (!loginService.login(login.getEmail(), login.getPassword())) {
            return false;
        }

        UserSession userSession = new UserSession(String.valueOf(login.getEmail()));
        session.setAttribute(SESSION_USER, userSession);
        int SESSION_MAX_INTERVAL = 60;
        session.setMaxInactiveInterval(SESSION_MAX_INTERVAL);

        return true;
    }

    @PostMapping("/logout")
    public boolean logout(HttpServletRequest request) {
        if (request.getSession().getAttribute(SESSION_USER) == null) {
            return false;
        }

        request.getSession().invalidate();
        return true;
    }

    @GetMapping("/session")
    public UserSession getSessionUser(HttpSession session) {
        return (UserSession) session.getAttribute(SESSION_USER);
    }

}
