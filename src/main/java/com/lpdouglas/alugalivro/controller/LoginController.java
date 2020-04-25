package com.lpdouglas.alugalivro.controller;

import com.lpdouglas.alugalivro.dto.LoginDto;
import com.lpdouglas.alugalivro.model.UserSession;
import com.lpdouglas.alugalivro.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LoginService loginService;
    @Value("${session.user}")
    private String SESSION_USER;
    private final int SESSION_MAX_INTERVAL = 60;

    @PostMapping("/login")
    public boolean signIn(@Valid @RequestBody LoginDto login, HttpSession session){
        if (!loginService.signIn(login.getEmail(), login.getPassword())) {
            return false;
        }

        UserSession userSession = new UserSession(String.valueOf(login.getEmail()));
        session.setAttribute(SESSION_USER, userSession);
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
//        System.out.println("SESSON: " + "" + " maxInterval: "+ session.getMaxInactiveInterval());
        return (UserSession) session.getAttribute(SESSION_USER);
    }

}
