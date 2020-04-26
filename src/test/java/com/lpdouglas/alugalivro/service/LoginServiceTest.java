package com.lpdouglas.alugalivro.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginServiceTest {

    @InjectMocks
    LoginService loginService;

    @Test
    public void loginOk(){
        //Action
        boolean login = loginService.login("some@email.com", "myPassw0rd");

        //Verify
        assertTrue(login);
    }

    @Test
    public void loginWithoutEmail(){
        //Action
        boolean login = loginService.login("", "myPassw0rd");

        //Verify
        assertFalse(login);
    }

    @Test
    public void loginWithoutPassword(){
        //Action
        boolean login = loginService.login("some@email.com", "");

        //Verify
        assertFalse(login);
    }

}