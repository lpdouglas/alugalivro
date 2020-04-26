package com.lpdouglas.alugalivro.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean login(String email, String password) {
        return (email != null && !email.isBlank() && password != null && !password.isBlank());
    }
}
