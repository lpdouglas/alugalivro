package com.lpdouglas.alugalivro.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean signIn(String email, String password) {
        return (email != null && password != null);
    }
}
